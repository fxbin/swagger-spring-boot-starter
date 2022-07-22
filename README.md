# swagger-spring-boot-starter
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/70cd38871b8041f0a4f9c93d44c59c93)](https://www.codacy.com/gh/fxbin/swagger-spring-boot-starter/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fxbin/swagger-spring-boot-starter&amp;utm_campaign=Badge_Grade)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.5.3-brightgreen.svg)](https://github.com/spring-projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/SpringCloud-2020.0.3-brightgreen.svg)](https://github.com/spring-cloud)
[![Knife4j](https://img.shields.io/badge/Knife4j-3.0.3-brightgreen.svg)](https://gitee.com/xiaoym/knife4j)

```
自制 swagger spring-boot-starter, 默认集成 knife4j, 界面更加友好，同时使用便捷，一键集成sringboot 项目，
同时支持一键开启网关聚合功能，使你解脱书写、配置Swagger API文档的烦恼...
```

| 依赖 | 版本       |
---|----------
| Spring Boot | 2.5.3    |
| Spring Cloud | 2020.0.3 | 
| Knife4j | 3.0.3    |  

* 注意： 笔者仅测试过如上版本的使用情况，低版本请自行测试

## 使用步骤

PS: `最新版为1.4`

1. 添加 maven 依赖
```xml
<dependency>
    <groupId>cn.fxbin.swagger</groupId>
    <artifactId>swagger-spring-boot-starter</artifactId>
    <version>${currentVersion}</version>
</dependency>
```
PS: 已发布中央仓库，可直接引用

2. 修改配置文件

```yaml
spring:
  swagger:
    enabled: true

  # 如果使用 2.6.X 以上版本，需增加如下配置，否则不生效
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
```

更多配置属性参考：

![配置属性](doc/swagger%20配置属性.png)

## 其它注意事项

与网关集成时，默认按照 `spring.cloud.gateway.discovery.locator.enabled` 配置项来决定是否开启动态文档，默认读取文件配置

## 参考文档
[knife4j doc](https://doc.xiaominfo.com/knife4j/)
