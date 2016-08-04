package com.ttnd.linksharing

import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    def removereadingItems(User user,Topic topic){

        Set<Resource> resources = topic.resources
        resources.each{
            ReadingItem readingItem = ReadingItem.findByUserAndResource(user,it)


            // it.removeFromReadingItems(readingItem)
            try{
                user.removeFromReadingItems(readingItem)

                /*user.confirmPassword = user.password
                user.save(flush:true,failOnError: true)*/

               // readingItem.delete(flush:true,failOnError: true)
                /* readingItem.resource = null
                   readingItem.save(flush:true)*/
            }catch(Exception ex){
                log.error("Exception occurred while removing reading_item " + ex)
            }
        }
    }
}
