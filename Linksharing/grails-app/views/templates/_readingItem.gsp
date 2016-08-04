<%@ page import="com.ttnd.linksharing.LinkResource" %>



<div class="panel-body">
    <div>
        <div class="col-md-2">
            <g:if test="${resource.createdBy?.photo}">

                    <a href="user/profile?id=${resource?.createdBy.id}"><img src="user/image/${resource.createdBy.id}"
                    style="width:80px;height:80px; float: left ;margin-right: 40px;"></a>

            </g:if>
            <g:else>
                    <a href="user/profile?id=${resource?.createdBy?.id}"><img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:80px;height:80px;float: left;margin-right: 40px;"></a>
            </g:else>
        </div>
        <div class="col-md-10">
            <div class="row">
            <div class="col-md-6">
            <p>${resource?.createdBy} &nbsp @${resource.createdBy?.username}</p>
                </div>
            <div class="col-md-4 pull-right">
                <g:link controller="topic" action="show" id="${resource.topic.id}">${resource.topic.name}</g:link>
            </div>
            </div>
            <div class="description">

                ${(resource.description.length()>100)?resource.description.substring(0,100):resource.description}

            </div>


            <div class="col-md-2">
                <i class="fa fa-facebook-official social"></i>
                <i class="fa  fa-tumblr social"></i>
                <i class="fa fa-google-plus social"></i>
            </div>

            <div class="col-md-10 resource-actions"   id="${resource.id}" >
                <div class="col-md-2"> <g:if test="${(resource instanceof com.ttnd.linksharing.LinkResourceVO || resource instanceof com.ttnd.linksharing.LinkResource)}"> <a id = "${resource.url}" class="full-site">Full Site</a></g:if><g:else><a href="" class="download">Download</a></div><div class="col-md-2"></g:else></div><g:if test="${session.user != null}"><div class="col-md-4"><a class="mark-read" >Mark as read</a></div></g:if>  <div class="col-md-3"><g:link controller="resource" action="show" id="${resource.id}" >View Post</g:link></div>
            </div>
        </div>

    </div >
</div>