package com.ttnd.linksharing

import org.hibernate.criterion.CriteriaSpecification


class User {

    String email
    String username
    String password
    String confirmPassword
    String firstName
    String lastName
    String name
    byte[] photo
//    String filePath
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
//        filePath(nullable: true)
    }

    static mapping = {
        photo sqlType: 'blob'
        sort id: 'desc'
        resources cascade: 'all-delete-orphan'
        readingItems cascade: 'all-delete-orphan'
//        resources lazy: false
//        subscriptions lazy:false
    }



    @Override
    String toString() {
        return username
    }

     List<ResourceVO> getUnReadResources(SearchCO searchCO){


        List list = ReadingItem.createCriteria().list(max:searchCO.max,offset:searchCO.offset) {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
//            createAlias("user","user")
            createAlias("resource","res")
           createAlias("resource.createdBy","createdBy")
            projections {
                property("res.id","id")
                property("res.description","description")
                property("res.createdBy","createdBy")
                property("res.topic","topic")
                property("res.url","url")

            }
            if(searchCO.q){
                ilike("res.description","%"+searchCO.q +"%")
            }
            eq("isRead",false)
            eq("user",this)
            order("description")

        }

       return  list.collect{
                if(it.url != null){
                    new LinkResourceVO(it)

                }else{

                    new DocumentResourceVO(id:it.id,description: it.description,topic: it.topic,createdBy: it.createdBy)
                }
         }
    }

    Integer getUnReadResourcesCount(SearchCO searchCO){


        List list = ReadingItem.createCriteria().list() {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
//            createAlias("user","user")
            createAlias("resource","res")
            createAlias("resource.createdBy","createdBy")
            projections {
                count("id","count")

            }
            if(searchCO.q){
                ilike("res.description","%"+searchCO.q +"%")
            }
            eq("isRead",false)

        }
        return list[0].count


    }

    String getName(){
        firstName+lastName
    }


     transient List<TopicVO> getTopSubscribedTopicsForUser( int max,int offset){
        List list =  Topic.createCriteria().list(max:max,offset:offset){
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
            order("resourceCount","desc")

        }

        List<TopicVO> listOfTopicVOs = []

        list.each {
            TopicVO topicVO = new TopicVO(id:it[0],name: it[1],createdBy: it[2],visibility: it[3])
            topicVO.count = it[4]
            topicVO.subscriptionCount=it[5]
            topicVO.subscriptions = Subscription.findAllByTopic(Topic.get(it[0]))
            listOfTopicVOs.add(topicVO)
        }

        return listOfTopicVOs
    }

    static List<User> adminSearch(String searchtString,Boolean active,int max,int offset){
        User.createCriteria().list(max:max,offset:offset){
            or {
                ilike("username","%${searchtString}%")
                ilike("firstName","%${searchtString}%")
                ilike("lastName","%${searchtString}%")
                ilike("email","%${searchtString}%")
            }
            if(active != null){
                eq("active",active)
            }
            notEqual("username","Admin")
           /* if(ilike("username","%${searchtString}%")){
                order(ilike("username","%${searchtString}%"))
            }
            if(ilike("firstName","%${searchtString}%")){
                order(ilike("firstName","%${searchtString}%"))
            }
            if(ilike("lastName","%${searchtString}%")){
                order(ilike("lastName","%${searchtString}%"))
            }
            if(ilike("email","%${searchtString}%")){
                order(ilike("email","%${searchtString}%"))
            }*/
            order("dateCreated")
        }
    }

    static Integer adminSearchCount(String searchtString,Boolean active){
        User.createCriteria().count(){
            or {
                ilike("username","%${searchtString}%")
                ilike("firstName","%${searchtString}%")
                ilike("lastName","%${searchtString}%")
                ilike("email","%${searchtString}%")
            }
            if(active != null){
                eq("active",active)
            }

            notEqual("username","Admin")

        }
    }





}