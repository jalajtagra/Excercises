


<div class="panel-body">
    <div>
        <div class="col-md-2">
            <img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:80px;height:80px;float: left;margin-right: 40px;">
        </div>
        <div class="col-md-10">
            <p>${ri.resource.createdBy} &nbsp @${user.username}</p>
            <div class="">
                ${ri.resource.description}
            </div>

            <div class="col-md-2">
                <i class="fa fa-facebook-official"></i>
                <i class="fa  fa-tumblr"></i>
                <i class="fa fa-google-plus"></i>
            </div>

            <div class="col-md-10">
                <a href="#">Download</a>&nbsp&nbsp&nbsp<a href="#">Full Site</a>&nbsp&nbsp&nbsp<a href="#">Mark as read</a>&nbsp&nbsp&nbsp<g:link controller="resource" action="show" id="${ri.resource.id}" >View Post</g:link>
            </div>
        </div>

    </div >
</div>