<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ckzippo
  Date: 9/14/16
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--从组织架构管理处,点击查看组织架构--%>
<html>
<head>
    <title>增加用户</title>
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
                查看组织架构
            </a>
        </div>
        <h1 align="left">查看组织架构</h1>
    </div>

    <hr>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon">
                            <i class="icon-user"></i>
                        </span>
                        <h5>查看组织架构</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form method="post" name="showDept" id="showDept">
                            <table class="table table-bordered table-hover data-table">
                                <thead>
                                <tr>
                                    <th> </th>
                                    <th>组织架构名称</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${userlist}">
                                    <tr>
                                        <td>${user.userid}</td>
                                        <td>${user.username}</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="deptment" items="${departmentlist}">
                                    <tr>
                                        <td><input type="radio" id="deptid" name="deptid" value="${deptment.deptid}"></td>
                                        <td>${deptment.deptname}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                            <div align="right">
                                <a>
                                    <button class="btn btn-info" onclick="javascript :history.back(-1);">上一层</button>
                                </a>

                                <c:if test="${!empty departmentlist}">
                                    <button class="btn btn-warning" onclick="nextlayer()">下一层</button>
                                </c:if>

                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title">
                        <h5>操作说明</h5>
                    </div>

                    <div class="widget-content">
                        <p>
                        <ol>
                            <li>后台配置了您的权限,您只能修改您权限对应的组织架构</li>
                            <li>选择部门后,点击下一步,会到其子部门</li>
                            <li>当没有下一层节点时,"下一层"按钮会消失</li>
                        </ol>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>

<script language="JavaScript" type="text/javascript">
    <%--下一层组织架构节点--%>
    function nextlayer() {
        var x = document.getElementsByName("deptid");
        var deptid;
        var t = 0;
        for (var i = 0; i < x.length; i++) {
            if (x[i].checked == true) {
                t = 1;
                deptid = x[i].value;
                break;
            }
        }

        if (t != 1) {
            alert("请选择一个部门");
        } else {
            var action = "/dept/showDept?deptid=" + deptid;
            showDept.action = action;
            showDept.submit();
        }
    }

</script>
</body>
</html>
