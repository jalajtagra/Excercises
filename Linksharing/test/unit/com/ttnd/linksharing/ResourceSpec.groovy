package com.ttnd.linksharing

import grails.test.mixin.TestFor
import org.grails.datastore.gorm.query.NamedCriteriaProxy
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Resource)
class ResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

   /* def "testing search named query for resources"(){
        setup:
            ResourceSearchCO co = new ResourceSearchCO(max: 5,offset: 0,q:'Dumm')
        when:
            NamedCriteriaProxy namedCriteriaProxy = Resource.search(co)
        then:
            namedCriteriaProxy.count() > 0
            namedCriteriaProxy.listDistinct().each {
                it.description.contains('Dumm')
            }

    }*/


}
