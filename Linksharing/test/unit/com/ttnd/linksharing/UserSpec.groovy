package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {

    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "email should be email "() {
        setup:
        User user = new User(email: emailId, firstName: "Jalaj", lastName: "Tagra", password: "dsfkhk", username: "jalajtagra");
        when:
        Boolean result = user.validate()
        then:
        result == output
        where:
        emailId                     | output
        "jalaj@.com"                | false
        "vikas.kumar1@tothenew.com" | true


    }

    def "email should be email and unique"() {
        setup:
        User user = new User(email: 'jalajtagra@yahoo.com', firstName: "Jalaj", lastName: "Tagra", password: "dsfkhk", username: "jalajtagra");
        when:
        user.save();
        then:
        User.count == 1
        when:
        User user1 = new User(email: 'jalajtagra@yahoo.com', firstName: "Jalaj", lastName: "Tagra", password: "dsfkwhk", username: "jalajtagraf");
        user1.save();
        then:
        User.count == 1
        user1.errors.allErrors.size() == 1
        user1.errors.getFieldErrorCount('email') == 1


    }

    def "Username of User should be unique"() {
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        when:
        user.save()
        then:
        User.count==1
        when:
        User user2 = new User(username: 'jalajtagra', password: 'ndsbfdhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra786@gmail.com')
        user2.save()
        then:
        User.count==1
        user2.errors.allErrors.size()==1
        user2.errors.getFieldErrorCount('username')==1
    }

    def "user is found using username and password"() {
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        when:
        User user2 = User.findByUsernameAndPassword('jalajtagra','ndsbfhj')
        then:
        user2.firstName == 'Jalaj'

    }

    def "toString method should return username of the user" () {
        setup:
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj',confirmPassword: 'ndsbfhj',firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com')
        user.save()
        when:
        String output = user.toString()
        then:
        output == 'jalajtagra'
    }
}
