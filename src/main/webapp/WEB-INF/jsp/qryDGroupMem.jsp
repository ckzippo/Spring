<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ckzippo.usermanage.User" %>
<%@ page import="com.ckzippo.Enum.ActionEnum" %>
<%@ page import="com.ckzippo.groupmanage.GroupMember" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.ckzippo.dgpmanage.DGroupMember" %>

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
    <title>查询讨论组成员</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-style.css" />
    <link rel="stylesheet" href="/resources/css/matrix/matrix-media.css" />
    <link href="/resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    <script language="JavaScript" type="text/javascript">

        // 删除讨论组成员
        function delDGroupMember() {

            var x = document.getElementsByName("memberid");
            var t = 0;
            for (var i = 0; i < x.length; i++) {
                if (x[i].checked == true) {
                    t = 1;
                    break;
                }
            }

            if (t == 1) {
                showDGroupmem.action = "/group/delDGroupMember";
                showDGroupmem.submit();
            } else {
                alert("请选择一个群成员");
            }
        }

        // 新增讨论组成员
        function addDGroupMember() {
            showDGroupmem.action = "/dgroup/addDGroupMember";
            showDGroupmem.submit();
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
                查看讨论组成员
            </a>
        </div>
        <h1>查看讨论组成员</h1>
    </div>

    <hr>

    <div class="container-fluid row-fluid">
        <div class="span4">
            <ol>
                <li>选中一个群成员后,点击删除群成员可以删除群成员</li>
                <li>点击增加群成员后可以跳转到增加群成员页面</li>
            </ol>
        </div>
        <div class="span8" >
            <%
                LinkedList<DGroupMember> dgroupMembers =
                        (LinkedList<DGroupMember>)request.getSession().getAttribute(ActionEnum.QRYDGPMEM.getActionName());
                if (dgroupMembers != null) {
            %>
            <div class="span12">
                <form id="showDGroupmem" name="showDGroupmem" method="post" class="span12">
                <div>
                    <table class="table table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><i class="icon-resize-vertical"></i></th>
                                <th>用户ID</th>
                                <th>用户姓名</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (DGroupMember dgroupmem :
                                        dgroupMembers) {

                            %>
                                <tr>
                                    <td class="span1"><input type="radio" id="memberid" name="memberid" value="<%=dgroupmem.getId()%>"></td>
                                    <td class="span2"><%=dgroupmem.getId()%></td>
                                    <td class="span5"><%=dgroupmem.getName()%></td>
                                    <td style="display: none"><input type="hidden" name="dgroupid" value="<%=dgroupmem.getDgpid()%>"></td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </form>
            </div>
            <div>
                <button class="btn btn-info" onclick="addDGroupMember()">增加讨论组成员</button>
                <button class="btn btn-danger" onclick="delDGroupMember()">删除讨论组成员</button>
            </div>
            <%
                    request.getSession().removeAttribute(ActionEnum.QRYDGPMEM.getActionName());
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
