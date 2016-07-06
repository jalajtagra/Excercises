package com.ttnd.linksharing

abstract class Resource {

    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static mapping = {
        discriminator column:"content_type",
                description:'text'
    }
    static belongsTo = [createdBy:User]
}


