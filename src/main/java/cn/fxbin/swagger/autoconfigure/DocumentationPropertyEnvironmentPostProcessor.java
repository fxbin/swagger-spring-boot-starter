package cn.fxbin.swagger.autoconfigure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;
import springfox.boot.starter.autoconfigure.OpenApiAutoConfiguration;

import java.util.Properties;

/**
 * SpringFoxPropertyEnvironmentPostProcessor
 *
 * @author fxbin
 * @version v1.0
 * @since 2022/3/17 17:03
 */
public class DocumentationPropertyEnvironmentPostProcessor implements EnvironmentPostProcessor {


    /**
     * `springfox.documentation.enabled` 属性值默认为 true, 引发swagger文档关闭问题
     * {@link OpenApiAutoConfiguration}
     */
    public static final String SPRINGFOX_3 = "springfox.documentation.enabled";

    /**
     * 自定义的 swagger 开关属性
     * {@link cn.fxbin.swagger.SwaggerProperties}
     */
    public static final String  CUSTOMIZE_PROPERTY = "spring.swagger.enabled";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        // 获取自定义属性
        String enabled = environment.getProperty(CUSTOMIZE_PROPERTY, "false");
        // 设置属性值
        applyTo(SPRINGFOX_3, enabled);
    }

    /**
     * applyTo
     *
     * <p>
     *     配置系统属性
     * </p>
     *
     * @since 2021/9/9 10:36
     * @param key key
     * @param value value
     */
    public static void applyTo(String key, String value) {
        put(System.getProperties(), key, value);
        put(System.getProperties(), key, value);
    }

    private static void put(Properties properties, String key, String value) {
        if (StringUtils.hasText(value)) {
            properties.put(key, value);
        }
    }
}
