<%@ page import="com.ttnd.linksharing.Subscription" %>
<div class="panel-body">
    <div class="col-md-4">
        <g:if test="${topic.createdBy.photo != null}">
            <a href="user/profile?id=${topic?.createdBy?.id}"><img src="user/image/${topic.createdBy.id}"
                 style="width:80px;height:80px;float: left;margin-right: 40px;"></a>
        </g:if>
        <g:else>
            <a href="user/profile?id=${topic?.createdBy?.id}"><img src="${asset.assetPath(src: 'defaults/dp.png')}"
                 style="width:80px;height:80px;float: left;margin-right: 40px;"></a>

        </g:else>
    </div>

    <div class="col-md-8 topic-info">
        <div class="topic-name"><g:link controller="topic" action="show" id="${topic.id}">${topic.name}</g:link></div>
        <div class="edit-topic-name row" id="${topic.id}"><input type="text" value="${topic.name}" name="name" style="width:90px"><button class="save-topic" style="margin-left: 10px">Save</button><button class="cancel-topic" style="margin-left: 10px">Cancel</button></div>

        <p style="color:gray">@${topic.createdBy.username}</p>
        <table style="padding-left: 20px;margin-top:10px    ;">
            <tr>
                <td style="padding-right:20px">${topic?.createdBy?.username}</td>
                <td style="padding-right:15px">Subscriptions</td>
                <td>Posts</td>
            </tr>
            <tr>
                <td style="padding-right:20px">
                    <% com.ttnd.linksharing.Subscription sub = topic.subscriptions.find {
                        it.user.id == session.user?.id
                    } %>
                    <g:if test="${session.user != null && topic.createdBy.id != session.user?.id}">
                        <g:if test="${sub}">

                            <a class="unsubscribe" id="${topic.id}">Unsubscribe</a>
                        </g:if>
                        <g:else>
                            <a class="subscribe" id="${topic.id}">Subscribe</a>
                        </g:else>
                    </g:if>
                    <g:elseif test="${session.user != null}">
                        <a class="unsubscribe" id="${topic.id}">Unsubscribe</a>
                    </g:elseif>

                </td>
                <td style="padding-right:15px"><p style="color:lightblue">${topic.subscriptionCount}</p></td>
                <td><p style="color:lightblue">${topic.count}</p></td>

            </tr>

        </table>

    </div>

    <div class="row">
        <div class="col-md-8">
            <div style="margin-left: 20px;margin-top:10px;padding-right: 10px">
                <g:if test="${session.user != null && (sub?.seriousness)}">%{--only is user is subscribes--}%
                    <% List<String> li = ['VerySerious', 'Serious', 'Casual'] %>
                    <div style="width:100px;float:left">
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="menuserious"
                                    data-toggle="dropdown">${sub.seriousness.toString().replace("_", "")}
                                <span class="caret"></span></button>
                            <ul class="dropdown-menu seriousness-dd" role="menu" aria-labelledby="menu1" id="${sub.id}">
                                <g:each in="${li}">
                                    <g:if test="${it != sub.seriousness.toString().replace("_", "")}">
                                        <li role="presentation"><a role="menuitem" href="#">${it}</a></li>
                                    </g:if>
                                </g:each>

                            %{--<li role="presentation"><a role="menuitem" href="#">Serious</a></li>--}%

                            </ul>
                        </div>

                    </div>
                </g:if>

                <% List<String> visblist = ['Public', 'Private', 'Delete'] %>
                <div style="width:100px;float:left;margin-left: 20px">
                    <g:if test="${(topic.createdBy.id == session.user?.id)}">%{-- only if user is creator of topic--}%
                        <div class="dropdown visibility-dd" id="${sub.id}">
                            <button class="btn btn-default dropdown-toggle" type="button" id="menuprivate"
                                    data-toggle="dropdown">${topic.visibility}
                                <span class="caret"></span></button>
                            <ul class="dropdown-menu menuprivate" role="menu" aria-labelledby="menu1">
                                <g:each in="${visblist}">
                                    <g:if test="${it != topic.visibility.toString()}">
                                        <li role="presentation" id="${topic.id}"><a role="menuitem" href="#">${it}</a></li>
                                    </g:if>
                                </g:each>

                            </ul>
                        </div>
                    </g:if>
                </div>




                %{--<custom:canUpdateTopic topic="${topic}" sub="${sub}" user="${user}"></custom:canUpdateTopic>--}%
            </div>
        </div>
        <div class="col-md-4" style="margin-top: 20px">
            <g:if test="${session.user != null}">
                <span class="glyphicon glyphicon-envelope subscriptionicons" id="${topic.id}" data-toggle="modal" data-target="#sendInvitation"></span>
            </g:if>
            <g:if test="${topic.createdBy.id == session.user?.id}">

                <span class="glyphicon glyphicon-edit subscriptionicons" id="${topic.id}"></span>
                <span class="glyphicon glyphicon-trash subscriptionicons" id="${topic.id}"></span>
            </g:if>
        </div>


    </div>

</div>

