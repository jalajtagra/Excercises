package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "Link Resource should be saved"() {
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        LinkResource linkResource = new LinkResource(createdBy: user,topic: topic,url:'http://www.google.com',description: 'dgfkh')
        when:
        linkResource.save()
        then:
        LinkResource.count()==1
    }

    def "toString method should return url of the link resource"() {
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        LinkResource linkResource = new LinkResource(createdBy: user,topic: topic,url:'http://www.google.com',description: 'dgfkh')
        linkResource.save()
        when:
        String output = linkResource.toString()
        then:
        output == 'http://www.google.com'
    }
}
