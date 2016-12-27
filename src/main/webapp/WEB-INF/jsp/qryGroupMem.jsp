<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ckzippo.usermanage.User" %>
<%@ page import="com.ckzippo.Enum.ActionEnum" %>
<%@ page import="com.ckzippo.groupmanage.GroupMember" %>
<%@ page import="java.util.LinkedList" %>

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
    <title>查询群成员</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-style.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-media.css" />
    <link href="/resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    <%--修改用户、重置密码、增加建群权限的JS代码--%>
    <script language="JavaScript" type="text/javascript">

        // 修改用户
        function addGroupMember() {
            if (t == 1) {
                showGroupmem.action = "/user/moduserFromadd";
                showGroupmem.submit();
            } else {
                alert("请选择一个用户");
            }
        }

        // 删除群成员
        function delGroupMember() {
            var x = document.getElementsByName("memberid");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            var groupid = document.getElementsByName("groupid");

            if (t == 1) {
                showGroupmem.action = "/group/delGroupMember";
                showGroupmem.submit();
            } else {
                alert("请选择一个群成员");
            }
        }
    </script>
</head>
<body>
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
                查看群成员
            </a>
        </div>
        <h1>查看群成员</h1>
    </div>

    <hr>

    <div class="container-fluid">
        <div class="row-fluid">
            <jsp:useBean id="result" class="com.ckzippo.groupmanage.GroupMember" scope="page"/>
            <%
                LinkedList<GroupMember> groupMembers =
                        (LinkedList<GroupMember>)request.getSession().getAttribute(ActionEnum.QRYGRPMEM.getActionName());
                if (groupMembers != null) {
            %>
            <form id="showGroupmem" name="showGroupmem" method="post">
                <div class="span6" align="center">
                    <table class="table table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><i class="icon-resize-vertical"></i></th>
                                <th>用户姓名</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (GroupMember groupmem :
                                        groupMembers) {

                            %>
                                <tr>
                                    <td class="span2"><input type="radio" id="memberid" name="memberid" value="<%=groupmem.getMemberid()%>"></td>
                                    <td class="span4"><%=groupmem.getMembername()%></td>
                                    <td style="display: none"><input type="hidden" name="groupid" value="<%=groupmem.getGroupid()%>"></td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>

                    <div align="right">
                        <a href="#" onclick="addGroupMember()">
                            <button class="btn btn-info">增加群成员</button>
                        </a>
                        <a href="#" onclick="delGroupMember()">
                            <button class="btn btn-danger">删除群成员</button>
                        </a>
                    </div>
                </div>
            </form>
            <%
                }
            %>
    </div>
</div>
</div>

<%@include file="footer.jsp"%>

<%--去除session--%>
<%--<%--%>
<%--request.getSession().removeAttribute(ActionEnum.QRYUSER.toString());--%>
<%--%>--%>
</body>
</html>
