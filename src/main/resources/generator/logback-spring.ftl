<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <property name="CONSOLE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}] %-5level %logger{36} - %msg%n"/>

    <!--dev文件路径：src同级目录logs,如果上级目录不存在会自动创建-->
    <property name="LOG_FILE_PATH" value="./logs/${artifactId}" />

    <!-- 控制台输出 -->
    <appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${r"${CONSOLE_LOG_PATTERN}"}</pattern>
        </encoder>
    </appender>

    <!-- 按照每天生成系统默认日志文件 -->
    <appender name="defaultAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${r"${LOG_FILE_PATH}"}/common-default.log</file>
        <encoder charset="UTF-8">
            <!--格式化输出：%d表示日期，%thread表示线程，%-5level：级别从左显示五个字符宽度，%logger{36}：logger是class的全名,后面的数字代表限制最长的字符，%msg：日志消息，%n换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}][Tenant-Id:%X{Tenant-Id}] %-5level %logger{36}:%L - %msg%n</pattern>
        </encoder>
        <!--滚动策略按照时间滚动-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily 文件名称 -->
            <fileNamePattern>${r"${LOG_FILE_PATH}"}/common-default.log.%d{yyyy-MM-dd}</fileNamePattern>
        </rollingPolicy>
    </appender>

    <!-- 按照每天生成系统默认日志文件 -->
    <appender name="dalAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${r"${LOG_FILE_PATH}"}/common-dal.log</file>
        <encoder charset="UTF-8">
            <!--格式化输出：%d表示日期，%thread表示线程，%-5level：级别从左显示五个字符宽度，%logger{36}：logger是class的全名,后面的数字代表限制最长的字符，%msg：日志消息，%n换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}][Tenant-Id:%X{Tenant-Id}] %-5level %logger{36}:%L - %msg%n</pattern>
        </encoder>
        <!--滚动策略按照时间滚动-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily 文件名称 -->
            <fileNamePattern>${r"${LOG_FILE_PATH}"}/common-dal.log.%d{yyyy-MM-dd}</fileNamePattern>
        </rollingPolicy>
    </appender>


    <!-- 按照每天生成业务摘要日志文件 -->
    <appender name="bizDigestAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${r"${LOG_FILE_PATH}"}/biz-digest.log</file>
        <encoder charset="UTF-8">
            <!--格式化输出：%d表示日期，%thread表示线程，%-5level：级别从左显示五个字符宽度，%logger{36}：logger是class的全名,后面的数字代表限制最长的字符，%msg：日志消息，%n换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}][Tenant-Id:%X{Tenant-Id}] %-5level %logger{36}:%L - %msg%n</pattern>
        </encoder>
        <!--滚动策略按照时间滚动-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily 文件名称 -->
            <fileNamePattern>${r"${LOG_FILE_PATH}"}/biz-digest.log.%d{yyyy-MM-dd}</fileNamePattern>
        </rollingPolicy>
    </appender>

    <!-- 按照每天生成消息日志文件 -->
    <appender name="messageAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${r"${LOG_FILE_PATH}"}/message.log</file>
        <encoder charset="UTF-8">
            <!--格式化输出：%d表示日期，%thread表示线程，%-5level：级别从左显示五个字符宽度，%logger{36}：logger是class的全名,后面的数字代表限制最长的字符，%msg：日志消息，%n换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}][Tenant-Id:%X{Tenant-Id}] %-5level %logger{36}:%L - %msg%n</pattern>
        </encoder>
        <!--滚动策略按照时间滚动-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily 文件名称 -->
            <fileNamePattern>${r"${LOG_FILE_PATH}"}/message.log.%d{yyyy-MM-dd}</fileNamePattern>
        </rollingPolicy>
    </appender>

    <!-- 按照每天生成定时任务的日志文件 -->
    <appender name="scheduleTaskAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${r"${LOG_FILE_PATH}"}/task.log</file>
        <encoder charset="UTF-8">
            <!--格式化输出：%d表示日期，%thread表示线程，%-5level：级别从左显示五个字符宽度，%logger{36}：logger是class的全名,后面的数字代表限制最长的字符，%msg：日志消息，%n换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}][Tenant-Id:%X{Tenant-Id}] %-5level %logger{36}:%L - %msg%n</pattern>
        </encoder>
        <!--滚动策略按照时间滚动-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily 文件名称 -->
            <fileNamePattern>${r"${LOG_FILE_PATH}"}/task.log.%d{yyyy-MM-dd}</fileNamePattern>
        </rollingPolicy>
    </appender>

    <!-- 按照每天生成二方/三方集成日志文件 -->
    <appender name="integrationAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${r"${LOG_FILE_PATH}"}/integration.log</file>
        <encoder charset="UTF-8">
            <!--格式化输出：%d表示日期，%thread表示线程，%-5level：级别从左显示五个字符宽度，%logger{36}：logger是class的全名,后面的数字代表限制最长的字符，%msg：日志消息，%n换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}][Tenant-Id:%X{Tenant-Id}] %-5level %logger{36}:%L - %msg%n</pattern>
        </encoder>
        <!--滚动策略按照时间滚动-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily 文件名称 -->
            <fileNamePattern>${r"${LOG_FILE_PATH}"}/integration.log.%d{yyyy-MM-dd}</fileNamePattern>
        </rollingPolicy>
    </appender>

    <!-- 按照每天生成错误日志文件 -->
    <appender name="errorAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${r"${LOG_FILE_PATH}"}/common-error.log</file>
        <!-- 此日志文件只记录ERROR级别的 -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <encoder charset="UTF-8">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread][%X{traceId}][Tenant-Id:%X{Tenant-Id}] %-5level %logger{36}:%L - %msg%n</pattern>
        </encoder>
        <!--输出日志到src同级目录logs中的error.log文件中-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${r"${LOG_FILE_PATH}"}/common-error.log.%d{yyyy-MM-dd}</fileNamePattern>
        </rollingPolicy>
    </appender>

    <logger name="BIZ-DIGEST-LOG" level="info" additivity="false">
        <appender-ref ref="bizDigestAppender" />
    </logger>

    <logger name="MESSAGE-LOG" level="info" additivity="false">
        <appender-ref ref="messageAppender" />
    </logger>

    <logger name="SCHEDULE-TASK-LOG" level="info" additivity="false">
        <appender-ref ref="scheduleTaskAppender" />
    </logger>

    <logger name="INTEGRATION-LOG" level="info" additivity="false">
        <appender-ref ref="integrationAppender" />
    </logger>

    <logger name="DAL-LOG" level="info" additivity="false">
        <appender-ref ref="dalAppender" />
    </logger>

    <root level="INFO">
        <appender-ref ref="consoleAppender" />
        <appender-ref ref="defaultAppender" />
        <appender-ref ref="errorAppender" />
    </root>

</configuration>