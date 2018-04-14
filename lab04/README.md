基于Spring Security OAuth2+内存H2数据库的最简授权服务器
======

# 操作方式

### 1. 启动mobile_authserver，端口8080

### 2. 在Android Studio中启动AuthCodeApp

### 3. 校验

1. 校验h2内存数据库生成的访问令牌 http://localhost:8080/h2-console
2. Android Studio中的Device File Explorer查看
```
data\data\spring2go.io.authcodeapp\shared_prefs
```