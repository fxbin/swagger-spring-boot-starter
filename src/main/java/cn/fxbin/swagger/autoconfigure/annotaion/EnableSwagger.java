package cn.fxbin.swagger.autoconfigure.annotaion;

import cn.fxbin.swagger.autoconfigure.SwaggerAutoConfiguration;
import cn.fxbin.swagger.autoconfigure.SwaggerProperties;
import cn.fxbin.swagger.autoconfigure.webflux.RouterFunctionConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * EnableBubbleFireworkSwagger
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/3/31 18:49
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@Import({SwaggerAutoConfiguration.class, RouterFunctionConfiguration.class})
public @interface EnableSwagger {
}
