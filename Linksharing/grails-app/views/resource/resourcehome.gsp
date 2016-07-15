<%--
  Created by IntelliJ IDEA.
  User: ttnd
  Date: 12/7/16
  Time: 6:52 AM
--%>

<html>
<head>
    <title></title>
    <meta name="layout" content="main"/>
</head>

<body>
    <div class="col-md-7">
        <div class="panel panel-default">
            <div class="panel-body">
                <div>
                    <div class="col-md-2">
                        <img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:80px;height:80px;float: left;margin-right: 40px;">
                    </div>
                    <div class="col-md-10">
                        <p>${resource.createdBy} &nbsp @${resource.createdBy}</p>
                        <div class="">
                            ${resource.description}
                        </div>

                        <div class="col-md-2">
                            <i class="fa fa-facebook-official"></i>
                            <i class="fa  fa-tumblr"></i>
                            <i class="fa fa-google-plus"></i>
                        </div>

                        <div class="col-md-10">
                            <g:if test="${session.user?.username == resource?.createdBy?.username}"><a href="#">Delete</a>&nbsp&nbsp&nbsp<a href="#">Edit</a>&nbsp&nbsp&nbsp</g:if><a href="#">Download</a>&nbsp&nbsp&nbsp<a href="#">Full Site</a>&nbsp&nbsp&nbsp
                        </div>
                    </div>

                </div >
            </div>
        </div>
    </div>

    <div class="col-md-5">
        <div class="panel panel-default">
            <div class="panel-heading" style="height:35px">
                <div class="col-md-9">
                    Trending Topics
                </div>


            </div>
            <g:render template="/templates/topicTemplate" collection="${topics}" var="topic"></g:render>
        </div>
    </div>
</body>
</html>