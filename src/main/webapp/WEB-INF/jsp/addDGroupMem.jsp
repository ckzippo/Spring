<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ckzippo.usermanage.User" %>
<%@ page import="com.ckzippo.Enum.ActionEnum" %>
<%@ page isELIgnored="false" %>

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
    <title>查询用户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-style.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-media.css" />
    <link href="/resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    <script language="JavaScript" type="text/javascript">

        // 增加讨论组成员
        function addMemtoDGroup() {
            var x = document.getElementsByName("id");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            if (t == 1) {
                var dgroupid="${dgroupid}";
                showUser.action = "/dgroup/addDGroupMemInvoke?dgroupid=" + dgroupid;
                showUser.submit();
            } else {
                alert("请选择一个用户");
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
                新增讨论组成员
            </a>
        </div>
        <h1>新增讨论组成员</h1>
    </div>

    <hr>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span4">
                <form action="/dgroup/qryuser">
                    <input type="text" style="height: 30px" name="keyword"><br>
                    <input type="hidden" name="dgroupid" value="${dgroupid}">
                    <button class="btn btn-success">查询</button>
                </form>
                <br>
                <hr>
                <p>
                <ol>
                    <li>输入用户名、账号、手机号、邮箱关键字搜索一个用户</li>
                    <li>关键字尽量输入长一点,以免结果太多,反应慢</li>
                    <li>选择用户后,点击加入到讨论组即可</li>
                </ol>
                </p>
            </div>

            <%
               ArrayList<User> resultList =
                       (ArrayList<User>) request.getSession().getAttribute(ActionEnum.QRYUSER.getActionName());
                if (resultList != null) {
            %>
            <form id="showUser" name="showUser" method="post">
                <div class="span8">
                    <%--<table class="table table-bordered table-striped with-check">--%>
                    <table class="table table-bordered table-hover data-table">
                        <thead>
                        <tr>
                            <th><i class="icon-resize-vertical"></i></th>
                            <th>用户姓名</th>
                            <th>登录账号</th>
                            <th>用户ID</th>
                            <th>手机号</th>
                            <th>部门</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (User user : resultList) {
                        %>
                        <tr>
                            <td><input type="radio" id="id" name="id" value="<%=user.getUserid()%>"></td>
                            <td><%=user.getUsername()%></td>
                            <td><%=user.getUseracc()%></td>
                            <td><%=user.getUserid()%></td>
                            <td><%=user.getMobilephone()%></td>
                            <td><%=user.getDeptname()%></td>
                            <td style="display: none"><input type="hidden" name="acc" value="<%=user.getUseracc()%>"></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <div align="right">
                        <a href="#" onclick="addMemtoDGroup()">
                            <button class="btn btn-success">加入到讨论组</button>
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
