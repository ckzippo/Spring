<%--
  Created by IntelliJ IDEA.
  User: ckzippo
  Date: 9/5/16
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作失败</title>
</head>
<body>
<%@include file="header.jsp" %>


<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom">
            <i class="icon-home"></i> Home</a>
            <h1>出错了</h1>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-info-sign"></i> </span>
                        <h5>操作失败</h5>
                    </div>
                    <div class="widget-content">
                        <div class="error_ex">
                            <h1>417</h1>
                            <h3>密码错误</h3>
                            <%--<a class="btn btn-warning btn-big" href="main.jsp">Back to Home</a></div>--%>
                            <a class="btn btn-warning btn-big" onclick="location.href='javascript:history.go(-1)'">Back</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
