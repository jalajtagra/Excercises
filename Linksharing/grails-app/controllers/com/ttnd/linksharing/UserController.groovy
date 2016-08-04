package com.ttnd.linksharing

import grails.converters.JSON
import org.springframework.validation.FieldError

import java.nio.file.Files
import java.nio.file.Paths

class UserController {



    def messageSource

    def emailService

    def index() {
        if(session.user){

            render "User Dashboard"
        }else{
            redirect([controller: 'login',action: 'index'])
        }
    }

    def logout() {
        session.invalidate()

        redirect([controller: 'login',action: 'index'])
    }

    def showtopic(ResourceSearchCO resourceSearchCO){
        Topic topic = Topic.find("from Topic where id = :topic_id ",Integer.parseInt(resourceSearchCO.topicId))
        if(topic != null){
            if( topic.visibility == Visibility.Public){

                render "success"
            }else{
                Subscription subscription = Subscription.find("from Subscription where user=${session.user} and topic = :topic",topic)
                if(subscription != null){
                    render "success"
                }else{
                    flash.error = "User is not subscribed to the topic"
                    redirect([controller: 'login',action: 'index'])
                }
            }
        }else{
            redirect([controller: 'login',action: 'index'])
        }

    }



    /*def registeruser(){
        def user = new User();
        bindData(user,params,[exclude:['photo','admin','active']])
        if(user.validate()){
            user.save();
            flash.message = "User registered successfully"
        }else{
            user.getErrors().getFieldErrors();
            flash.message="Details are not correct"
        }
        render "flash.message"
    }*/

    def registeruser(UserCommand userCommand){

        if(userCommand.validate()){
            User user = new User(userCommand.properties)
            user.active=true
            user.admin=false
           user.photo = userCommand.profilePhoto.getBytes()
            String rootPath = System.getProperty("user.dir")
            //Files.copy(userCommand.profilePhoto.getInputStream(),Paths.get(rootPath+"/grails-app/assets/images/defaults",userCommand.profilePhoto.getOriginalFilename()))
           // user.filePath = Paths.get(rootPath+"/grails-app/assets/images/defaults",userCommand.profilePhoto.getOriginalFilename());
            try{

                user.save(failOnError: true,flush: true);
                flash.message = "User registered successfully"
                session.user = user
                redirect([action: 'dashboard'])
            }catch(Exception ex){


                log.error("Exception occurred while registering user"+ex)
                render model: [userCommand:user]
            }

        }else{
            /*List<FieldError> errors = user.getErrors().getFieldErrors();
            errors.each {
                String errorfield = it.getField()
                flash.message = messageSource.getMessage("registeruser.${errorfield}.error",null,null)
                render flash.message,view: 'login'
            }*/

                    if(userCommand.hasErrors()){
                        List<ResourceVO> resources = Resource.getTopResources()
                        List<Resource> recentResources = Resource.search(new ResourceSearchCO(max:5,offset: 0)).listDistinct()
                        render model:[userCommand:userCommand,resources:resources,recentPosts:recentResources],view:'login'
                    }


        }

    }

    def dashboard(){
            User user = User.get(session.user.id)
        List<TopicVO> topics = Topic.getTopicsInfo(5,0)
//        List<Resource> topResources = Resource.getTopResources()
//        List<Subscription> subscriptions = Subscription.findAllByUser(session.user);
        Integer topicsCount = Topic.count
        List<TopicVO> top5SubscribedTopics = user.getTopSubscribedTopicsForUser(5,0)
        List<ResourceVO> readingItems = user.getUnReadResources(new SearchCO(max: 5,offset: 0,q:null,))
        Integer unreadResourcesCount = user.readingItems.findAll{
            it.isRead == false
        }.size()
       Set<Subscription> allSubscriptions = user.getSubscriptions()
        Integer count = user.getResources().size()


                render view: 'dashboard',model: [topics:topics,subscriptions:top5SubscribedTopics,readingItems:readingItems,user:user,allSubscriptions:allSubscriptions,resourceCount:count,topicsCount:topicsCount,unreadResourcesCount:unreadResourcesCount]

    }

    def image(Long id) {
        User user = User.findById(id)

        if(user?.photo){
            response.setContentType("image/png")
            response.setContentLength(user.photo.length)
          //  response.setHeader("Content-disposition", "inline;filename=\"abc.png\"")

            response.outputStream.write(user.photo)
            response.outputStream.flush()

        }
    }


    def validateUsername(String username){
        User user = User.findByUsername(username)
        if(user){
            render text: false, contentType: 'application/json'
        }else{
            render text: true, contentType:'application/json'
        }
    }

    def validateEmail(String email){
        User user = User.findByEmail(email)
        if(user){
            render text: false, contentType: 'application/json'
        }else{
            render text: true, contentType:'application/json'
        }
    }



    def profile(Long id){
        User user = User.get(id)
        Set<Resource> listposts = user.resources
        List<ResourceVO> resources = []
        listposts.each {
            if(it instanceof DocumentResource){
                resources.add(new DocumentResourceVO(id:it.id,description: it.description,topic: it.topic,createdBy: it.createdBy,filePath: it.filePath))
            }else{
                resources.add(new LinkResourceVO(id:it.id,description: it.description,topic: it.topic,createdBy: it.createdBy,url: it.url))
            }
        }
        if(resources.size() >5)
        resources = resources.subList(0,5)

            List<TopicVO> top5SubscribedTopics = user.getTopSubscribedTopicsForUser(5,0)

        Set<Topic> listtopics = Topic.findAllByCreatedByAndVisibility(user,Visibility.Public)

            List<TopicVO> topics = []
        listtopics.each{
            TopicVO topicVO = new TopicVO(id:it.id,name:it.name,count: it.resources.size(),subscriptionCount: it.subscriptions.size(),createdBy: it.createdBy,visibility: it.visibility)
            topicVO.setSubscriptions(Subscription.findAllByTopic(it))
            topics.add(topicVO)
        }

        render model: [user:user,topics:topics,posts:resources,subscribedTopics: top5SubscribedTopics,allSubscriptions: user.subscriptions,resourceCount: user.resources.size()] ,view: "userprofile"


    }

    def editProfile(){
        User user = User.get(session.user.id)
        UserCommand userCommand = new UserCommand(firstName: user.firstName,lastName: user.lastName,username: user.username)
        Set<Topic> listtopics = user.topics

        List<TopicVO> topics = []
        listtopics.each{
            TopicVO topicVO = new TopicVO(id:it.id,name:it.name,count: it.resources.size(),subscriptionCount: it.subscriptions.size(),createdBy: it.createdBy,visibility: it.visibility)
            topicVO.setSubscriptions(Subscription.findAllByTopic(it))
            topics.add(topicVO)
        }
        render model: [topics:topics,userCommand: userCommand,user:user],view: "editProfile"
    }

    def updateProfile(UserCommand userCommand){
        User user = User.get(session.user.id)
        userCommand.setPassword(user.password)
        userCommand.setConfirmPassword(user.password)
        userCommand.setEmail(user.email)


        if(userCommand.validate()){
            user.setFirstName(userCommand.firstName)
            user.setLastName(userCommand.lastName)
            user.setUsername(userCommand.username)
            user.setConfirmPassword(user.password)
            if(userCommand.profilePhoto){

                user.photo = userCommand.profilePhoto.getBytes()
            }
            try{

                user.save(flush: true,failOnError: true)
                flash.message = "Success"

                redirect([action: "editProfile"])
            }catch(Exception ex){

                    if(user.errors.getFieldError("username") != null){
                        userCommand.errors.rejectValue("username","unique","Please type in a unique username")
                    }

                log.error("Exception occurred while updating user " + ex)
            }


        }
        Set<Topic> listtopics = user.topics

            List<TopicVO> topics = []
            listtopics.each{
                TopicVO topicVO = new TopicVO(id:it.id,name:it.name,count: it.resources.size(),subscriptionCount: it.subscriptions.size(),createdBy: it.createdBy,visibility: it.visibility)
                topicVO.setSubscriptions(Subscription.findAllByTopic(it))
                topics.add(topicVO)
            }

            render model: [userCommand: userCommand,topics: topics,user:user],view:"editProfile"

    }

    def changePassword (String password,String confirmPassword){
        User user = User.get(session.user.id)
        user.setPassword(password)
        user.setConfirmPassword(password)
        try{

            user.save(flush:true)
            flash.message = "Password updated successfully"
        }catch(Exception ex){
            flash.error = "Could not save password"
            log.error("Exception occurred while updating user " + ex)
        }

        redirect([action: "editProfile"])
    }

    def trendingTopics(){

           render model: [topics: Topic.getTopicsInfo(Integer.parseInt(params.max),Integer.parseInt(params.offset)),topicsCount:Topic.count],view: "/templates/_listTopics"
    }

    def getTopSubscribedTopicsForUser(){
        User user = User.get(session.user.id)
        render model:[topics:user.getTopSubscribedTopicsForUser(Integer.parseInt(params.max),Integer.parseInt(params.offset))],view: "/templates/_listTopics"
    }

    def getUnreadResources(){
        User user = User.get(session.user.id)
        SearchCO searchCo = new SearchCO(max: Integer.parseInt(params.max),offset:Integer.parseInt(params.offset),q:params.q,)

       // Long unreadResourcesCount = getUnreadResourcesCount(params.q)

        List<ResourceVO> readingItems = user.getUnReadResources(searchCo)
        render model: [readingItems: readingItems],view: "/resource/_listReadingItem"
    }

    def getUnreadResourcesCount(String q){
        User user = User.get(session.user.id)
        SearchCO searchCo = new SearchCO(q:q)
        Integer count = user.getUnReadResourcesCount(searchCo)
        render count
    }

    def sendEmail(String emailId,Long topicId){
        User user = User.get(session.user.id)
        render   emailService.sendMail(emailId,user.email,topicId)
    }

    def searchAll(){

            //User user = User.get(session.user.id)
        SearchCO searchCo = new SearchCO(max: 5,offset:5,q:params.q,)
        List<ResourceVO> resources = Resource.search(new ResourceSearchCO(q:params.q,max:5,offset: 0)).listDistinct()

        Integer count = Resource.searchCount(new ResourceSearchCO(q:params.q,max:5,offset: 0))

        List<Topic> listtopics = Topic.searchTopics(params.q,5,0)

        List<TopicVO> topics = []
        listtopics.each{
            TopicVO topicVO = new TopicVO(id:it.id,name:it.name,count: it.resources.size(),subscriptionCount: it.subscriptions.size(),createdBy: it.createdBy,visibility: it.visibility)
            topicVO.setSubscriptions(Subscription.findAllByTopic(it))
            topics.add(topicVO)

        }

        Integer topicsCount = Topic.searchCount(params.q)


        render model: [topics:topics,posts:resources,postCount:count,topicsCount:topicsCount,q:params.q],view:"searchhome"
    }

}
