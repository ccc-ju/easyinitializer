## 命名规则：
groupId:统一使用com.deepinnet
<br/>
artifactId：deepinnet-应用名

包命名规范：com.deepinnet.应用名.模块名
<br/>
举例：com.deepinnet.${artifactId}.biz

## 项目分层说明：
- ${artifactId}-app-starter（SpringBoot应用启动类）
- ${artifactId}-biz（业务层）
  - ${artifactId}-biz-service-impl（业务层的实现，具体业务流程编排）

- ${artifactId}-common（通用层）
  - ${artifactId}-common-dal（仓储层）
  - ${artifactId}-common-service
    - ${artifactId}-common-service-facade（需要向外界暴露的领域服务）
    - ${artifactId}-common-service-integration（集成的第三方接口）
  - ${artifactId}-common-util（工具）

- ${artifactId}-core（领域层）
  - ${artifactId}-core-service（本系统内使用的service接口）
  - ${artifactId}-core-model（领域模型）

## 各模块依赖关系：
- biz：负责业务流程编排，依赖于common-service-facade、common-service-integration、
  common-service-util、core-service
- core-service：本系统内的服务， 不需要向外暴露，依赖core-model、
  common-dal、common-util
