<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 11/7/16
  Time: 6:02 PM
--%>


<html>
<head>
    <title></title>
    <meta name="layout" content="main"/>

</head>

<body>
<div class="col-md-5">
<div class="panel panel-default">
    <div class="panel-heading">
        Topic : "${topic.name}"
    </div>
    <g:render template="/templates/topicInfo" model="${topic}"></g:render>
</div>



    <div class="panel panel-default">
        <div class="panel-heading" style="height:35px">
            <div class="col-md-9">
                Users :"${topic.name}"
            </div>
            <div class="col-md-3">
                <a href="#">View all</a>
            </div>

        </div>
        <g:render template="/templates/userinfoTemplate" collection="${subscribingUsers}" var="user"></g:render>
    </div>
</div>
<div class="col-md-7">
    <div class="panel panel-default">
        <div class="panel-heading">
            Posts : "${topic.name}"
        </div>
        <g:render template="/templates/resourceView" collection="${resources}" var="resource"></g:render>
</div>
</body>
</html>