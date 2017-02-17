<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ckzippo.usermanage.User" %>
<%@ page import="com.ckzippo.Enum.ActionEnum" %>
<%@ page import="com.ckzippo.groupmanage.Group" %>

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
    <title>查询群</title>
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

        // 修改群信息
        function modifyGroupMem() {
            var x = document.getElementsByName("id");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            if (t == 1) {
                showGroup.action = "/group/modGroup";
                showGroup.submit();
            } else {
                alert("请选择一个群");
            }
        }

        // 查看群成员
        function qryGroupMember() {
            var x = document.getElementsByName("id");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            if (t == 1) {
                showGroup.action = "/group/qryGroupMember";
                showGroup.submit();
            } else {
                alert("请选择一个群");
            }
        }

        // 删除群
        function delGroup() {
            var x = document.getElementsByName("id");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            if (t == 1) {
                showGroup.action = "/group/delGroup";
                showGroup.submit();
            } else {
                alert("请选择一个群");
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
                查询群
            </a>
        </div>
        <h1>查询群</h1>
    </div>

    <hr>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span4">
                <form action="/group/qryGroup">
                    <input type="text" style="height: 30px" name="keyword"><br>
                    <button class="btn btn-success">查询群</button>
                </form>
                <br>
                <hr>
                <p>
                <ol>
                    <li>输入群名称关键字进行搜索</li>
                    <li>关键字尽量输入长一点,以免结果太多,反应慢</li>
                    <li>修改群信息可以修改群名称、请公告</li>
                    <li>查看群成员可以展示、新增、修改群成员</li>
                </ol>
                </p>
            </div>

            <jsp:useBean id="qryresult" class="com.ckzippo.groupmanage.Group" scope="page"/>
            <%
               ArrayList<Group> resultList =
                       (ArrayList<Group>) request.getSession().getAttribute(ActionEnum.QRYGROUP.getActionName());
                if (resultList != null) {
            %>
            <form id="showGroup" name="showGroup" method="post">
                <div class="span8">
                    <%--<table class="table table-bordered table-striped with-check">--%>
                    <table class="table table-bordered table-hover data-table">
                        <thead>
                        <tr>
                            <th><i class="icon-resize-vertical"></i></th>
                            <th>群ID</th>
                            <th class="span4">群名称</th>
                            <th class="span4">群公告</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (Group group : resultList) {
                        %>
                        <tr>
                            <td><input type="radio" id="id" name="id" value="<%=group.getId()%>"></td>
                            <td><%=group.getId()%></td>
                            <td><%=group.getGp_name()%></td>
                            <td><%=group.getNote()%></td>
                            <td style="display: none"><input type="hidden" name="acc" value="<%=group.getId()%>"></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <div align="right">
                        <a href="#" onclick="modifyGroupMem()">
                            <button class="btn btn-success">修改群信息</button>
                        </a>
                        <a href="#" onclick="qryGroupMember()">
                            <button class="btn btn-info">查看群成员</button>
                        </a>
                        <a href="#" onclick="delGroup()">
                            <button class="btn btn-danger">删除群</button>
                        </a>
                    </div>
                </div>
            </form>
            <%
                }
                request.getSession().getAttribute(ActionEnum.QRYGROUP.getActionName());
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
