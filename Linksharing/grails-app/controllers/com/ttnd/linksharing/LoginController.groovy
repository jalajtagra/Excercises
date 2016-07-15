package com.ttnd.linksharing

class LoginController {


    def index() {
        if(session.user == null){
           List<ResourceVO> resources = Resource.getTopResources()
            List<Resource> recentResources = Resource.search(new ResourceSearchCO(max:5,offset: 0)).listDistinct()
            render model:[resources:resources,recentPosts:recentResources],view:'login'
        }else{
            forward([controller: 'user',action: 'dashboard'])
        }
    }


    def loginhandler() {
      // User user1 = new User(username: 'jalajtagra',password: 'jshksaf')
        User user = User.find("from User as b where b.username='${params.username}' and b.password='${params.password}'")
        if(user != null){
            if(user.active){
                user.subscriptions
                session.setAttribute("user",user)
                redirect([controller: 'login', action: 'index'])
            }else{
                flash.error = 'Your account is not active'
            }
        }else{
            flash.error = 'User not found'
        }
        render flash.error
    }




}
