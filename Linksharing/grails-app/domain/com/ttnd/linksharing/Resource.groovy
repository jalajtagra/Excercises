package com.ttnd.linksharing

abstract class Resource {

    String description
    User createdBy
    Topic topic
    RatingInfoVO ratingInfoVO
    Date dateCreated
    Date lastUpdated

//    static final Integer DEFAULT_MAX = 10
//    static final Integer DEFAULT_OFFSET = 0
//    static final DEFAULT_SORT_COLUMN = 'dateCreated'
//    static final DEFAULT_SORT_DIRECTION = 'desc'


    static hasMany = [resourceRatings: ResourceRating]

    static transients = ['ratingInfoVO']

    static mapping = {
        discriminator column: "content_type",
                description: 'text'
        createdBy lazy: false
    }


    static belongsTo = [createdBy: User]

    transient RatingInfoVO getRatingInfo() {
        RatingInfoVO ratingInfoVO1 = new RatingInfoVO()

        Long totalRating = ResourceRating.createCriteria().get {
            projections {
                sum("rating")
            }
            eq('resource', this)

        }

        Long noOfRatings = ResourceRating.createCriteria().count() {

            eq("resource", this)

        }

        ratingInfoVO1.setTotalVotes(noOfRatings)
        ratingInfoVO1.setTotalScore(totalRating)
        ratingInfoVO1.setAvgScore(totalRating / noOfRatings)

        return ratingInfoVO1

    }

    static List<ResourceVO> getTopResources() {
        List list = ResourceRating.createCriteria().list(max: 5) {
            createAlias("resource", "resource")
            createAlias("resource.topic", "topic")
            projections {
                groupProperty("resource")
                property("resource.id")
                property("resource.description")
                property("resource.createdBy")
//                property("resource.dis")
                    property("resource.filePath")
                property("resource.url")
                rowCount("totalRatings")
            }
            eq("topic.visibility", Visibility.Public)
            order("totalRatings", "desc")
        }

        List<ResourceVO> resources = [];

        list.each {
            if (it[4] != null) {

                resources.add(new DocumentResourceVO(id: it[1], description: it[2], createdBy: it[3], filePath: it[4]))
            } else {
                resources.add(new LinkResourceVO(id: it[1], description: it[2], createdBy: it[3], url: it[5]))
            }
        }

        return resources
    }

    static namedQueries = {
        search { ResourceSearchCO co ->
            createAlias("topic", "top")


            if (co.q)
                ilike("description", "%${co.q}%")
            if (co.topicId) {
//                        'in'('topic',Topic.findAll("from Topic where visibility = :visibility and id=:id",[visibility:Visibility.Public,id:co.topicId]))
                eq('top.id', co.topicId)
            }
            if (co.visibility) {
                eq('top.visibility', Visibility.getVisibilityFromString(co.visibility))
            }
            if (co.order && co.sort) {
                order(co.order, co.sort)
            } else {
                order('dateCreated', 'desc')
            }
            if (co.max && (co.offset != null)) {
                maxResults co.max
                firstResult co.offset
            } else {
                maxResults 10
                firstResult 0
            }


        }
    }

    void delete() {

    }


}


