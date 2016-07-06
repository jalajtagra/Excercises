package com.ttnd.linksharing

class ResourceRating {
    Resource resource
    User user
    Integer rating

    static constraints = {
        rating(range: 1..5)
        resource unique: "user"
    }
}
