import com.ttnd.linksharing.RatingInfoVO
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceVO
import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import junit.framework.Test

/**
 * Created by ttnd on 15/7/16.
 */
class ResourceSpec extends IntegrationSpec {

    def "testing that resource getTopResources " (){
        setup:
//            List<Resource> resources = Resource.getTopResources()
        when:
            List<ResourceVO> resources = Resource.getTopResources()
        then:
            resources.head().filePath == 'dfsjkkjk'

    }

    def "getRatingInfo returns correct data" (){
        setup:
        Resource resource = Resource.findById(1)
        when:
        RatingInfoVO ratingInfoVO = resource.getRatingInfo()
        then:
        ratingInfoVO.totalScore == 1
        ratingInfoVO.avgScore == 1
        ratingInfoVO.totalVotes == 1
    }
}
