package com.ttnd.linksharing

class ReadingItem {
    Resource resource
    User user
    Boolean isRead

    static belongsTo = [
            user:User,
            resource:Resource
    ]

    static mapping = {
        isRead type: 'boolean'
        resource lazy: false
        sort "resource.dateCreated"

    }

    static constraints = {
        resource unique: "user"

    }
}
