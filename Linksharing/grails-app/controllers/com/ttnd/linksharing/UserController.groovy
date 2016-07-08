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

        forward([controller: 'login',action: 'index'])
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

    def registeruser(User user){

        if(user.validate()){
            user.active=true
            user.admin=false
            try{

                user.save(failOnError: true,flush: true);
            }catch(Exception ex){
                log.error("Exception occurred while registering user"+ex)
            }
            flash.message = "User registered successfully"
        }else{
            List<FieldError> errors = user.getErrors().getFieldErrors();
            errors.each {
                String errorfield = it.getField()
                flash.message = messageSource.getMessage("registeruser.${errorfield}.error",null,null)
            }

        }
        render flash.message
    }

}
