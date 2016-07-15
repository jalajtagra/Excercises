package com.ttnd.linksharing

import org.codehaus.groovy.grails.orm.hibernate.cfg.NamedCriteriaProxy

class ResourceController {

    def index() { }


    def search(ResourceSearchCO resourceSearchCO) {
       // Resource.find("from Resource re where  re.description like '%:descrip%' and re.topic.visibility = :visibility",[descrip:resourceSearchCO.q,visibility:resourceSearchCO.visibility])
//        ResourceSearchCO resourceSearchCO = new ResourceSearchCO(q:'Dumm',visibility: 'Public')

    if(resourceSearchCO.q){

        NamedCriteriaProxy namedCriteriaProxy =  Resource.search(resourceSearchCO)
        List list = namedCriteriaProxy.listDistinct()
        println list
    }


    }

    def saveLinkResource(){

    }

    def saveDocumentResource(){

    }

    def show(Long id){
        Resource resource = Resource.findById(id)
        List<TopicVO> topics = Topic.getTopicsInfo(2)

        render model: [resource:resource,topics:topics],view:'resourcehome'
    }

    def addToReadingItems(Resource resource){
        List<Subscription> subscriptions = resource.topic.subscriptions
        subscriptions.each{
            ReadingItem readingItem1 = new ReadingItem(resource: resource,user:it.user,isRead: false)
                readingItem1.save()
        }
    }

    def delete(Resource resource){
        resource.delete()

    }

    def download(Long id){

    }


}
