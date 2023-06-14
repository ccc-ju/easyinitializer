# EasyInitializr | SpringBoot 脚手架，简化项目构建

> **作者：** juju

<br/>

---

`为什么我们要去造轮子？`

造轮子的核心目的，是为了解决通用共性问题的凝练和复用。

虽然市面上已经有了大量成熟稳定用于支撑系统建设的轮子，也就是服务、框架、组件、工具等，但对于一些较大型的公司来说，这些轮子可能并不一定能很好的支撑起系统需要承载的服务体量，这个时候就需要自建一些轮子。

而提倡的不重复造轮子，新造轮子不一定能保证稳定性。一般用在以官网推出的核心轮子上是适合的，比如 SpringBoot、Netty、HBase 等。但对于一些特殊场景的解决方案工具型组件，通常是没有完全符合的轮子的，就像 SpringBoot 脚手架。

其实每个较大型的公司都会有很多同类技术服务的组件，例如 RPC、数据库路由、注册中心、分布式任务、MQ队列消息等，而这时候脚手架的开发就需要适配这些组件，搭建出符合自己公司技术栈实现需要的系统架构。*这不同于一些较小的互联网公司，可以完全使用 SpringBoot 提供的一整套解决方案*

**另外**，造轮子是个人技术沉淀、也是薪资待遇的积累！*别说造不了飞机，只是你没有提供场地！*

## 一、什么是脚手架呢？

**What is scaffolding? Is it a term for a particular platform?**

>Scaffolding is a meta-programming method of building database-backed software applications. It is a technique supported by some model-view-controller frameworks, in which the programmer may write a specification that describes how the application database may be used. The compiler uses this specification to generate code that the application can use to create, read, update and delete database entries, effectively treating the template as a "scaffold" on which to build a more powerful application.

- [https://stackoverflow.com/questions/235018/what-is-scaffolding-is-it-a-term-for-a-particular-platform](https://stackoverflow.com/questions/235018/what-is-scaffolding-is-it-a-term-for-a-particular-platform)


结合 stackoverflow 上的回答，脚手架是一种元编程方法，用于构建基于数据的应用。创建系统架构的程序员编写一份规格说明书，用于描述怎么去使用数据库。而脚手架可以根据这份规则说明书生成相应的框架代码。我们把这种模式成为脚手架，在脚手架上更高效的构建出 `powerful` 的应用！

说白了就是简化具有共性重复操作的简单工作，不再需要程序员还得一点点粘贴复制，克隆出一个已经存在的架构。只需要在界面或者公用接口上，传入必要的参数，就可以创建出一个应用开发框架。

## 二、谁提供了脚手架？

### 1、Spring 官网脚手架

![spring initializr](https://image.laobinggun.site/github/spring.png)

- 推荐：⭐⭐⭐⭐
- 链接：[https://start.spring.io](https://start.spring.io/)
- 源码：[https://github.com/spring-io/start.spring.io](https://github.com/spring-io/start.spring.io)
- 描述：Spring Initializr 本质上也是一个 Web 应用，它可以通过 Web 界面、Spring Tool Suite、IntelliJ IDEA 等方式，构建出一个基本的 Spring Boot 项目结构。*同时可以使用它的源码进行本地部署*

### 2、阿里云脚手架

![Aliyun Java Initializr](https://image.laobinggun.site/github/alibaba.png)

- 推荐：⭐⭐⭐⭐
- 链接：[https://start.spring.io](https://start.spring.io/)
- 描述：Aliyun Java Initializr 和 Spring Initializr 是同类的 Web 服务，是代码框架生成器，一键生成你的代码框架，有完善的工具链，免费的IDEA插件，方便直接在IDE中生成，同时也非常适合国内用户的网络环境。

---

**其实**，这两个脚手架都能很好的生成项目结构，让程序员可以在统一的标准下快速的进入开发环境。只是依赖于自身选择的支撑服务，选择不同的框架就可以了。

## 三、手撸一个脚手架！

`都有脚手架了，那为什么要自己撸一个呢？`

脚手架的目的是为了在统一的标准下快速建设系统框架，把系统开发过程中需要的配置、组件、服务、测试，一并通过配置引入到系统开发中。

但有些时候在互联网公司通用的脚手架是不太合适使用的，因为它没有把公司内的一些自研性质的组件引入进去，也不能很好的融合。如果已经用脚手架生成后还得需要研发人员自己大量复制进去一些特定的组件，就破坏了脚手架本身能力，也是破坏了准则和规范。

**所以**，需要结合脚手架的开发能力，包装各类特定组件、服务、配置，实现符合公司领域的统一脚手架。

**那么**，本章节就带着大家看看一个脚手架，该如何开发实现。其实并没有太复杂，我们可以使用 freemarker 的能力，构建系统框架。

### 1. 工程框架

```java
EasyInitializr
```
![img.png](https://image.laobinggun.site/github/img.png)

脚手架生成工程

整个用于创建脚手架的工程并不复杂，主要就是通过 freemarker 对各类定义的 ftl 模板文件，生成对应的系统框架结构。这里包括：工程主体、框架结构、启动类、配置文件、测试类等，也可以结合自身需求把对应 ORM 的类和映射关系生成出来。

整个工程结构偏 DDD 层次结构，domain 领域中建设了所有的生成方式，resources/generator 定义生成模板，其他地方就没有太大的差异了。

**综上**，就是整个脚手架生成的简要介绍，其实并没有多复杂，主要就是 ftl 文件的定义和使用，这种创建脚手架的方式还是很方便的。

### 2. 如何使用

**网页访问**

运行项目，打开 http:127.0.0.1:8081/index.html

输入项目信息，点击 '下载' 按钮，即可下载已经生成的项目zip文件

注：项目中为了防止信息泄漏，模板中未定义redis / mysql数据库 连接及密码

![项目截图](https://image.laobinggun.site/github/projectinit.png)

下载后的项目直接使用idea打开:
![项目结构](https://image.laobinggun.site/github/openproject.png)

生成的项目代码:
![部分代码示例](https://image.laobinggun.site/github/code-example.png)

### 3.更多功能还在加紧排期完善中，敬请期待...



