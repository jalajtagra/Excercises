package com.ttnd.linkshring

class CustomTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "custom"

    def userImage = {attr,body->
        Long userId = attr.userId
        Integer height = attr.height
        Integer width = attr.width
        out << "<img src='/user/img/${userId}' height='${height}px' width='${width}px'/>"
    }
}
