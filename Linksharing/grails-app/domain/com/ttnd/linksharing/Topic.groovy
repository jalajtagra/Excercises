package com.ttnd.linksharing

class Topic {

    String name
    Visibility visibility
    Date dateCreated

    static belongsTo = [createdBy:User]

    static constraints = {
        name(blank: false,validator: {val,obj->
            if(Topic.findByNameAndCreatedBy(val,obj.createdBy) != null){
                return false;
            }else{
                return true;
            }

        })

    }



    static hasMany = [subscriptions:Subscription,
                        resources:Resource
    ]

    static mapping = {
        id : 'name'
        sort "name"
    }

    @Override
    String toString() {
        return name
    }

    static TopicVO getTopicsInfo(){

        List list= Resource.createCriteria().list(max:5){
            createAlias("topic",'res')
            projections{

                groupProperty("topic")
                property("topic.name")
                property("topic.createdBy")
                property("topic.visibility")
                countDistinct("id","count")
            }
            order("count",'desc')
        }

        List<TopicVO> topicVOs = []

        list.each{
            Topic topic = it[0]
            TopicVO topicVO = new TopicVO(name: it[1],createdBy: it[2],visibility: it[3])
            topicVOs.add(topicVO)
        }


        topicVOs
    }

/*def afterInsert(){
        Subscription
    }*/

}
