<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 1/8/16
  Time: 12:58 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Users</title>
    <asset:javascript src="users.js"/>
</head>

<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">
                Users
            </div>
            <form action="admin/usersPaginate" id="users-form">
            <div class="col-md-6 row">
                <div class="col-md-4">
                    <select name="filter" id="filter">
                        <option value="all">All Users</option>
                        <option value="active">Active</option>
                        <option value="inactive ">Inactive</option>
                    </select>
                </div>
                <div class="col-md-8">
                    <input type="hidden" name="max" value="5">
                    <input type="hidden" name="offset" value="0">

                        <div class="col-md-6">
                            <input type="text" placeholder="Search" name="searchString" >
                        </div>
                        <div class="col-md-6 pull-right" >
                            <button type="submit">Submit</button>
                        </div>

                </div>
            </div>
            </form>
        </div>
    </div>
    <div class="panel-body users-panel-body" style="margin:0px !important;padding: 0px !important">
        <table class="table table-bordered" style="margin:0px !important;padding: 0px !important">
            <thead>
            <tr>
                <th><span class="caret"></span>Id</th>
                <th><span class="caret"></span>Username</th>
                <th><span class="caret"></span>Email</th>
                <th><span class="caret"></span>Firstname</th>
                <th><span class="caret"></span>Lastname</th>
                <th><span class="caret"></span>Active</th>
                <th><span class="caret"></span>Manage</th>
            </tr>
            </thead>
            <tbody class="users-list">
            <g:each in="${users}" var="user">
                <tr class="user-row" >
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.active}</td>
                    <td id="${user.id}" class="activate"><span class="span-mouseover">${user.active==false?"Activate":"Deactivate"}</span></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</div>
<div class="pagination" id="users-pagination" style="margin-left: 40% !important;">
    <a href="#" class="first" data-action="first">&laquo;</a>
    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
    <input type="text" readonly="readonly" data-max-page="${(int)Math.ceil(totalUsers/5)}"/>
    <a href="#" class="next" data-action="next">&rsaquo;</a>
    <a href="#" class="last" data-action="last">&raquo;</a>
</div>
</body>
</html>