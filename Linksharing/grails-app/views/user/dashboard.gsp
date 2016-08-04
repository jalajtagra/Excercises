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
    <asset:javascript src="dashboard.js"/>

</head>

<body>
<div class="row">
<div class="col-md-5">
    <div class="panel panel-default">
    <g:render template="/templates/userinfoTemplate" model="${user}"></g:render>
    </div>
<g:if test="${subscriptions.size()}">
    <div class="panel panel-default">
        <div class="panel-heading" style="height:35px">
            <div class="col-md-9">
                Subscriptions
            </div>
           %{-- <div class="col-md-3">
                <a href="#">View all</a>
            </div>--}%

        </div>
        <div class="list">
    <g:render template="/templates/topicTemplate" collection="${subscriptions}" var="topic"></g:render>
        </div>
        <div class="pagination" id="subscriptions-pagination">
            <a href="#" class="first" data-action="first">&laquo;</a>
            <a href="#" class="previous" data-action="previous">&lsaquo;</a>
            <input type="text" readonly="readonly" data-max-page="${(int)Math.ceil(allSubscriptions.size()/5)}"/>
            <a href="#" class="next" data-action="next">&rsaquo;</a>
            <a href="#" class="last" data-action="last">&raquo;</a>
        </div>
    </div>

    </g:if>



    <div class="panel panel-default " id="trending-topics">
        <div class="panel-heading" style="height:35px">
            <div class="col-md-9">
                Trending Topics
            </div>
        </div>
        <div class="list">
            <g:render template="/templates/topicTemplate" collection="${topics}" var="topic"></g:render>
        </div>
        <div class="pagination" id="trending-topic-pagination">
            <a href="#" class="first" data-action="first">&laquo;</a>
            <a href="#" class="previous" data-action="previous">&lsaquo;</a>
            <input type="text" readonly="readonly" data-max-page="${(int)Math.ceil(topicsCount/5)}"/>
            <a href="#" class="next" data-action="next">&rsaquo;</a>
            <a href="#" class="last" data-action="last">&raquo;</a>
        </div>

    </div>



</div>

<div class="col-md-7">
    <div class="panel panel-default">
        <div class="panel-heading">
            Inbox<span style="float:right"><input type="search" name="searchposts" id="searchposts" placeholder="Search" style="border-radius: 4px"> </span>
        </div>
        <g:if test="${readingItems?.size()}">
            <div class="inbox-list">
                <g:render template="/templates/readingItem" collection="${readingItems}" var="resource" id="inbox-items"></g:render>
            </div>
        </g:if>
        <g:else>
            <div class="panel-body">
                Oops,You have nothing in your Inbox. Please subscribe to some topics in order to get items in your inbox
            </div>
        </g:else>
        <div class="pagination" id="inbox">
            <a href="#" class="first" data-action="first">&laquo;</a>
            <a href="#" class="previous" data-action="previous">&lsaquo;</a>
            <input type="text" readonly="readonly" data-max-page="${(int)Math.ceil(unreadResourcesCount/5)}"/>
            <a href="#" class="next" data-action="next">&rsaquo;</a>
            <a href="#" class="last" data-action="last">&raquo;</a>
        </div>

    </div>
    %{--<div class="panel panel-default" id="ajax_wrap">
        <div class="panel-heading">
            Top Topics
        </div>
        <div class="list">
            <g:render template="/templates/topicTemplate" collection="${topics}" var="topic"/>
        </div>

        <div class="paginateButtons">
      --}%%{--  <g:paginate total="${topicsCount}" controller="user" action="trendingTopics" max="5">
            --}%%{----}%%{--<custom:observe classes="${['step','prevLink','nextLink']}" event="click" function="clickPaginate"/>--}%%{----}%%{--
        </g:paginate>--}%%{--

        </div>
    </div>--}%

</div>

</div>


</body>
</html>