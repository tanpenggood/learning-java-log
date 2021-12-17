# 学习Java日志体系

## 项目结构

```
├─java-log-impl           # Java各个日志实现（jul、log4j、log4j2、logback）
├─java-log-api-jcl        # commons logging
├─java-log-api-slf4j      # slf4j
└─log4j2-vulnerabilities  # log4j2安全漏洞复现
```

## Log4j2 CVE-2021-44228 复现步骤
   
1. cd `log4j2-vulnerabilities`
2. 启动`com.itplh.log4j2.RmiServer`
3. 运行`com.itplh.log4j2.TestLog4j2UseJndi`

## Java日志体系实现方案

- 接口
    - JCL（Apache Commons Logging）
    - Slf4j

- 实现
    - JUL（java.util.logging）
    - log4j
    - log4j2
    - logback

## JCL（Apache Commons Logging，又名Jakarta Commons Logging）

1. cd `java-log-api-jcl`
2. 运行 `com.itplh.jcl.TestCommonsLogging`，观察日志输出
3. 调整 `commons-logging.properties` 中 Log 的具体实现，再重新运行程序，观察日志输出

## Slf4j适配解决方案

## Slf4j桥接方案

## Spring日志方案实战

## 参考

[Apache Log4j](https://logging.apache.org/log4j/1.2/index.html)

[Apache Log4j 2](https://logging.apache.org/log4j/2.x/index.html)

[logback](http://logback.qos.ch/)

[Apache Commons Logging](https://commons.apache.org/proper/commons-logging/)

[Simple Logging Facade for Java (SLF4J)](http://www.slf4j.org/)