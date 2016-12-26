<%@ page import="com.ckzippo.usermanage.User" %>
<%@ page import="com.ckzippo.util.InvokeHttpUtil" %>
<%@ page import="com.ckzippo.groupmanage.Group" %><%--
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
    String groupid = request.getParameter("id");
    Group group = InvokeHttpUtil.QryGroupByID(groupid);
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
                    <h5>修改群信息</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="/group/modGroupInvoke" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">群名称 :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<%=group.getGp_name()%>"
                                       value="<%=group.getGp_name()%>" name="name"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">群公告:</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<%=group.getNote()%>"
                                       value="<%=group.getNote()%>" name="note"/>
                            </div>
                        </div>

                        <%--隐藏域--%>
                        <input type="hidden" name="flag" value="invoke">
                        <input type="hidden" name="id" value="<%=group.getId()%>">
                        <div class="form-actions" align="right">
                            <button type="submit" class="btn btn-success">更改群信息</button>
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
