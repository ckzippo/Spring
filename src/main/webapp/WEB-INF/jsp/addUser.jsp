<%--
  Created by IntelliJ IDEA.
  User: ckzippo
  Date: 9/20/16
  Time: 9:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <%@include file="left.jsp"%>

    <div id="content">

        <div id="content-header">
            <div id="breadcrumb">
                <a class="tip-bottom" data-original-title="主页" href="#">
                    <i class="icon-home"></i>
                    主页
                </a>
                <a class="current">
                    增加用户
                </a>
            </div>
            <h1 align="left">增加用户</h1>
        </div>
        <hr>

        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
                            <span class="icon"><i class="icon-user"></i></span>
                            <h5>增加用户</h5>
                        </div>

                        <%--TODO 自动验证各个字段是否非空--%>
                        <div class="widget-content">
                            <form action="/user/addUserinvoke" method="post" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label">账号 :</label>
                                    <div class="controls">
                                        <input type="text" class="span11" name="useracc"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">姓名:</label>
                                    <div class="controls">
                                        <input type="text" class="span11" name="username"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">手机号</label>
                                    <div class="controls">
                                        <input type="text" class="span11"  name="mobilephone"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">固定电话 :</label>
                                    <div class="controls">
                                        <input type="text" class="span11" name="telephone"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">邮箱:</label>
                                    <div class="controls">
                                        <input type="text" class="span11" name="email"/>
                                    </div>
                                </div>

                                <%--隐藏域--%>
                                <input type="hidden" name="deptid" value="<%=request.getParameter("deptid")%>">

                                <div class="form-actions" align="right">
                                    <button type="submit" class="btn btn-success">新增用户</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp"%>
</body>
</html>
