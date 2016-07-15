<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 9/7/16
  Time: 6:50 PM
--%>


<html>
<head>
    <meta name="layout" content="main"/>
    <title>
        Home
    </title>

</head>

<body>
<div class="row">
<div class="col-md-5">
    <g:render template="/templates/userinfoTemplate" model="${user}"></g:render>

<g:if test="${subscriptions.size()}">
    <div class="panel panel-default">
        <div class="panel-heading" style="height:35px">
            <div class="col-md-9">
                Subscriptions
            </div>
            <div class="col-md-3">
                <a href="#">View all</a>
            </div>

        </div>
    <g:render template="/templates/topicTemplate" collection="${subscriptions}" var="topic"></g:render>
    </div>
    </g:if>



    <div class="panel panel-default">
        <div class="panel-heading" style="height:35px">
            <div class="col-md-9">
                Trending Topics
            </div>


        </div>
        <g:render template="/templates/topicTemplate" collection="${topics}" var="topic"></g:render>
    </div>



</div>

<div class="col-md-7">
    <div class="panel panel-default">
        <div class="panel-heading">
            Inbox
        </div>
<g:if test="${readingItems?.size()}">

    <g:render template="/templates/readingItem" collection="${readingItems}" var="ri"></g:render>
</g:if>
<g:else>
    <div class="panel-body">
        Oops,You have nothing in your Inbox. Please subscribe to some topics in order to get items in your inbox
    </div>
</g:else>
    </div>
</div>

</div>


</body>
</html>