/**
 * Created by ttnd on 30/6/16.
 */

log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //

//    root{
//        info 'stdout'
//    }

    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }

//    info 'com.ttnd.liksharing.UtilcontrollerController'


    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

    info    'grails.app',
            'grails.app.controllers.com.ttnd.liksharing.UtilcontrollerController'

}


