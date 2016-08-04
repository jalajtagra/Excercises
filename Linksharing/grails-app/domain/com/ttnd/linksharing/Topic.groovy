package com.ttnd.linksharing

class Topic {

    String name
    Visibility visibility
    Date dateCreated

    static belongsTo = [createdBy:User]

    static constraints = {
      /*  name(blank: false,validator: {val,obj->
            if(Topic.findByNameAndCreatedBy(val,obj.createdBy) != null){
                return false;
            }else{
                return true;
            }

        })*/
        name unique: "createdBy"

    }



    static hasMany = [subscriptions:Subscription,
                        resources:Resource
    ]

    static mapping = {
//        id : 'name'
        sort name: "asc"
      //  subscriptions lazy: false
        createdBy lazy:false
    }

    @Override
    String toString() {
        return name
    }

    def afterInsert(){

        Subscription subscription = new Subscription(topic: this,user:createdBy, seriousness: Seriouness.Very_Serious)
        withNewSession {
            subscription.save()
        }
    }

    static List<TopicVO> getTopicsInfo(int max,int offset){

        List list= Resource.createCriteria().list(max:max,offset:offset){
            createAlias("topic",'topic')
            //createAlias("subscription","subscription")
            projections{

                groupProperty("topic")
                property("topic.name")
                property("topic.createdBy")
                property("topic.visibility")
                property("topic.id")
                countDistinct("id","count")

            }
            order("count",'desc')
        }

        List<TopicVO> topicVOs = []

        list.each{
            Topic topic = it[0]
            TopicVO topicVO = new TopicVO(name: it[1],createdBy: it[2],visibility: it[3],count: it[5],id:it[4],subscriptionCount: Subscription.findAllByTopic(topic).size())
            topicVO.subscriptions = Subscription.findAllByTopic(Topic.get(it[4]))
            topicVOs.add(topicVO)
        }


        topicVOs
    }


    transient List<User> getSubscribedUsers(){
        List<User> subscribingUsers = []
        subscriptions.each{
            subscribingUsers.add(it.user)
        }
        subscribingUsers
    }

    transient static List<Topic> searchTopics(String searchString,int max, int offset){
        Topic.createCriteria().list(max:max,offset:offset) {
            ilike("name","%" +searchString +"%")
            eq("visibility",Visibility.Public)
            order("name")
        }
    }

    static Long searchCount(String searchString){
        Topic.createCriteria().count() {

            ilike("name","%" +searchString +"%")
            eq("visibility",Visibility.Public)

        }
    }



}
