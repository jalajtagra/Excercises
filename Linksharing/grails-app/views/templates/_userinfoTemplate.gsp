<div class="panel panel-default">
    <div class="panel-content">
        <img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:150px;height:150px;float: left;margin-right: 40px;padding:10px;">
        <p style="padding-top:20px; "> ${user.name}</p>
        <p style="color: gray">@${user.username}</p>
        <table style="padding-left: 20px;margin-top:30px;margin-bottom: 10px;">
            <tr>
                <td style="padding-right:30px">Subscriptions</td>
                <td>Posts</td>
            </tr>
            <tr>
                <td><p style="color:lightblue">${user?.subscriptions?.size()}</p> </td>
                <td><p style="color:lightblue" >${user?.resources?.size()}</p> </td>
            </tr>
        </table>
    </div>
</div>