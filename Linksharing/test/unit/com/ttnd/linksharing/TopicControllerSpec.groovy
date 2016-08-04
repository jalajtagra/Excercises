package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
@Mock(User)
class TopicControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "test something"() {
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save(flush: true)
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        when:
        topic.save(flush: true)
        then:
        Topic.count ==1
        when:
        controller.session.setAttribute("user",user)
        controller.delete(topic.id)
        then:
        Topic.count ==0



    }

}
