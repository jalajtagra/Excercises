package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "resource rating is not saved when not between between 1 and 5" (){
        setup:
        User user = Mock(User)
        Topic topic = Mock(Topic)
        Resource resource = new LinkResource(url:'http://www.sdfjkf.com',description: 'Description',createdBy: user,topic: topic);

        when:
        ResourceRating resourceRating = new ResourceRating(resource:resource,user: user,rating: 6)
        resourceRating.save()
        then:
        ResourceRating.count==0
        resourceRating.errors.getAllErrors().size()==1
        resourceRating.errors.getFieldErrorCount('rating')==1

    }

    def "resource rating saved when between 1 and 5" (){
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        LinkResource linkResource = new LinkResource(createdBy: user,topic: topic,url:'http://www.google.com',description: 'dgfkh')
        linkResource.save()

        when:
        ResourceRating resourceRating = new ResourceRating(resource:linkResource,user: user,rating: 4)
        resourceRating.save()

        then:
        ResourceRating.count==0
        resourceRating.errors.getAllErrors().size()==1
        resourceRating.errors.getFieldErrorCount('rating')==1


    }

    def "resource rating should be unique per user and resource combination" (){
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        Topic topic = new Topic(createdBy: user,visibility: Visibility.Public,name: 'NEW')
        topic.save()
        Resource resource = new LinkResource(url:'http://www.sdfjkf.com',description: 'Description',createdBy: user,topic: topic);
        resource.save()
        when:
        ResourceRating resourceRating = new ResourceRating(resource:resource,user: user,rating: 4)
        resourceRating.save()
        then:
        ResourceRating.count==1
        when:
        ResourceRating resourceRating2 = new ResourceRating(resource:resource,user: user,rating: 4)
        resourceRating2.save()
        then:
        ResourceRating.count==1
       // resourceRating2.errors.getAllErrors().size()==1
       // resourceRating2.errors.getFieldErrorCount('resource')==1


    }
}
