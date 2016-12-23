<%@ page import="com.ckzippo.usermanage.User" %>
<%@ page import="com.ckzippo.util.InvokeHttpUtil" %><%--
  Created by IntelliJ IDEA.
  User: ckzippo
  Date: 9/8/16
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
</head>
<body>

<%
    String userid = request.getParameter("id");
    User user = InvokeHttpUtil.QryUserById(userid);
%>
<%--header--%>
<%@include file="header.jsp" %>

<%--left subbar--%>
<%@include file="left.jsp" %>

<%--main content--%>
<div id="content">
    <div class="row-fluid">
        <div class="span6">
            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="icon-user"></i>
                    </span>
                    <h5>修改账号信息</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="/user/moduser" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">账号 :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<%=user.getUseracc()%>"
                                       value="<%=user.getUseracc()%>" name="acc"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">姓名:</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<%=user.getUsername()%>"
                                       value="<%=user.getUsername()%>" name="username"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">手机号</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<%=user.getMobilephone()%>"
                                       value="<%=user.getMobilephone()%>" name="mobilephone"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">固定电话 :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<%=user.getTelephone()%>"
                                       value="<%=user.getTelephone()%>" name="telephone"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">邮箱:</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<%=user.getEmail()%>"
                                       value="<%=user.getEmail()%>" name="email"/>
                            </div>
                        </div>
                        <%--隐藏域--%>
                        <input type="hidden" name="flag" value="invoke">
                        <input type="hidden" name="id" value="<%=user.getUserid()%>">
                        <div class="form-actions" align="right">
                            <button type="submit" class="btn btn-success">更改用户</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="span6">
            <div>
                &nbsp;
            </div>
            <div class="widget-title">

            </div>
            <div class="widget-box">
                <div class="widget-content">
                    <p>
                    <h5>注意事项:</h5>
                    <ol>
                        <li>账号字段请勿随意更改,可能跟现有其他账号冲突,导致更改失败</li>
                        <li>姓名字段请勿更改</li>
                    </ol>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<%--TODO 隐藏域内容比较。如果内容未更改,则不提交到服务器修改,改为提示用户--%>
<%@include file="footer.jsp" %>
</body>
</html>
