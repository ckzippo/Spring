<%--
  Created by IntelliJ IDEA.
  User: ckzippo
  Date: 9/5/16
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>`
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-style.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-media.css" />
    <link href="/resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<body>

    <%--先判断是否登录--%>
    <%
        String name = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        if (name == null || password == null) {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    %>

    <%--header--%>
    <%@include file="header.jsp"%>

    <%--left subbar--%>
    <%@include file="left.jsp"%>

    <%--main content--%>
    <div id="content" >
        <div id="content-header">
            <div id="breadcrumb">
                <a class="tip-bottom" data-original-title="主页" href="#">
                    <i class="icon-home"></i>
                    主页
                </a>
                <a class="current">
                    欢迎
                </a>
            </div>
        </div>
        <div id="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-content">
                        <h1>欢迎使用优信后台管理系统</h1>
                        <p>您可以对个人、群组、组织架构进行修改</p>
                        <p>有任何问题欢迎联系陈江(15301586700)</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp"%>
</body>
</html>
