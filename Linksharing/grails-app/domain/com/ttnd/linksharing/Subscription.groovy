package com.ttnd.linksharing

class Subscription implements Serializable{

    Topic topic
    User user
    Seriouness seriousness = Seriouness.Serious
    Date dateCreated

    static  belongsTo = [user:User,topic:Topic]

    static constraints = {
            id composite: ['topic','user']
            user unique: "topic"

    }
    static mapping = {
        user lazy:false
        topic lazy: false
        sort topic: "asc"
        //id composite: ['topic','user']
    }

    def afterInsert(){
        Set<Resource> resources = topic.resources
        resources.each{
            ReadingItem readingItem = new ReadingItem(user:user,resource: it,isRead: false)
            try{

                readingItem.save(flush:true,failOnError: true)
            }catch(Exception ex){
                log.error("Exception occurred while saving reading item" + ex)
            }
        }
    }





}
