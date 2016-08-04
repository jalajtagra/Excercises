package com.ttnd.linksharing

class ReadingItemController {

    def resourceService

    def index() { }


    def changeIsRead(Long id,Boolean isRead){
        //ReadingItem readingItem = ReadingItem.findById(id)
        User user = User.get(session.user.id)
        Resource resource = Resource.get(id)

            try{
                ReadingItem.executeUpdate("update ReadingItem set isRead = :isRead where resource = :resource  and user = :user",[resource:resource,user:user,isRead:isRead])
            }catch(Exception ex){
                log.error("Exception occurres while updating reading item")
            }

        List listResourceVOs = user.getUnReadResources(new SearchCO(max: 5,offset: 0,q:null,))
        render(template: "/resource/listReadingItem", model: [readingItems: listResourceVOs])

    }
}
