package com.ttnd.linksharing

class Subscription implements Serializable{

    Topic topic
    User user
    Seriouness seriousness = Seriouness.Serious
    Date dateCreated

    static  belongsTo = [User,Topic]

    static constraints = {
            id composite: ['topic','user']
            user unique: "topic"

    }
    static mapping = {
        user lazy:false
        topic lazy: false
        //id composite: ['topic','user']
    }


}
