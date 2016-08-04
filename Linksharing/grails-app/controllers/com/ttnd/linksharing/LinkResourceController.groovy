package com.ttnd.linksharing

import grails.transaction.Transactional

class LinkResourceController {

    def index() { }

    @Transactional
    def save(LinkResourceCO linkResourceCO){
        Topic topic = Topic.get(linkResourceCO.topicId)
        User user = User.get(session.user.id)
        LinkResource linkResource = new LinkResource(topic: topic,user:user,description: linkResourceCO.description,url:linkResourceCO.url,createdBy: user)
        try{
            linkResource.save(failOnError: true,flush: true)
            render "success"
        }catch(Exception ex){
            log.error("Exception occurred while saving link resource")
            render "failure"
        }
    }

    def delete(Long id){
        Resource resource = Resource.get(id)
        try{

            resource.delete(flush: true)
        }catch(Exception ex){
            log.error("Cannot delete resource" + ex)
        }
    }


}
