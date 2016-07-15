package com.ttnd.linksharing

class TopicController {

    def index() { }

    def save(String topicName,String visibility){
        Topic topic = new Topic(name: topicName,createdBy: session.user,Visibility.getVisibilityFromString(visibility))
        try{

            topic.save(failOnError: true,flush:true)
            render "Successfully saved"
        }catch(Exception ex){
           log.error("Exception occured while saving topic" + ex)
            flash.error = "Exception occured while saving topic" + ex
            render flash.error
        }
    }

    def show(Long id){
        Topic topic    = Topic.findById(id)
        List<User> subscribingUsers = topic.getSubscribedUsers()
        render model: [topic:topic,subscribingUsers: subscribingUsers,resources:topic.resources,user:session.user],view:'topichome'

    }


}
