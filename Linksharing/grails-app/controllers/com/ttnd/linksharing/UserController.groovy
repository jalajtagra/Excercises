package com.ttnd.linksharing

import org.springframework.validation.FieldError

class UserController {



    def messageSource

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
            try{

                user.save(failOnError: true,flush: true);
                flash.message = "User registered successfully"
                session.user = user
                redirect([action: 'dashboard'])
            }catch(Exception ex){


                log.error("Exception occurred while registering user"+ex)
                render model: [userCommand:user],view:'login'
            }

        }else{
            /*List<FieldError> errors = user.getErrors().getFieldErrors();
            errors.each {
                String errorfield = it.getField()
                flash.message = messageSource.getMessage("registeruser.${errorfield}.error",null,null)
                render flash.message,view: 'login'
            }*/
                    if(userCommand.hasErrors())
            render model: [userCommand:userCommand],view:'login'

        }

    }

    def dashboard(){
        User user = session.user
        List<TopicVO> topics = Topic.getTopicsInfo(2)
//        List<Resource> topResources = Resource.getTopResources()
//        List<Subscription> subscriptions = Subscription.findAllByUser(session.user);
        List<TopicVO> top5SubscribedTopics = user.getTopSubscribedTopicsForUser(2)
        List<ReadingItem> readingItems = user.getUnReadResources(new SearchCO(max: 2,offset: 0,q:null,))
      //  List<Subscription> allSubscriptions = session.user.subscriptions


        render view: 'dashboard',model: [topics:topics,subscriptions:top5SubscribedTopics,readingItems:readingItems,user:session.user]

    }

    def image(Long id) {
        User user = User.findById(id)
        if(user.photo){
            return photo
        }else{
            return null
        }
    }






}
