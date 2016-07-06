package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
class LoginControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    @ConfineMetaClassChanges([User])
    def "user is  able to login, if username or password is  right" (){
        setup:
            Map params = [username: 'jalajtagra',password: 'linksharing']
            User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com',active: true)
         //  User.metaClass.static.findByUsernameAndPassword(_,_) >> user
        User.metaClass.static.find = {String str->
            return user
        }
        when:
            controller.params.username = 'jalajtagra'
            controller.params.password = 'linksharing'
            controller.loginhandler()
        then:
            response.getRedirectedUrl()=='/'
            session.user == user

    }

    @ConfineMetaClassChanges([User])
    def "user is  unable to login, if username or password is not right" (){
        setup:
        Map params = [username: 'jalajtagra',password: 'linksharing']
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com',active: false)
        //  User.metaClass.static.findByUsernameAndPassword(_,_) >> user
        User.metaClass.static.find = {String str->
            return null
        }
        when:
        controller.params.username = 'jalajtagra'
        controller.params.password = 'linksharing'
        controller.loginhandler()
        then:
        response.getContentAsString() == 'User not found'

    }

    @ConfineMetaClassChanges([User])
    def "user is  unable to login, if username or password is  right but user is not active" (){
        setup:
        Map params = [username: 'jalajtagra',password: 'linksharing']
        User user = new User(username: 'jalajtagra', password: 'ndsbfhj', firstName: 'Jalaj', lastName: 'Tagra',email: 'jalajtagra@yahoo.com',active: false)
        //  User.metaClass.static.findByUsernameAndPassword(_,_) >> user
        User.metaClass.static.find = {String str->
            return user
        }
        when:
        controller.params.username = 'jalajtagra'
        controller.params.password = 'linksharing'
        controller.loginhandler()
        then:
        response.getContentAsString() == 'Your account is not active'

    }
}
