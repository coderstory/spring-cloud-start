# spring-cloud-start
学习spring cloud全家桶的附带项目

整个项目的成本，最终应该是一个包含了服务发现与注册，oauth2.0认证，网关服务，消息队列，webmvc，mybatis，feign声明式调用服务，熔断机制，配置统一管理等内容。

因为spring cloud netflix组件已经不再维护更新，所以会有两个分支的代码，master将使用spring cloud gateway之类的组件替代netflix,spring cloud netflix组件单独一个分支


### 当前实现功能 ###

> 1. spring cloud 多module项目搭建
>   - 一个基本的多模块的的java项目，所有的微服务项目公用一个parent
> 2. 基础spring boot项目搭建
>   - 所有的微服务都是一般基于spring boot搭建的,当然也可能是其他语言写的.
> 3. zuul网关
>   - 所有的微服务应该都对外封闭的，对外暴露的只有网关的api，网关通过负载均衡和智能路由将请求转发，且实现了基本的权限校验，登入用户校验，参数过滤等功能，
> 4. euraka服务注册与发现
>   - 微服务项目所必须的组件，实现不同微服务之间的关联互访。
> 5. 使用Feign和RestTemplatejinx服务间通信
 >  - 每个微服务都不是独立存在的，总会有那么一些需求需要在一个服务内访问其他的服务。
