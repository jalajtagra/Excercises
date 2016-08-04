package com.ttnd.linksharing

class LoginController {

    def emailService


    def index() {
        List<ResourceVO> resources = Resource.getTopResources()
        List<Resource> recentResources = Resource.search(new ResourceSearchCO(max:5,offset: 0)).listDistinct()


        if(session.user == null){
            if(flash.error == null){
                render model:[resources:resources,recentPosts:recentResources],view:'login'
            }
            else if(flash.error!=null ){
                render model:[resources:resources,recentPosts:recentResources,username:params.username],view:'login'
            }
        }

        else{
            forward([controller: 'user',action: 'dashboard'])
        }
    }


    def loginhandler() {
      // User user1 = new User(username: 'jalajtagra',password: 'jshksaf')
        User user = User.find("from User as b where b.username='${params.username}' and b.password='${params.password}'")
        if(user != null){
            if(user.active){
                user.getSubscriptions()
                session.setAttribute("user",user)
                redirect([controller: 'login', action: 'index'])
            }else{
                flash.error = 'Your account is not active'
                redirect action: "index",params: [username:params.username]
            }
        }else{
            flash.error = params.username + ' Username or Password is incorrect'
            redirect action: "index",params: [username:params.username]
        }

    }

    def forgotPassword(){
        User user = User.findByUsername(params.username);
        if(user){
            emailService.sendPasswordMail(user.email,user.username,user.password)
            flash.message = "Password sent to your registered email-id"
        }else{
            flash.error = "Username not registered with us"
        }
        redirect([controller: 'login', action: 'index'])

    }





}
