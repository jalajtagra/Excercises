<div class="panel-body">
    <div>
        <div class="col-md-2">
            <img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:80px;height:80px;float: left;margin-right: 40px;">
        </div>
        <div class="col-md-10">
            <p>${resource.createdBy} &nbsp @${resource.createdBy.username}</p>
            <div class="">
                ${resource.description}
            </div>

            <div class="col-md-2">
                <i class="fa fa-facebook-official"></i>
                <i class="fa  fa-tumblr"></i>
                <i class="fa fa-google-plus"></i>
            </div>

            <div class="col-md-10 pull-right">
                <g:if test="${session.user!=null}"><g:if test="${session.user.id==resource.createdBy.id}"><a href="#">Delete</a>&nbsp&nbsp&nbsp<a href="#">Edit</a></g:if>&nbsp&nbsp&nbsp<a href="#">Download</a>&nbsp&nbsp&nbsp<a href="#">Full Site</a>&nbsp&nbsp&nbsp<a href="#">Mark as read</a></g:if>&nbsp&nbsp&nbsp<g:link controller="resource" action="show" id="${resource.id}" >View Post</g:link>
            </div>
        </div>

    </div >
</div>