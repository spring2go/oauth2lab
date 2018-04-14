基于密码模式+Spring Security OAuth2+JWT的最简授权服务器
======

# 操作方式

### 1. 启动jwt-authserver，端口8080

### 2. 启动jwt-resourceserver，端口8081

### 3. 获取JWT令牌

curl -X POST --user clientapp:112233 http://localhost:8080/oauth/token -H "accept: application/json" -H "content-type: application/x-www-formurlencoded" -d "grant_type=password&username=bobo&password=xyz&scope=read_userinfo"

响应案例：

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjM3MDkyNzUsInVzZXJfbmFtZSI6ImJvYm8iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYTAxODc4NmUtY2I5MS00ZGFkLTg4NTUtNDM1ZjQ4ZGI4ZjllIiwiY2xpZW50X2lkIjoiY2xpZW50YXBwIiwic2NvcGUiOlsicmVhZF91c2VyaW5mbyJdfQ.Qa5qPDM866cI9PANyNBU7_8eGXh4-YHYpW2uVIhIbO8",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJib2JvIiwic2NvcGUiOlsicmVhZF91c2VyaW5mbyJdLCJhdGkiOiJhMDE4Nzg2ZS1jYjkxLTRkYWQtODg1NS00MzVmNDhkYjhmOWUiLCJleHAiOjE1MjYyNTgwNzUsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiIxYmEwMTNmYy01YjdjLTRjNmUtYWFhMy01NTQxYTNkMjYwMWIiLCJjbGllbnRfaWQiOiJjbGllbnRhcHAifQ.UMsddrfevWv9K7EQFAei7JrBXTw7jTxsjt2vB-WeWMs",
    "expires_in": 43199,
    "scope": "read_userinfo",
    "jti": "a018786e-cb91-4dad-8855-435f48db8f9e"
}
```

### 4. 调用API

curl -X GET http://localhost:8081/api/userinfo -H "authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjM3MDkyNzUsInVzZXJfbmFtZSI6ImJvYm8iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYTAxODc4NmUtY2I5MS00ZGFkLTg4NTUtNDM1ZjQ4ZGI4ZjllIiwiY2xpZW50X2lkIjoiY2xpZW50YXBwIiwic2NvcGUiOlsicmVhZF91c2VyaW5mbyJdfQ.Qa5qPDM866cI9PANyNBU7_8eGXh4-YHYpW2uVIhIbO8"

案例响应：

```json
{
    "name": "bobo",
    "email": "bobo@spring2go.com"
}
```