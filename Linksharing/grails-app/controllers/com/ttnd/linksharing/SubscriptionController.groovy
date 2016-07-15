package com.ttnd.linksharing

class SubscriptionController {

    def index() { }

    def save(Long topicId){
        Topic topic = Topic.get(topicId)
        Subscription subscription = new Subscription(topic: topic,user: session.user)
        try{
            subscription.save(flush:true,failOnError: true)
            render "Subscribed successfully"
        }catch(Exception ex){
            log.error("Exception occurred whhile subscribing" + ex)
            flash.error = "Exception occurred whhile subscribing" + ex
            render flash.error
        }
    }

    def delete(Long subscriptionId) {
        Subscription subscription = Subscription.get(subscriptionId)
        if(subscription){
            subscription.delete(flush: true)
            render "Subscription removed"
        }else{
            flash.error = "Subscription not found"
            render flash.error
        }
    }

    def update(Long subscriptionId,String seriousness){
        Subscription subscription = Subscription.findById(subscriptionId)
        if (subscription){
            subscription.seriousness = Seriouness.getSeriousnessFromString(seriousness)
            try{
                subscription.save(flush:true,failOnError: true)
                render "Subscription updated"
            }catch(Exception ex){
                log.error("Exception occurred while updating subscription" + ex)
                flash.error = "Exception occurred while updating subscription" + ex
                render flash.error
            }
        }
    }
}
