package com.ttnd.linksharing

class UserController {

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

    def showtopic(String topic_id){
        Topic topic = Topic.find("from Topic where id = :topic_id ",Integer.parseInt(topic_id))
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
            user.active==true
            user.admin==false
            user.save();
            flash.message = "User registered successfully"
        }else{
            user.getErrors().getFieldErrors();
            flash.message="Details are not correct"
        }
        render flash.message
    }

}
