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


              return true
           }else{

              return  'registeruser.confirmPassword.error'
           }
       })
        firstName(nullable: false,blank:false)
        username(nullable: false,blank: false,unique: true)
        lastName(nullable: false, blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        name(nullable: true)
    }

    static mapping = {
        photo type:'blob'
        sort id: 'desc'
        resources lazy: false
        subscriptions lazy:false
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
                ilike("resource.description","%"+searchCO.q +"%")
            }
            eq("isRead",false)

        }

    }

    String getName(){
        firstName+lastName
    }


     transient List<TopicVO> getTopSubscribedTopicsForUser( int max){
        List list =  Topic.createCriteria().list(max:max){
            createAlias("resources","resource")
            createAlias("subscriptions","subscription")
            projections {
                groupProperty("id")
                property("name")
                property("createdBy")
                property("visibility")
                countDistinct("resource.id","resourceCount")
                countDistinct("subscription.id","subscriptionCount")

            }
            eq("subscription.user",this)

        }

        List<TopicVO> listOfTopicVOs = []

        list.each {
            TopicVO topicVO = new TopicVO(id:it[0],name: it[1],createdBy: it[2],visibility: it[3])
            topicVO.count = it[4]
            topicVO.subscriptionCount=it[5]
            listOfTopicVOs.add(topicVO)
        }

        return listOfTopicVOs
    }


}