import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.SearchCO
import com.ttnd.linksharing.User
import grails.test.spock.IntegrationSpec

/**
 * Created by ttnd on 15/7/16.
 */
class UserSpec extends IntegrationSpec{
    //This test is not working strangely beacuse the same call works from the UI
    def "testing getUnreadResources method"(){
       setup:
        User user = User.get(1)
        when:
        List<ReadingItem> readingItems = user.getUnReadResources(new SearchCO(max:30,offset: 0,q:null))
        then:
        readingItems.size() == 20


    }
}
