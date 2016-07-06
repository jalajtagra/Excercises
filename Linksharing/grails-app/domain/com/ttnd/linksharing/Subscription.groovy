package com.ttnd.linksharing

class Subscription implements Serializable{

    Topic topic
    User user
    Seriouness seriouness
    Date dateCreated

    static  belongsTo = [User,Topic]

    static constraints = {
            id composite: ['topic','user']
            user unique: "topic"

    }
    static mapping = {
        //id composite: ['topic','user']
    }
}
