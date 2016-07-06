package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
class TopicSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "test name should be unique per user"() {
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        when:
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        then:
        Topic.count==1
        when:
        Topic topic2 = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic2.save()
        then:
        Topic.count==1
        topic2.errors.getAllErrors().size()==1
        topic2.errors.getFieldErrorCount('name')==1
    }

    def "testing toString method should return name"(){
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        Topic topic = new Topic(createdBy: user,name: "String Expected",visibility:Visibility.Public)
        topic.save()
        when:
        String output = topic.toString()
        then:
        output == 'String Expected'
    }
}
