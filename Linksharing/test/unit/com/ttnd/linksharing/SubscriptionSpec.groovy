package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "Subscription for a user and topic should be one"(){
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()

        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        when:
        Subscription subscription = new Subscription(user: user,topic: topic, seriousness: Seriouness.Casual)
        subscription.save()
        then:
        Subscription.count==1
        when:
        Subscription subscription2 = new Subscription(user: user,topic: topic, seriousness: Seriouness.Very_Serious)
        subscription2.save()
        then:
        Subscription.count==1
//        Subscription.get(subscription.id).seriousness == Seriouness.Very_Serious
    }
}
