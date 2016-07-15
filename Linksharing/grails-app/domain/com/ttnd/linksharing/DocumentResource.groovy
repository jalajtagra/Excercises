package com.ttnd.linksharing

class DocumentResource extends Resource{

    String filePath
    String contentType
    String fileName

    static final DOCUMENT_CONTENT_TYPE = 'application/pdf'

    static transients = ['contentType','fileName']

    static mapping = {
        discriminator("Document")
    }

    static constraints = {
        contentType bindable:true,validator: {val,obj->
            val == DOCUMENT_CONTENT_TYPE
        }
        fileName nullable: true
    }


    @Override
    String toString() {
        return filePath
    }

    def afterInsert(){
        ReadingItem readingItem = new ReadingItem(resource: this,user:createdBy,isRead: true)
        withNewSession {
            readingItem.save()
        }

    }

    @Override
    void delete(){

    }

    byte[] download(long id){
        Resource resource = Resource.findById(id)
        if(resource == null){
            return "Resource not found"
        }else{

        }
    }

}
