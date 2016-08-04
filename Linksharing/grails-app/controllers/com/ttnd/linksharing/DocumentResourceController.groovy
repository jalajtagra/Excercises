package com.ttnd.linksharing

import org.springframework.web.multipart.MultipartFile

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class DocumentResourceController extends ResourceController{

    def index() { }


    def save(DocumentResourceCO documentResourceCO){
        String rootPath = System.getProperty("user.dir")
        Path path = Paths.get(rootPath+"/Files",documentResourceCO.document.getOriginalFilename()+new Date().getTime())
        Files.copy(documentResourceCO.document.getInputStream(),path)
        String filePath = path;
        Topic topic = Topic.get(documentResourceCO.topicId)
        User user = User.get(session.user.id)
        DocumentResource documentResource = new DocumentResource(topic: topic,createdBy: user,description: documentResourceCO.description,filePath:filePath,contentType: documentResourceCO.document.getContentType())
        try{

            documentResource.save(failOnError: true,flush:true);
            render "success"
        }catch(Exception ex){
            log.error("Exception occured while sharing document resource" + ex)
            render "failure"
        }

    }


}
