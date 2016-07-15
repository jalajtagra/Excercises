<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 12/7/16
  Time: 11:34 AM
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Login</title>

    %{--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>--}%
</head>

<body>


<div class="col-md-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            Recent Shares
        </div>
        <g:render template="/templates/resourceView" collection="${resources}" var="resource"></g:render>


    </div>

    <div class="panel panel-default">
        <div class="panel-heading" style="overflow: hidden">
            <div class="col-md-8" >
                Top Posts
            </div>
            <div class="col-md-4">
                <div class="dropdown">
                    <button class=" dropdown-toggle" type="button" data-toggle="dropdown">Today
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="#">1 Week</a></li>
                        <li><a href="#">1 Month</a></li>
                        <!--<li><a href="#">JavaScript</a></li>-->
                    </ul>
                </div>
            </div>
        </div>
        <g:render template="/templates/resourceView" collection="${recentPosts}" var="resource"></g:render>
    </div>

</div>
<div class="col-md-1"></div>
<div class="col-md-4">
    <div class="panel panel-default">
        <div class="panel-heading">
            Login
        </div>
        <div class="panel-body">
            <form class= "form-horizontal" role="form" action="login/loginhandler">

                <div class="form-group" style = "padding-left: 5px;padding-right:5px" >
                    <label class = "control-label col-md-4" for="email" style = "padding: 5px ; text-align: left">Email/Username:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control " name="username" id="email" style = "padding: 5px">
                    </div>
                </div>
                <div class="form-group" style = "padding-left: 5px;padding-right:5px" >
                    <label class= "control-label col-md-4" for="pwd" style = "padding: 5px;text-align: left">Password:</label>
                    <div class="col-md-8">
                        <input type="password" class="form-control" id="pwd" name="password" style = "padding: 5px">
                    </div>
                </div>
                <div class="col-md-4"></div>
                <div classs="col-md-8">
                    <a style = "padding: 5px;align-items: center">Forgot Password</a>
                    <button type="submit" class="btn btn-default" style = "margin: 5px ">Login</button>

                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            Register
        </div>
        <div class="panel-body">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userCommand}">
                <div class="error">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${userCommand}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                </div>
                </ul>
            </g:hasErrors>

            <form class="form-horizontal" role="form" action="user/registeruser" method="POST">
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
                <div class="form-group ${userCommand?.errors?.getFieldError('email')?'has-error':''}" style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="emailreg" style = "text-align: left; padding-left:30px">Email * : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="text" class="form-control" id="emailreg" name='email' style = "padding: 5px" value="${userCommand?.email}">
                    </div>
                </div>
                <div class="form-group ${userCommand?.errors?.getFieldError('username')?'has-error':''}" style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="uname" style = "text-align: left; padding-left:30px">Username * : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="text" class="form-control" id="uname" name='username' style = "padding: 5px" value="${userCommand?.username}">
                    </div>
                </div>
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
                <div class="form-group" style = "margin-right:0px">
                    <label class = "control-label col-sm-5" for="photo" style = "text-align: left; padding-left:30px">Photo : </label>
                    <div class="form-group col-sm-7" style = "padding-left: 5px;padding-right:5px" >
                        <input type="file"  id="photo" name='photo' style = "padding: 5px" >
                    </div>
                </div>
                <button type="submit" class="btn btn-default" style = "margin: 5px ">Register</button>

            </form>
        </div>
    </div>
</div>
%{--<style>
.topsearch{
    margin-top:20px;
    float:left;
}
</style>--}%
</body>
</html>