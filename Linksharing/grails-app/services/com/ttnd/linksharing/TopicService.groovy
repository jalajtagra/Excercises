package com.ttnd.linksharing

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class TopicService {

    def serviceMethod() {

    }

    def getAllSubscribedTopicsForUser(User user){

    }

    def updateVisibility(Long id, String visibility){

        Topic topic = Topic.findById(id)
        if(visibility.equalsIgnoreCase("delete")){
            try{
                topic.delete(failOnError:true)
                return "success"
            }catch(Exception ex){
                log.error("Exception caught while deleting topic" + ex)
                return "failure"
            }
        }else{

            try{
                //topic.save(failOnError: true,flush: true)
                topic.setVisibility(Visibility.getVisibilityFromString(visibility))
                topic.save(failOnError: true,flush: true)


                return "success"

            }catch(Exception ex){
                log.error("Exception occurred while updating visibility for topic" + ex)
                return "failure"
            }
        }


    }
}
