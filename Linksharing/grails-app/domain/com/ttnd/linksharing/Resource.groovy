package com.ttnd.linksharing

abstract class Resource {

    String description
    User createdBy
    Topic topic
    RatingInfoVO ratingInfoVO
    Date dateCreated
    Date lastUpdated

    static hasMany = [resourceRatings:ResourceRating]

    static transients = ['ratingInfoVO']

    static mapping = {
        discriminator column:"content_type",
                description:'text'
    }


    static belongsTo = [createdBy:User]

    RatingInfoVO getRatingInfo(){
        RatingInfoVO ratingInfoVO1 = new RatingInfoVO()

        Long totalScore = ResourceRating.createCriteria().sum("rating") {

            eq("resource",this)

        }

        Long noOfRatings = ResourceRating.createCriteria().count(){

            eq("resource",this)

        }

        ratingInfoVO1.setTotalVotes(noOfRatings)
        ratingInfoVO1.setTotalScore(totalScore)
        ratingInfoVO1.setAvgScore(totalScore/noOfRatings)

        return ratingInfoVO1

    }

    static List getTopResources(){
       List list = ResourceRating.createCriteria().list(max:5){
            createAlias("resource","resource")
            projections{
                groupProperty("resource")
                property("resource.description")
                property("resource.createdBy")
                rowCount("totalRatings")
            }
            order("totalRatings","desc")
        }

        list.each{
            println list.get(0).description
        }
    }


}


