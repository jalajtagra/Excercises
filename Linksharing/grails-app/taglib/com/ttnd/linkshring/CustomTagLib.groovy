package com.ttnd.linkshring

import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.TopicVO
import com.ttnd.linksharing.User

class CustomTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "custom"

    def userImage = {attr,body->
        Long userId = attr.userId
        String height = attr.height
        String width = attr.width
        out << "<img src='user/image/${userId}' height='${height}px' width='${width}px'/>"
    }

    def canUpdateTopic = {attr,body->
        TopicVO topic = attr.topic
        List<String> visblist = ['Public', 'Private', 'Delete']
        Subscription sub = attr.sub
        User user = attr.user
        if(topic?.createdBy?.id == user?.id)
        out << """


                <div style="width:100px;float:left">
                    <div class="dropdown visibility-dd" id="${sub?.id}">
                        <button class="btn btn-default dropdown-toggle" type="button" id="menuprivate"
                                data-toggle="dropdown">${topic.visibility}
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu menuprivate" role="menu" aria-labelledby="menu1">

                            ${visblist.each {
                                    "<li role='presentation'><a role='menuitem'' href='#''>${it}</a></li>"
                                }}

                        </ul>
                    </div>
                </div>

            """

        if(user != null)
           out << '<span class="glyphicon glyphicon-envelope subscriptionicons"></span>'

      if(topic.createdBy.username == user?.username)
    out<<   """   <span class="glyphicon glyphicon-edit subscriptionicons"></span>
            <span class="glyphicon glyphicon-trash subscriptionicons"></span>

            """
    }



    def observe = {attrs ->
        if(!attrs.noScript){
            out << '<script type="text/javascript">'
        }
        if(attrs.element && attrs.element instanceof String){
            printObserve("\$('${attrs.element}')", attrs.event, attrs.function, out)
        }
        if(attrs.element && attrs.element instanceof List){
            attrs.element.each{it -> printObserve("\$('${it}')", attrs.event, attrs.function, out)}
        }
        if(attrs.classes && attrs.classes instanceof String){
            printObserveClass(attrs.classes, attrs.event, attrs.function, out)
        }
        if(attrs.classes && attrs.classes instanceof List){
            attrs.classes.each{ it -> printObserveClass(it, attrs.event, attrs.function, out)}
        }
        if(!attrs.noScript){
            out << '</script>'
        }
    }

    def printObserveClass(className, event, function, out){
        out <<  "var classes  = \$\$('.' + '${className}');"
        out <<  "for(i = 0; i < classes.length; i++) {"
        printObserve("classes[i]", event, function, out)
        out <<  "}"
    }

    def printObserve(element, event, function, out){
        if(event && event instanceof String){
            out << "${element}.observe('${event}', ${function});"
        }
        if(event && event instanceof List){
            attrs.event.each{ it -> out << "${element}.observe('${it}', ${function});"}
        }
    }
}
