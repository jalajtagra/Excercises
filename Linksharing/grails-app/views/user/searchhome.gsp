<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 29/7/16
  Time: 5:28 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
    <asset:javascript src="searchhome.js"/>
</head>

<body>

<div class="col-md-5">
    <div class="panel panel-default">
        <div class="panel-heading" style="height:35px">
            <div class="col-md-9">
                Topic Results
            </div>
        </div>

        <div class="topics">
            <g:render template="/templates/topicTemplate" collection="${topics}" var="topic"></g:render>
        </div>

    </div>
    <div class="pagination" id="topics-pagination">
        <a href="#" class="first" data-action="first">&laquo;</a>
        <a href="#" class="previous" data-action="previous">&lsaquo;</a>
        <input type="text" readonly="readonly" data-max-page="${(int)Math.ceil(topicsCount/5)}"/>
        <a href="#" class="next" data-action="next">&rsaquo;</a>
        <a href="#" class="last" data-action="last">&raquo;</a>
    </div>
</div>

<div class="col-md-7">
    <div class="panel panel-default">
        <div class="panel-heading" style="height:35px">
            <div class="col-md-9">
                Posts Results
            </div>
        </div>
        <div class="posts">
            <g:render template="/templates/readingItem" collection="${posts}" var="resource" id="inbox-items"></g:render>
        </div>

    </div>
    <div class="pagination" id="posts-pagination">
        <a href="#" class="first" data-action="first">&laquo;</a>
        <a href="#" class="previous" data-action="previous">&lsaquo;</a>
        <input type="text" readonly="readonly" data-max-page="${(int)Math.ceil(postCount/5)}"/>
        <a href="#" class="next" data-action="next">&rsaquo;</a>
        <a href="#" class="last" data-action="last">&raquo;</a>
    </div>
</div>

</body>
</html>