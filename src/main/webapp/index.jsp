<%--
  Created by IntelliJ IDEA.
  User: chencong
  Date: 2017/5/24
  Time: 18:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件测试</title>
</head>
<body>
<h3>现在开始测试SpringMVC上传文件测试</h3>
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springMVC上传文件">
</form>
富文本simditor中图片上传
<form action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="SpringMVC文件上传">
</form>
</body>
</html>
