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
            <p>${resource.createdBy} &nbsp @${resource.createdBy.username}</p>
            <div class="">
                ${resource.description}
            </div>

            <div class="col-md-3">
                <i class="fa fa-facebook-official"></i>
                <i class="fa  fa-tumblr"></i>
                <i class="fa fa-google-plus"></i>
            </div>

            <div class="col-md-9 resource-actions" id="${resource.id}">
                %{--<a href="#">Download</a>&nbsp&nbsp&nbsp<a href="#">Full Site</a>&nbsp&nbsp&nbsp<a href="#">Mark as read</a>&nbsp&nbsp&nbsp<g:link controller="resource" action="show" id="${resource.id}" >View Post</g:link>--}%
            <div class="col-md-2"> <g:if test="${(resource instanceof com.ttnd.linksharing.LinkResourceVO || resource instanceof com.ttnd.linksharing.LinkResource)}"> <a href="" id = "${resource.url}" class="full-site">Full Site</a></g:if><g:else><a href="" class="download">Download</a></div><div class="col-md-2"></g:else></div><g:if test="${session.user != null}"><div class="col-md-4"><a class="mark-read" >Mark as read</a></div></g:if>  <div class="col-md-3"><g:link controller="resource" action="show" id="${resource.id}" >View Post</g:link></div>
            </div>
        </div>

    </div >
</div>