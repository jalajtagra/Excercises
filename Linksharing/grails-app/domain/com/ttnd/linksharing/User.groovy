package com.ttnd.linksharing


class User {

    String email
    String username
    String password
    String confirmPassword
    String firstName
    String lastName
    String name
    byte[] photo
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated

    static transients = ['confirmPassword','name']


    static hasMany = [topics:Topic,
            resources:Resource,
            readingItems:ReadingItem,
            subscriptions:Subscription
    ]




    static constraints = {
        email(unique: true, email: true)
        password(size: 5..15,nullable: false,blank: false)
        username(unique: true,nullable: false)
       confirmPassword(bindable:true,nullable: true, validator: {val,obj->
           if(val == obj.password){

               println "-----------------------------${val}--------------${obj.password}---------------"
              return true
           }else{
               println "-----------------------------${val}--------------${obj.password}---------------"
              return  false
           }
       })
        firstName(nullable: false,blank:false)
        username(nullable: false,blank: false,unique: true)
        lastName(nullable: false, blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)

    }

    static mapping = {
        photo type:'blob'
        sort id: 'desc'
    }



    @Override
    String toString() {
        return username
    }

     List<ReadingItem> getUnReadResources(SearchCO searchCO){
        ReadingItem.createCriteria().list(max:searchCO.max,offset:searchCO.offset) {
            createAlias("user","user")
            createAlias("resource","resource")
            projections {

                property("resource.description")
                property("resource.createdBy")
                property("resource.topic")

            }
            if(searchCO.q){
                like("resource.description","%"+searchCO.q +"%")
            }
            eq("isRead",false)

        }

    }

    String getName(){
        firstName+lastName
    }


}