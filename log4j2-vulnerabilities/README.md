## log4j2 安全漏洞

[Apache Log4j Security Vulnerabilities](https://logging.apache.org/log4j/2.x/security.html#)

- CVE-2021-44228： Apache Log4j2 JNDI features do not protect against attacker controlled LDAP and other JNDI related endpoints.
    - Severity: Critical
    - Fixed in Log4j 2.15.0
    - Versions Affected: all versions from 2.0-beta9 through 2.12.1 and 2.13.0 through 2.14.1
    
- CVE-2021-45046：Apache Log4j2 Thread Context Message Pattern and Context Lookup Pattern vulnerable to a denial of service attack.
    - Fixed in Log4j 2.12.2 and Log4j 2.16.0
    - Severity: Moderate
    - Versions Affected: all versions from 2.0-beta9 through 2.12.1 and 2.13.0 through 2.15.0

## CVE-2021-44228 复现步骤

1. 启动`com.itplh.log4j2.RmiServer`
2. 运行`com.itplh.log4j2.TestLog4j2UseJndi`

## CVE-2021-44228 修复方案

### 临时修复

启动程序时增加JVM参数`-Dlog4j2.formatMsgNoLookups=true`

### 正式修复

升级log4j2版本到 2.16.0 + 

https://logging.apache.org/log4j/2.x/changes-report.html