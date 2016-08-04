package com.ttnd.linksharing

import grails.converters.JSON

class SubscriptionController {

    def subscriptionService

    def index() {}

    def save(Long id) {
        Topic topic = Topic.get(id)
        Subscription subscription = new Subscription(topic: topic, user: session.user)
        try {
            subscription.save(flush: true, failOnError: true)
            flash.message = "Subscrption saved seccessfully"

        } catch (Exception ex) {
            log.error("Exception occurred whhile subscribing" + ex)
            flash.error = "Exception occurred while subscribing" + ex

        }
        render flash as JSON
    }

    def delete(Long id) {
        Topic topic = Topic.get(id)
        User user = User.get(session.user.id)
        if (topic.createdBy.id != user.id) {  //Creator of the topic should not be able to unsubscribe

            Subscription subscription = Subscription.findByTopicAndUser(topic, user)

            if (subscription) {
                try {

                    subscription.delete(flush: true, failOnError: true)
                    subscriptionService.removereadingItems(user,topic)
                    flash.message = "Subscription removed"
                } catch (Exception ex) {
                    log.error("Exception occurred while unsubscribing" + ex)
                    flash.message = "Exception"
                }
            } else {
                flash.error = "Subscription not found"

            }
        } else {
            flash.error = "Creator of the topic cannot unsubscribe the topic"
        }
        render flash as JSON
    }

    def update(Long id, String seriousness) {
        Subscription subscription = Subscription.findById(id)
        if (subscription) {
            subscription.seriousness = Seriouness.getSeriousnessFromString(seriousness)
            try {
                subscription.save(flush: true, failOnError: true)
                flash.message = "Subscription updated"
            } catch (Exception ex) {
                log.error("Exception occurred while updating subscription" + ex)
                flash.error = "Exception occurred while updating subscription" + ex

            }
            render flash as JSON
        }
    }
}
