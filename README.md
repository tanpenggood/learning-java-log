# Learn Java Log System

## Project Structure

```
├─java-log-impl           # Java some log implementations（jul、log4j、log4j2、logback）
├─java-log-api-jcl        # commons logging
├─java-log-api-slf4j      # slf4j
└─log4j2-vulnerabilities  # log4j2 CVE recurrence
```

## Log4j2 CVE-2021-44228 recurrence step
   
1. cd `log4j2-vulnerabilities`
2. run `com.itplh.log4j2.RmiServer`
3. run `com.itplh.log4j2.TestLog4j2UseJndi`

## Java Log System Implementation plan

- Interface
    - JCL（Apache Commons Logging）
    - Slf4j

- Implementation
    - JUL（java.util.logging）
    - log4j
    - log4j2
    - logback

## JCL（Apache Commons Logging, alias Jakarta Commons Logging）

1. cd `java-log-api-jcl`
2. Run `com.itplh.jcl.TestCommonsLogging`, then observe console output
3. Adjust `org.apache.commons.logging.Log's` value in `commons-logging.properties`, then rerun program and observe console output

### Principle

Load implement class who `org.apache.commons.logging.Log`, the bind log framework used it, invoke stack as follows：

```
Log log = LogFactory.getLog("jcl");
    |- getLog:669, LogFactory (org.apache.commons.logging)
        |- getInstance:292, LogFactoryImpl (org.apache.commons.logging.impl)
            |- newInstance:541, LogFactoryImpl (org.apache.commons.logging.impl)
                |- discoverLogImplementation:790, LogFactoryImpl (org.apache.commons.logging.impl)
                    |- createLogFromClass:960, LogFactoryImpl (org.apache.commons.logging.impl)
```

The whole process that commons-logging get Logger, details and summary as follows：

```
Get current thread of classloader, then classloader get LogFactroy from cache, that cache is WeakHashTable; if the cache exists it, return it, otherwise into next step；
 
Load commons-logging.properties that under classpath, judge use_tccl attribute, if it exists, then judge its value, if value is false, set baseClassLoader as current class classLoader；
 
Then get LogFactory, and has four ways: 
    (1)in system property find “org.apache.commons.logging.LogFactory” of value, then generate LogFactory;
    (2)in resource “META-INF/services/org.apache.commons.logging.LogFactory” of value, then generate LogFactory;
    (3)get “org.apache.commons.logging.LogFactory” of value from commons-logging.properties, then generate LogFactory;
    (4)if none of the above successful, create default log factory：org.apache.commons.logging.impl.LogFactoryImpl

After get log factory is successfly, according to class name  get Log instance;

The main logic is in discoverLogImplementation method:
    (1)Check is exists “org.apache.commons.logging.Log” key in commons-logging.properties, create Log's implement class if this key exists, otherwise execute next logic;
    (2)Traverse classesToDiscover array, this array saved Log's implement class full name and it contains four elements：org.apache.commons.logging.impl.Log4JLogger、org.apache.commons.logging.impl.Jdk14Logger、org.apache.commons.logging.impl.Jdk13LumberjackLogger、org.apache.commons.logging.impl.SimpleLog；
    (3)Accroding to these full class name, load class file in order, then instantiates, final return Log's implementation.(Log's default implement class is Jdk14Logger.)
```
## Slf4j Adaptor Solution 

1. cd `java-log-api-slf4j`
2. Run `com.itplh.slf4j.TestSlf4j`, then observe console output
3. Change slf4j's adaptor dependence relation where pom.xml, then rerun program and observe console out put

### Principle

Load `org.slf4j.impl.StaticLoggerBinder` from slf4j adaptor jar package, then bind log framework used it, invoke stack as follows：

```
Logger logger = LoggerFactory.getLogger("slf4j");
    |- getLogger:362, LoggerFactory (org.slf4j)
        |- getILoggerFactory:417, LoggerFactory (org.slf4j)
            |- performInitialization:124, LoggerFactory (org.slf4j)
                |- bind:146, LoggerFactory (org.slf4j)
                    |- findPossibleStaticLoggerBinderPathSet:301, LoggerFactory (org.slf4j)
```

> If has multiple `org.slf4j.impl.StaticLoggerBinder` where classpath, final load one of these what depend on JVM class load mechanism.

## Slf4j Bridge Solution

## Spring Log Solution Action

## Reference

[Apache Log4j](https://logging.apache.org/log4j/1.2/index.html)

[Apache Log4j 2](https://logging.apache.org/log4j/2.x/index.html)

[logback](http://logback.qos.ch/)

[Apache Commons Logging](https://commons.apache.org/proper/commons-logging/)

[Simple Logging Facade for Java (SLF4J)](http://www.slf4j.org/)