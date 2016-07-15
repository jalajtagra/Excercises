package com.ttnd.linkshring

import grails.test.mixin.TestFor
import org.apache.commons.lang.StringEscapeUtils
import org.codehaus.groovy.grails.web.util.StreamCharBuffer
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(CustomTagLib)
class CustomTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "custom tag returns img tag"() {
        when:
        StreamCharBuffer streamCharBuffer = tagLib.custom.userImage([userId:1, height:20, width:50],'')
        String str = StringEscapeUtils.unescapeHtml(streamCharBuffer.toString())

        then:
        str == "<img src='/user/img/1' height='20px' width='50px'/>"


    }
}
