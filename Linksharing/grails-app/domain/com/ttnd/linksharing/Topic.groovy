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
    }

    @Override
    String toString() {
        return name
    }
/*def afterInsert(){
        Subscription
    }*/

}
