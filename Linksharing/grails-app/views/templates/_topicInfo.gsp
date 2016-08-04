    <div class="panel-body">
        <img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:150px;height:150px;float: left;margin-right: 40px;padding:10px;">
        <p style="padding-top:20px; "> ${topic.name}(${topic.visibility})</p>

        <table style="padding-left: 20px;margin-top:30px;margin-bottom: 10px;">
            <% com.ttnd.linksharing.Subscription sub = topic.subscriptions.find {
                it.user.id == session.user?.id
            } %>
            <tr>
                <td style="padding-right:30px"><p style="color: gray">@${topic.createdBy.username}</p></td>
                <td >Subscriptions</td>
                <td>Posts</td>
            </tr>
            <tr>
                <g:if test="${session.user != null}"> <td>
                    <g:if test="${sub}">

                        <a class="unsubscribe" id="${topic.id}">Unsubscribe</a>
                    </g:if>
                    <g:else>
                        <a class="subscribe" id="${topic.id}">Subscribe</a>
                    </g:else>
                </td></g:if>
                <td><a href="#">${topic.subscriptions.size()}</a> </td>
                <td><g:link controller="topic" action="resources" id="${topic.id}" >${resources.size()}</g:link> </td>
            </tr>
        </table>
    </div>