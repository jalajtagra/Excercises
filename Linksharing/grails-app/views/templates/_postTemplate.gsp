<div class="panel-body">
    <div>
        <div class="col-md-2">
            <img src="images/dp.png" style="width:80px;height:80px;float: left;margin-right: 40px;">
        </div>
        <div class="col-md-10">
            <p>${resouce.user.name} &nbsp @${post.user.email}</p>
            <div class="">
                ${resource.description}
            </div>

            <div class="col-md-2">
                <i class="fa fa-facebook-official"></i>
                <i class="fa  fa-tumblr"></i>
                <i class="fa fa-google-plus"></i>
            </div>

            <div class="col-md-offset-8 col-md-4">
                <g:link controller="resource" action="show" id="${resource.id}">Book List</g:link>
            </div>
        </div>

    </div >
</div>