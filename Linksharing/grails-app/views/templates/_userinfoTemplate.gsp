
    <div class="panel-content">
        %{--<img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:150px;height:150px;float: left;margin-right: 40px;padding:10px;">--}%
        <g:if test="${user.photo}">
            <a href="user/profile?id=${session.user?.id}"> <img src="user/image?id=${user?.id}" style="width:150px;height:150px;float: left;margin-right: 40px;padding:10px;"></a>

        </g:if>
        <g:else>
            <a href="user/profile?id=${session.user?.id}"><img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:150px;height:150px;float: left;margin-right: 40px;padding:10px;"></a>
        </g:else>
        <p style="padding-top:20px; "> ${user.name}</p>
        <p style="color: gray">@${user.username}</p>
        <table style="padding-left: 20px;margin-top:30px;margin-bottom: 10px;">
            <tr>
                <td style="padding-right:30px">Subscriptions</td>
                <td>Posts</td>
            </tr>
            <tr>
                <td><p style="color:lightblue">${allSubscriptions?.size()}</p> </td>
                <td><p style="color:lightblue" >${resourceCount}</p> </td>
            </tr>
        </table>
    </div>
