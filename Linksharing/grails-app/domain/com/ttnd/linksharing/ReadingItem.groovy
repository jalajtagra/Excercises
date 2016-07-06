package com.ttnd.linksharing

class ReadingItem {
    Resource resource
    User user
    Boolean isRead

    static mapping = {
        isRead type: 'boolean'
    }

    static constraints = {
        resource unique: "user"
    }
}
