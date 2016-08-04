package com.ttnd.linksharing

import grails.converters.JSON

class TopicController {

    def topicService;

    def index() { }

    def save(String topicName,String visibility){
        Topic topic = new Topic(name: topicName,createdBy: session.user,visibility:Visibility.getVisibilityFromString(visibility))
        try{

            topic.save(failOnError: true,flush:true)
            flash.message =  "success"
            render flash.message
        }catch(Exception ex){
           log.error("Exception occured while saving topic" + ex)
            flash.error = "failure"
            render flash.error
        }
    }

    def show(Long id){
        Topic topic    = Topic.findById(id)
        List<User> subscribingUsers = topic.getSubscribedUsers()
        User user;
        if(session.user != null){
            user = User.get(session.user.id)
        }

        List<ResourceVO> resources = []
        topic.resources.each{
            if(it instanceof DocumentResource){

                resources.add(new DocumentResourceVO(id:it.id,description: it.description,topic: it.topic,createdBy: it.createdBy,filePath: it.filePath))
            }else{
                resources.add(new LinkResourceVO(id:it.id,description: it.description,topic: it.topic,createdBy: it.createdBy,url: it.url))
            }
        }
        render model: [topic:topic,subscribingUsers: subscribingUsers,resources:resources,user:session.user,allSubscriptions:user==null?new ArrayList<String>():user.getSubscriptions(),resourceCount:user==null?0:user.resources.size()],view:'topichome'

    }

    def delete(Long id){
        Topic topic =  Topic.findById(id)
        User  user = User.get(session.user.id)
        if ((topic.createdBy.id == user.id) || (user.username='admin')){
            try{
              /*  Set<Subscription> subscriptions = topic.getSubscriptions()
                subscriptions.each{
                    topic.removeFromSubscriptions(it)
                }
                Set<Resource> resources = topic.getResources()
                resources.each{
                    it.delete()
                }*/
                topic.delete(flush: true)
                flash.message = "Topic deleted successfully"

            }catch(Exception ex){
                log.error("Exception occurred while deleting topic" + ex)
            }
        }else{
            flash.error="Only creator or admin can delete a topic"
        }

        render flash as JSON
    }

    def editName(Long id,String name){
        Topic topic = Topic.get(id)

        try{
            //topic.save(failOnError: true,flush: true)
            topic.setName(name)
            topic.save(failOnError: true,flush: true)
            Map map = [name:name]

            render map as JSON

        }catch(Exception ex){
            log.error("Exception occurred while saving new name for topic" + ex)
            response.sendError(500)
        }
    }

    def updateVisibility(Long id,String visibility){
         if(topicService.updateVisibility(id,visibility) == "success"){
             flash.message="Visibility Successfullly updated"
             Map map = [visibility: visibility]
             render map as JSON
         }else{
             flash.error = "Could not update visibility"
             retutn "failure"
         }
    }

    def search(){
        List<Topic> listtopics = Topic.searchTopics(params.q,Integer.parseInt(params.max),Integer.parseInt(params.offset))

        List<TopicVO> topics = []
        listtopics.each{
            TopicVO topicVO = new TopicVO(id:it.id,name:it.name,count: it.resources.size(),subscriptionCount: it.subscriptions.size(),createdBy: it.createdBy,visibility: it.visibility)
            topicVO.setSubscriptions(Subscription.findAllByTopic(it))
            topics.add(topicVO)

        }

        render model: [topics:topics],view:"/templates/_listTopics"
    }

    def searchCount(){
        Integer topicsCount = Topic.searchCount(params.q)
        render topicsCount
    }



}
