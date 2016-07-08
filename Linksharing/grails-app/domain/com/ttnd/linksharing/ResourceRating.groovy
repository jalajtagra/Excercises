package com.ttnd.linksharing

class ResourceRating {
    Resource resource
    User user
    Integer rating

    static belongsTo = [resource:Resource,
                        user:User ]

    static constraints = {
        rating(range: 1..5)
        resource unique: "user"
    }
}
