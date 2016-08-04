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
    <asset:javascript src="resourcehome.js"/>
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

                        <g:if test="${session.user != null}"> <div class="rating row pull-right" rating="${ratingInfo.avgScore}" id="${resource.id}"><i class="fa fa-heart-o "></i><i class="fa fa-heart-o"></i><i class="fa fa-heart-o"></i><i class="fa fa-heart-o"></i><i class="fa fa-heart-o"></i>By <span class="totalVotes">${ratingInfo.totalVotes}</span> user(s)</div></g:if>
                        <div class="">
                            ${resource.description}
                        </div>
                        <div>

                        </div>

                        <div class="col-md-2">
                            <i class="fa fa-facebook-official"></i>
                            <i class="fa  fa-tumblr"></i>
                            <i class="fa fa-google-plus"></i>
                        </div>

                        <div class="col-md-10 resource-actions" id ="${resource.id}">
                            <g:if test="${session.user?.id == resource?.createdBy?.id}"><a href="resource/delete?id=${resource.id}">Delete</a>&nbsp&nbsp&nbsp<a class="edit-description" >Edit</a>&nbsp&nbsp&nbsp</g:if><g:if test="${(resource instanceof com.ttnd.linksharing.DocumentResourceVO || resource instanceof com.ttnd.linksharing.DocumentResource)}"> <a class="download">Download</a></g:if><g:else><a id="${resource.url}" class="full-site">Full Site</a></g:else><g:if test="${session.user != null}"><div class="col-md-4"></div></g:if>  <div class="col-md-3"></div>
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



%{--<--------------------------------------------------Modal------------------------------------------------------------------>--}%


<div id="updateDescription" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Update Description</h4>
            </div>

            <div class="modal-body">
                <div class="panel panel-default">
                    <!--<div class="panel-heading">
                            Send Invitation
                        </div>-->
                    <div class="panel-body">
                        <form class="form-horizontal" role="form" id="update-description-form" action="resource/updateDescription" method="POST">
                            <div class="form-group" style = "padding-left: 5px;padding-right:5px" >
                                <label class = "control-label col-md-4" for="description" style = "padding: 5px ; text-align: left">Description:</label>
                                <div class="col-md-8">
                                    <textarea  class="form-control" id="description" name="description" style = "padding: 5px">${resource.description}</textarea>
                                </div>
                            </div>
                            <input type="hidden" value="${resource.id}" name="id">

                            <button class="button button-default" type="submit" style="margin-left:190px">Submit</button>
                            %{--<input type="button" class="button button-default" value="Invite" type="submit"  >--}%
                            %{--<input type="button" class="button button-default" value="Cancel" type="reset">--}%
                            <button class="button button-default cancel" type="button" data-toggle="modal" data-target="#updateDescription">Cancel</button>
                            <button class="button button-default reset" type="reset" hidden></button>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>



</body>
</html>



