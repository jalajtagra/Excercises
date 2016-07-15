package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([User,Topic])
class SubscriptionControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "subscription to a topic should be saved with default seriousness"(){
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        when:
        controller.save(topic.id)
        then:
        Subscription.count==1
        Subscription.findAll().head().seriousness == Seriouness.Serious

    }

    def "Subscription should be updated"(){
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        controller.save(topic.id)
        when:
        controller.update(Subscription.findAll().head().id,Seriouness.SERIOUSNESS_VERY_SERIOUS)
        then:
        Subscription.count==1
        Subscription.findById(Subscription.findAll().head().id).seriousness == Seriouness.Very_Serious
    }

    def "subscription should be deleted when user unsubscribes"(){
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        controller.save(topic.id)
        when:
        controller.delete(Subscription.findAll().head().id)
        then:
        Subscription.count==0
    }

}
