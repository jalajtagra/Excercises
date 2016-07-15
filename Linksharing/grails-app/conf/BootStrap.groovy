import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Seriouness
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility

import javax.xml.bind.ValidationException

class BootStrap {

    def init = { servletContext ->
        if(User.count==0){
            createUsers();
        }
        if(Topic.count==0){
            createTopics()
           // subscribeTopics()

        }
        if(!Resource.count){
            createResources()
            createReadingItems()
            createResourceRatings()

        }

    }
    def destroy = {
    }

    def createUsers(){

        User user = new User(username: 'newuser',password: 'Password',confirmPassword: 'Password', firstName: 'New',lastName: 'User',email:'newuser@gmail.com',active: true,admin:false);
        User admin = new User(username: 'Admin',password: 'Adminn',confirmPassword: 'Adminn',firstName: 'Jalaj',lastName: 'Tagra',email: 'jalajtagra786@gmail.com',active: true,admin: true)
        try{

            user.save(failOnError: true,flush: true)
            admin.save(failOnError: true,flush: true)
            log.info("Users saved successfully")
        }catch(Exception e){
            log.error("Exception caught while saving users " + e.printStackTrace())
        }


    }

    def createTopics(){
        if(Topic.count==0) {
            User user = User.findByUsername("newuser")
            User admin = User.findByUsername("Admin")
            if (user) {

                Topic topic = new Topic(name: "Dummy Topic", createdBy: user, visibility: Visibility.Public)
                Topic topic2 = new Topic(name: "Dummy Topic2", createdBy: user, visibility: Visibility.Public)
                Topic topic3 = new Topic(name: "Dummy Topic3", createdBy: user, visibility: Visibility.Public)
                Topic topic4 = new Topic(name: "Dummy Topic4", createdBy: user, visibility: Visibility.Public)
                Topic topic5 = new Topic(name: "Dummy Topic5", createdBy: user, visibility: Visibility.Public)



                Topic admintopic = new Topic(name: "Admin Topic", createdBy: admin, visibility: Visibility.Public)
                Topic admintopic2 = new Topic(name: "Admin Topic2", createdBy: admin, visibility: Visibility.Public)
                Topic admintopic3 = new Topic(name: "Admin Topic3", createdBy: admin, visibility: Visibility.Public)
                Topic admintopic4 = new Topic(name: "Admin Topic4", createdBy: admin, visibility: Visibility.Public)
                Topic admintopic5 = new Topic(name: "Admin Topic5", createdBy: admin, visibility: Visibility.Public)



                try{
                topic.save(failOnError: true,flush: true)
                topic2.save(failOnError: true,flush: true)
                topic3.save(failOnError: true,flush: true)
                topic4.save(failOnError: true,flush: true)
                topic5.save(failOnError: true,flush: true)

                admintopic.save(failOnError: true,flush: true)
                admintopic2.save(failOnError: true,flush: true)
                admintopic3.save(failOnError: true,flush: true)
                admintopic4.save(failOnError: true,flush: true)
                admintopic5.save(failOnError: true,flush: true)
                    log.info("Topics saved successfully")


                }catch(ValidationException ex){
                    log.error("Exception caught while saving topics " + ex.printStackTrace())
                }
               /* createOwnerSubscriptions(topic,user)
                createOwnerSubscriptions(topic2,user)
                createOwnerSubscriptions(topic3,user)
                createOwnerSubscriptions(topic4,user)
                createOwnerSubscriptions(topic5,user)
                createOwnerSubscriptions(admintopic,admin)
                createOwnerSubscriptions(admintopic2,admin)
                createOwnerSubscriptions(admintopic3,admin)
                createOwnerSubscriptions(admintopic4,admin)
                createOwnerSubscriptions(admintopic5,admin)*/
            }
        }
    }

    def createOwnerSubscriptions(Topic topic,User user) {
        Subscription subscription =  new Subscription(user: user,topic: topic, seriousness: Seriouness.Very_Serious)
        try{
            subscription.save(failOnError: true,flush: true)
            log.info("Subscription saved")
        }catch(ValidationException ex){
            log.error("Exception caught while saving subscription "+ex.printStackTrace())
        }
    }

    def createResources(){
        List<Topic> topics = Topic.findAll()

        topics.each {

            Resource linkresource = new LinkResource(url:'http://www.google.com',description: it.name, createdBy: it.createdBy,topic: it)
            Resource linkresource2 = new LinkResource(url:'http://www.amazon.com',description: it.name, createdBy: it.createdBy,topic: it)

            Resource documentResource = new DocumentResource(filePath: 'dfsjkkjk',description: it.name,createdBy: it.createdBy,topic: it,contentType: 'application/pdf')
            Resource documentResource2 = new DocumentResource(filePath: 'cxbvzmbmcvm',description: it.name,createdBy: it.createdBy,topic: it,contentType: 'application/pdf')
            try{
                linkresource.save(failOnError: true,flush: true)
                linkresource2.save(failOnError: true,flush: true)
                documentResource.save(failOnError: true,flush: true)
                documentResource2.save(failOnError: true,flush: true)
                log.info("Resource saved successfully" )
            }catch(ValidationException ex){
                log.error("Exception caught while saving resource "+ex.printStackTrace())
            }
        }
    }

    def subscribeTopics() {
        User user = User.findByUsername('newuser')
        List<Topic> topics = Topic.findAll('from Topic where createdBy != :user',[user:user] )
        topics.each {
            Subscription subscription = new Subscription(topic: it,user:user, seriousness: Seriouness.Serious)
            try{
                subscription.save(failOnError: true,flush: true)
            }catch(ValidationException ex){
                log.error("Exception caught while subscribing to topic "+ex.printStackTrace())
            }
        }
    }

    def createReadingItems() {
        User user = User.findByUsername('newuser')
        List<Resource> resources = Resource.findAll('from Resource where createdBy != :user',[user:user])
        resources.each {
            ReadingItem readingItem = new ReadingItem(resource: it,user: user,isRead: false)
            try{
                readingItem.save(failOnError: true,flush: true)
                log.info("Reading Item saved successfully")
            }catch(Exception ex){
                log.error("Exception caught while creating reading items "+ex.printStackTrace())
            }

        }

    }

    def createResourceRatings() {
        User user = User.findByUsername('newuser')
        List<ReadingItem> unreadReadingItems = ReadingItem.findAll("from ReadingItem  where  user = :user and isRead = false ",[user:user])
        println "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ${unreadReadingItems.size()}>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        unreadReadingItems.each {
            int x = ((Integer)(Math.random()*5))
            ResourceRating resourceRating = new ResourceRating(resource: it.resource,user: user,rating:x ==0?1:x)
            try{
                resourceRating.save(flush: true,failOnError: true)
                log.info("Resource rating saved successfully")
            }catch (Exception ex){
                log.error("Exception caught while creating resource rating "+ex.printStackTrace())
            }
        }
    }



}
