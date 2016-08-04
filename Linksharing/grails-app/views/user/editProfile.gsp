<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 25/7/16
  Time: 4:12 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
    <asset:javascript src="editprofile.js"/>
</head>

<body>
<div class="col-md-5">
    <div class="panel panel-default">
        <div class="panel-heading">
            ${user.name}
        </div>
        <g:render template="/templates/userinfoTemplate" model="${user}"></g:render>

    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            Topics
        </div>
        <g:render template="/templates/topicTemplate" collection="${topics}" var="topic"></g:render>
    </div>

</div>

<div class="col-md-7">
    <div class="panel panel-default">

        %{--------------------------------------------------Edit User Details Form------------------------------------------}%

        <div class="panel-heading">
           Profile
        </div>

        <div class="panel-body">

            <g:hasErrors bean="${userCommand}">
                <div class="error">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${userCommand}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                </div>
                </ul>
            </g:hasErrors>
            <form class="form-horizontal" role="form" id="updateProfile" action="user/updateProfile" method="POST" enctype="multipart/form-data">
                <div class="form-group ${userCommand?.errors?.getFieldError('firstName')?'has-error':''}" style = "margin-right:0px" >
                    <label class = "control-label col-sm-5" for="fname" style = "text-align: left; padding-left:30px">First name * : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="text" class="form-control" id="fname" name='firstName' style = "padding: 5px" value="${userCommand?.firstName}">
                    </div>
                </div>
                <div class="form-group ${userCommand?.errors?.getFieldError('lastName')?'has-error':''}" style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="lname" style = "text-align: left; padding-left:30px">Last name * : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="text" class="form-control" id="lname" name='lastName' style = "padding: 5px" value="${userCommand?.lastName}">
                    </div>
                </div>
                <div class="form-group ${userCommand?.errors?.getFieldError('username')?'has-error':''}" style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="uname" style = "text-align: left; padding-left:30px">Username * : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="text" class="form-control" id="uname" name='username' style = "padding: 5px" value="${userCommand?.username}">
                    </div>
                </div>

                <div class="form-group" style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="photo" style = "text-align: left; padding-left:30px">Photo : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="file"  id="photo" name='profilePhoto' style = "padding: 5px" >
                    </div>
                </div>
                <button type="submit" class="btn btn-default" style = "margin: 5px; float:right ">Update</button>

            </form>
        </div>
    </div>
        %{--------------------------------------------------Change Password Form-------------------------------------------------------}%
        <br>
    <div class="panel panel-default">
        <div class="panel-heading">
            Change Password
        </div>
        <div class="panel-body">
            <form class="form-horizontal" role="form" id="changePassword" action="user/changePassword" method="POST" enctype="multipart/form-data">
                <div class="form-group ${userCommand?.errors?.getFieldError('password')?'has-error':''}" style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="pwdreg" style = "text-align: left; padding-left:30px">Password * : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="password" class="form-control" id="pwdreg" name='password' style = "padding: 5px" value="${userCommand?.password}">
                    </div>
                </div>
                <div class="form-group" ${userCommand?.errors?.getFieldError('confirmPassword')?'has-error':''} style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="cnfpwd" style = "text-align: left; padding-left:30px">Confirm Password * : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="password" class="form-control" id="cnfpwd" name='confirmPassword' style = "padding: 5px; " value="${userCommand?.confirmPassword}">
                    </div>
                </div>
                <button type="submit" class="btn btn-default" style = "margin: 5px ;float:right ">Update</button>
             </form>
        </div>
        </div>
</div>
</div>
</body>
</html>