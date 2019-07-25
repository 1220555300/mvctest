<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/13
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录页</title>
  </head>
  <body>
    <form action="login/auth" method="post">
        username:<input type="text" name="username"><p>
        password:<input type="password" name="password"><p>
        <input type="submit" value="登录">
    </form>
  </body>
</html>
