package cn.fxbin.swagger.webflux;

import cn.fxbin.swagger.SwaggerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SwaggerProvider
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/4/3 11:19
 */
@Slf4j
@Primary
@Component
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnClass({HandlerFunction.class})
public class SwaggerProvider implements SwaggerResourcesProvider {

    private static final String API_URI = "/v3/api-docs";

    @Resource
    private GatewayProperties gatewayProperties;

    @Resource
    private DiscoveryLocatorProperties discoveryLocatorProperties;

    @Resource
    private RouteDefinitionRepository routeDefinitionRepository;

    @Resource
    private DiscoveryClient discoveryClient;

    @Override
    public List<SwaggerResource> get() {

        List<RouteDefinition> routeList = new ArrayList<>(gatewayProperties.getRoutes());

        routeDefinitionRepository.getRouteDefinitions()
                .sort(Comparator.comparingInt(RouteDefinition::getOrder))
                .subscribe(routeList::add);

        return routeList.stream().distinct().flatMap(routeDefinition -> routeDefinition.getPredicates().stream()
                .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                .map(predicateDefinition ->
                        swaggerResource(routeDefinition.getId(), predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI))
                ))
                // 过滤注册中心没有的服务
                .filter(swaggerResource -> {
                    if (isDynamic()) {
                        return discoveryClient.getServices().stream()
                                .anyMatch(serviceId ->
                                        serviceId.equalsIgnoreCase(swaggerResource.getName()));
                    }
                    return true;
                })
                .sorted(Comparator.comparing(SwaggerResource::getName))
                .distinct()
                .collect(Collectors.toList());
    }


    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{}, location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }


    /**
     * isDynamic
     *
     * <p>
     *     根据 {@link DiscoveryLocatorProperties#isEnabled()}
     *     决定是否依据服务发现规则走文档自动过滤加载，默认按照配置文件形式
     * </p>
     *
     * @since 2022/2/16 14:20
     * @return {@link boolean}
     */
    private boolean isDynamic() {
        return discoveryLocatorProperties.isEnabled();
    }

}
