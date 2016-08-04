package com.ttnd.linksharing

import grails.converters.JSON
import org.codehaus.groovy.grails.orm.hibernate.cfg.NamedCriteriaProxy

class ResourceController {

    def index() { }

    def resourceService


    def search(ResourceSearchCO resourceSearchCO) {

       // Resource.find("from Resource re where  re.description like '%:descrip%' and re.topic.visibility = :visibility",[descrip:resourceSearchCO.q,visibility:resourceSearchCO.visibility])
//        ResourceSearchCO resourceSearchCO = new ResourceSearchCO(q:'Dumm',visibility: 'Public')
        List listResourceVOs = resourceService.search(resourceSearchCO)

        render(template: "listReadingItem", model: [readingItems: listResourceVOs])

    }



    def saveLinkResource(){

    }

    def saveDocumentResource(){

    }

    def show(Long id){
        Resource resource = Resource.findById(id)
        List<TopicVO> topics = Topic.getTopicsInfo(2,0)
        RatingInfoVO ratingInfoVO = resource.getRatingInfo()

        List<Subscription> allSubscriptions = [];/* = new HashSet<Subscription>();*/

        if(session.user != null){
            allSubscriptions = User.findById(session.user.id).getSubscriptions().asList()
        }
        render model: [resource:resource,topics:topics,ratingInfo:ratingInfoVO,allSubscriptions:allSubscriptions],view:'resourcehome'
    }

    def addToReadingItems(Resource resource){
        List<Subscription> subscriptions = resource.topic.subscriptions
        subscriptions.each{
            ReadingItem readingItem1 = new ReadingItem(resource: resource,user:it.user,isRead: false)
                readingItem1.save()
        }
    }

    def delete(Long id){
        Resource resource = Resource.findById(id)
        resource.delete()
        flash.message="Resource deleted successfully"
        redirect controller: "user",action: "dashboard"

    }

    def download(Long id){
        Resource resource = Resource.get(id)
        response.setContentType("application/octet-stream")
        if(resource instanceof  DocumentResource ){
            File file = new File(resource.filePath)
            // response.setContentLength(file.getBytes().length)
            response.setHeader("Content-disposition", "attachment;filename=\"${file.getName().substring(0,file.getName().lastIndexOf("pdf")+3)}\"")
            try{

                response.outputStream.write(file.getBytes())
                response.outputStream.flush()
                response.outputStream.close()
            }catch (Exception ex){
                response.outputStream.close()
            }
        }

    }

    def searchCount(ResourceSearchCO resourceSearchCO){
        Integer count = Resource.searchCount(resourceSearchCO);
        render count;
    }


    def updateDescription(Long id,String description){
        Resource resource = Resource.findById(id)
        resource.description = description
        try{
            resource.save(flush:true,failOnError: true)
            flash.message = "Resource description updated successfully"
        }catch(Exception ex){
            log.error("Exception caught while updating resource description" + ex)
            flash.error = "Description could not be updated"
        }
        redirect action: "show",params: [id:id]
    }



}
