package com.ttnd.linksharing

import grails.converters.JSON

class AdminController {


    def index() {}

    def users(String filter,String searchString) {
        List<User> allUsers
        Integer usersCount

        if (filter == "all") {
            allUsers = User.adminSearch(searchString,null,Integer.parseInt(params.max),Integer.parseInt(params.offset))
            usersCount = User.adminSearchCount(searchString,null)
        } else {
            if (filter.equalsIgnoreCase("active")) {
                allUsers = User.adminSearch(searchString,true,Integer.parseInt(params.max),Integer.parseInt(params.offset))
                usersCount = User.adminSearchCount(searchString,true)
            } else {
                allUsers = User.adminSearch(searchString,false,Integer.parseInt(params.max),Integer.parseInt(params.offset))
                usersCount = User.adminSearchCount(searchString,false)
            }

        }
        /*List<User> users = allUsers.findAll {
            !it.username.equalsIgnoreCase("admin")
        }*/

        render model: [users: allUsers,totalUsers:usersCount], view: "users"
    }

    def usersPaginate(String filter,String searchString) {
        List<User> allUsers
        Integer usersCount

        if (filter == "all") {
            allUsers = User.adminSearch(searchString,null,Integer.parseInt(params.max),Integer.parseInt(params.offset))
            usersCount = User.adminSearchCount(searchString,null)
        } else {
            if (filter.equalsIgnoreCase("active")) {
                allUsers = User.adminSearch(searchString,true,Integer.parseInt(params.max),Integer.parseInt(params.offset))
                usersCount = User.adminSearchCount(searchString,true)
            } else {
                allUsers = User.adminSearch(searchString,false,Integer.parseInt(params.max),Integer.parseInt(params.offset))
                usersCount = User.adminSearchCount(searchString,false)
            }

        }
        /*List<User> users = allUsers.findAll {
            !it.username.equalsIgnoreCase("admin")
        }*/

        Map map = [users: allUsers,totalUsers:usersCount,searchString:searchString]

        render map as JSON
    }

    def changeUserStatus(String status,Long id){
        User user = User.findById(id);
        if(status.equalsIgnoreCase("activate")){
            user.active = true
        }else{
            user.active=false;
        }
        try{
            user.confirmPassword = user.password
            user.save(flush:true,failOnError: true)
            render "success"
        }catch(Exception ex){
            log.error("Exception occurred while activating/deactivating user" + ex)
            render "failure"
        }
    }

}
