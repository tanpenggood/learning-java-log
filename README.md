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

### 原理

查找`org.apache.commons.logging.Log`的实现类，进行日志框架的绑定，调用栈如下：

```
Log log = LogFactory.getLog("jcl");
    |- getLog:669, LogFactory (org.apache.commons.logging)
        |- getInstance:292, LogFactoryImpl (org.apache.commons.logging.impl)
            |- newInstance:541, LogFactoryImpl (org.apache.commons.logging.impl)
                |- discoverLogImplementation:790, LogFactoryImpl (org.apache.commons.logging.impl)
                    |- createLogFromClass:960, LogFactoryImpl (org.apache.commons.logging.impl)
```

commons-logging获取日志对象的全过程，具体文字总结如下：

> 获取当前线程的classLoader,根据classLoader从缓存中获取LogFactroy,使用的缓存是WeakHashTable对象;如果缓存中存在，则返回，没有则进入下面流程；
>  
> 读取classpath下的commons-logging.properties文件，判断其中是否设置了use_tccl属性，如果不为空则判断，该属性的值是否为false,若为false，则将baseClassLoader替换为当前类的classLoader；
>  
> 接着，继续获取LogFactory对象，此步骤分为4中方式：
      (1)在系统属性中查找“org.apache.commons.logging.LogFactory”属性的值，根据值生成LogFactory对象；
      (2)通过资源“META-INF/services/org.apache.commons.logging.LogFactory”文件，获取的值生成LogFactory对象；
      (3)通过配置文件commons-logging.properties，获取以“org.apache.commons.logging.LogFactory”为key的值，根据值生成logFactory；
      (4)如果以上均不成功，则创建系统默认的日志工厂：org.apache.commons.logging.impl.LogFactoryImpl
>  
> 成功获取日志工厂后，根据类名获取日志对象；
>  
> 主要逻辑在discoverLogImplementation方法中：
      (1)检查commons-logging.properties文件中是否存在“org.apache.commons.logging.Log”属性，若存在则创建具体的日志对象；若不存在，进行下面逻辑；
      (2)遍历classesToDiscover数组，该数组存有日志具体实现类的全限定类名：org.apache.commons.logging.impl.Log4JLogger、org.apache.commons.logging.impl.Jdk14Logger、org.apache.commons.logging.impl.Jdk13LumberjackLogger、org.apache.commons.logging.impl.SimpleLog；
      (3)根据数组中存着的全限定类名，按照顺序依次加载Class文件，进行实例化操作，最后返回Log实例，默认为Jdk14Logger；

## Slf4j适配解决方案

1. cd `java-log-api-slf4j`
2. 运行 `com.itplh.slf4j.TestSlf4j`，观察日志输出
3. 调整 `pom.xml` 中 slf4j适配到各个日志的依赖关系，再重新运行程序，观察日志输出

### 原理

查找适配器包中的`org.slf4j.impl.StaticLoggerBinder`类，进行日志框架的绑定，调用栈如下：

```
Logger logger = LoggerFactory.getLogger("slf4j");
    |- getLogger:362, LoggerFactory (org.slf4j)
        |- getILoggerFactory:417, LoggerFactory (org.slf4j)
            |- performInitialization:124, LoggerFactory (org.slf4j)
                |- bind:146, LoggerFactory (org.slf4j)
                    |- findPossibleStaticLoggerBinderPathSet:301, LoggerFactory (org.slf4j)
```

> 当classpath中有多个`org.slf4j.impl.StaticLoggerBinder`时，最终由JVM类加载机制决定加载其中的一个。

## Slf4j桥接方案

## Spring日志方案实战

## 参考

[Apache Log4j](https://logging.apache.org/log4j/1.2/index.html)

[Apache Log4j 2](https://logging.apache.org/log4j/2.x/index.html)

[logback](http://logback.qos.ch/)

[Apache Commons Logging](https://commons.apache.org/proper/commons-logging/)

[Simple Logging Facade for Java (SLF4J)](http://www.slf4j.org/)