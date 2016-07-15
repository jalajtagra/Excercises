<div class="panel-body">
    <g:if test="${topic.createdBy.photo != null}">
        <custom:userImage userId="${topic.createdBy.id}" height="64" width="64"></custom:userImage>
    </g:if>
    <g:else>
        <img src="${asset.assetPath(src: 'defaults/dp.png')}" style="width:80px;height:80px;float: left;margin-right: 40px;">

    </g:else>

    <div><g:link controller="topic" action="show" id="${topic.id}" >${topic.name}</g:link></div>

    <p style="color:gray">@${topic.createdBy.username} </p>
    <table style="padding-left: 20px;margin-top:30px    ;">
        <tr>
            <td style="padding-right:20px">${topic?.createdBy?.username}</td>
            <td style="padding-right:15px">Subscriptions</td>
            <td>Posts</td>
        </tr>
        <tr>
            <td style="padding-right:20px">
                <g:if test="${session.user != null && topic.createdBy.id != session.user?.id}">
                    <g:if test="${topic.subscriptions.collect{it.user.id == session.user?.id}}">

                        <g:link controller="subscription" action="unsubscribe" id="${topic.id}" >Unsubscribe</g:link>
                    </g:if>
                    <g:else>
                        <g:link controller="subscription" action="subscribe" id="${topic.id}" >Subscribe</g:link>
                    </g:else>
                </g:if>
                <g:elseif test="${session.user != null}">
                    <g:link controller="subscription" action="unsubscribe" id="${topic.id}" >Unsubscribe</g:link>
                </g:elseif>


            </td>
            <td style="padding-right:15px"><p style="color:lightblue" >${topic.subscriptionCount}</p></td>
            <td><p style="color:lightblue">${topic.count}</p></td>
        </tr>

    </table>

    <div style="margin-left: 20px;margin-top:20px">
    <g:if test="${session.user!=null}">
        <div style="width:100px;float:left">
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="menuserious"
                        data-toggle="dropdown">Serious
                    <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                    <li role="presentation"><a role="menuitem" href="#">Non-serious</a></li>
                    <li role="presentation"><a role="menuitem" href="#">Moderate</a></li>

                </ul>
            </div>

        </div>


        <div style="width:100px;float:left">
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="menuprivate"
                        data-toggle="dropdown">Serious
                    <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                    <li role="presentation"><a role="menuitem" href="#">Private</a></li>
                    <li role="presentation"><a role="menuitem" href="#">Delete</a></li>

                </ul>
            </div>
        </div>
    </g:if>
        <g:if test="${session.user!=null}">
            <span class="glyphicon glyphicon-envelope subscriptionicons"></span>
        </g:if>
        <g:if test="${topic.createdBy.username == session.user?.username}">

            <span class="glyphicon glyphicon-edit subscriptionicons"></span>
            <span class="glyphicon glyphicon-trash subscriptionicons"></span>
        </g:if>
    </div>
</div>