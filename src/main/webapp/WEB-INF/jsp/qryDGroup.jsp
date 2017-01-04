<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ckzippo.usermanage.User" %>
<%@ page import="com.ckzippo.Enum.ActionEnum" %>
<%@ page import="com.ckzippo.groupmanage.Group" %>
<%@ page import="com.ckzippo.dgpmanage.DGroup" %>

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
    <title>查询讨论组</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-style.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-media.css" />
    <link href="/resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    <script language="JavaScript" type="text/javascript">

        // 查看讨论组成员
        function qryDGroupMember() {
            var x = document.getElementsByName("id");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            if (t == 1) {
                showDGroup.action = "/dgroup/qryDGroupMember";
                showDGroup.submit();
            } else {
                alert("请选择一个讨论组");
            }
        }

        // 删除讨论组
        function delDGroup() {
            var x = document.getElementsByName("id");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            if (t == 1) {
                showDGroup.action = "/group/delDGroup";
                showDGroup.submit();
            } else {
                alert("请选择一个讨论组");
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
                查询讨论组
            </a>
        </div>
        <h1>查询讨论组</h1>
    </div>

    <hr>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span4">
                <form action="/dgroup/qryDGroup">
                    <input type="text" style="height: 30px" name="keyword"><br>
                    <button class="btn btn-success">查询讨论组</button>
                </form>
                <br>
                <hr>
                <p>
                <ol>
                    <li>输入讨论组名称关键字进行搜索</li>
                    <li>关键字尽量输入长一点,以免结果太多,反应慢</li>
                    <li>查看讨论组成员可以展示、新增、修改讨论组成员</li>
                </ol>
                </p>
            </div>

            <jsp:useBean id="qryresult" class="com.ckzippo.dgpmanage.DGroup" scope="page"/>
            <%
               ArrayList<DGroup> resultList =
                       (ArrayList<DGroup>) request.getSession().getAttribute(ActionEnum.QRYDGROUP.getActionName());
                if (resultList != null) {
            %>
            <form id="showDGroup" name="showDGroup" method="post">
                <div class="span8">
                    <%--<table class="table table-bordered table-striped with-check">--%>
                    <table class="table table-bordered table-hover data-table">
                        <thead>
                        <tr>
                            <th><i class="icon-resize-vertical"></i></th>
                            <th>讨论组ID</th>
                            <th class="span6">讨论组名称</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (DGroup dgroup : resultList) {
                        %>
                        <tr>
                            <td><input type="radio" id="id" name="id" value="<%=dgroup.getDgpid()%>"></td>
                            <td><%=dgroup.getDgpid()%></td>
                            <td><%=dgroup.getDgpname()%></td>
                            <td style="display: none"><input type="hidden" name="acc" value="<%=dgroup.getDgpid()%>"></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <div align="right">
                        <a href="#" onclick="qryDGroupMember()">
                            <button class="btn btn-info">查看讨论组成员</button>
                        </a>
                        <a href="#" onclick="delDGroup()">
                            <button class="btn btn-danger">删除讨论组</button>
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
