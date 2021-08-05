# swagger-spring-boot-starter
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/63f51f8ee55f42bd8284c1c04e2b6f7d)](https://app.codacy.com/manual/fxbin/swagger-spring-boot-starter?utm_source=github.com&utm_medium=referral&utm_content=fxbin/swagger-spring-boot-starter&utm_campaign=Badge_Grade_Settings)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.5.3-brightgreen.svg)](https://github.com/spring-projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/SpringCloud-2020.0.3-brightgreen.svg)](https://github.com/spring-cloud)
[![Knife4j](https://img.shields.io/badge/Knife4j-3.0.3-brightgreen.svg)](https://gitee.com/xiaoym/knife4j)
[![springfox](https://img.shields.io/badge/springfox-3.0.3-brightgreen.svg)](https://github.com/springfox/springfox)



自制 swagger spring-boot-starter, 默认集成 knife4j, 界面更加友好，同时使用便捷，使你解脱书写API文档的烦恼...



| 依赖 | 版本 |
---|---
| Spring Boot |  2.5.2 |
| Spring Cloud |  2020.0.3 | 
| Spring Cloud Alibaba |  2021.1 |
| Knife4j | 3.0.3 |  
| springfox | 3.0.0 |  


## 使用步骤

1. 添加 maven 依赖
```xml
<dependency>
    <groupId>cn.fxbin.swagger</groupId>
    <artifactId>swagger-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
PS: 已发布中央仓库，可直接引用

2. 修改配置文件

```yaml
spring:
  swagger:
    enabled: true
```

更多配置属性参考：

![配置属性](doc/swagger%20配置属性.png)


## 参考文档
[knife4j doc](https://doc.xiaominfo.com/knife4j/)
