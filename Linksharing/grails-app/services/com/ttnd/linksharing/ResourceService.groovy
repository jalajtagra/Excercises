package com.ttnd.linksharing

import grails.transaction.Transactional
import org.codehaus.groovy.grails.orm.hibernate.cfg.NamedCriteriaProxy

@Transactional
class ResourceService {

    def serviceMethod() {

    }

    def search(ResourceSearchCO resourceSearchCO){
        List<Resource> list = [];
       // if(resourceSearchCO.q){

            NamedCriteriaProxy namedCriteriaProxy =  Resource.search(resourceSearchCO)
            list= namedCriteriaProxy.listDistinct()
            println list



       // }
        List<ResourceVO> listResourceVOs = []
        list.each {

                if(it instanceof DocumentResource){

                    listResourceVOs.add(new DocumentResourceVO(id:it.id,description: it.description,topic: it.topic,createdBy: it.createdBy,filePath: it.filePath))
                }else{
                    listResourceVOs.add(new LinkResourceVO(id:it.id,description: it.description,topic: it.topic,createdBy: it.createdBy,url: it.url))
                }



        }
        return listResourceVOs
    }



}
