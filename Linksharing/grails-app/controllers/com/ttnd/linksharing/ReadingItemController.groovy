package com.ttnd.linksharing

class ReadingItemController {

    def index() { }

    def changeIsRead(Long id,Boolean isRead){
        //ReadingItem readingItem = ReadingItem.findById(id)
            try{
                ReadingItem.executeUpdate("update ReadingItem set isRead = :isRead where id = :id",[id:id,isRead:isRead])
            }catch(Exception ex){
                log.error("Exception occurres while updating reading item")
            }
    }
}
