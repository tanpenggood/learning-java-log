## log4j2 安全漏洞

[Apache Log4j Security Vulnerabilities](https://logging.apache.org/log4j/2.x/security.html#)

- CVE-2021-44228
    - Severity: Critical
    - Fixed in Log4j 2.15.0
    
- CVE-2021-45046
    - Fixed in Log4j 2.12.2 and Log4j 2.16.0
    - Severity: Moderate

## CVE-2021-44228 复现步骤

1. 启动`com.itplh.log4j2.RmiServer`
2. 运行`com.itplh.log4j2.TestLog4j2UseJndi`

## CVE-2021-44228 修复方案

### 临时修复

启动程序时增加JVM参数`-Dlog4j2.formatMsgNoLookups=true`

### 正式修复

升级log4j2版本到 2.16.0 + 

https://logging.apache.org/log4j/2.x/changes-report.html