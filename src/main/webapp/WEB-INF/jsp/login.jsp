<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<div style="width:500px;margin:20px auto;text-align: center">
    <form action="verificationUser" method="post">
      
    账号: <input name="name"> <br>
    密码: <input name="password" type="password"> <br>
    <button type="submit">提交</button>
    <a href="register">注册</a>
    </form>
</div>