<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 22/7/16
  Time: 1:13 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>User Profile</title>
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

    <div class="panel panel-default">
        <div class="panel-heading">
            Subscriptions
        </div>
        <g:render template="/templates/topicTemplate" collection="${subscribedTopics}" var="topic"></g:render>
    </div>

</div>
<div class="col-md-7">
    <div class="panel panel-default">
        <div class="panel-heading">
            Posts<span style="float:right"><input type="search" name="searchposts" id="searchposts" placeholder="Search" style="border-radius: 4px"> </span>
        </div>
        <g:render template="/templates/readingItem" collection="${posts}" var="resource"></g:render>
    </div>
</div>



</body>
</html>