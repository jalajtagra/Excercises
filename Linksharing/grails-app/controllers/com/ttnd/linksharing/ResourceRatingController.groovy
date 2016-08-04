package com.ttnd.linksharing

import grails.converters.JSON

class ResourceRatingController {

    def index() { }

    def saveRating(Long id,Integer rating){
        User loggedInUser = User.get(session.user.id)
        Resource resource = Resource.get(id)
        ResourceRating resourceRating = ResourceRating.findByUserAndResource(loggedInUser,resource)

        if(resourceRating){
            resourceRating.setRating(rating)
        }else{
            resourceRating = new ResourceRating(user: loggedInUser,resource: resource,rating: rating)
        }
        try{
            resourceRating.save(flush: true,failOnError: true)
            Map map = [rating:resource.getRatingInfo().avgScore,totalVotes:resource.getRatingInfo().totalVotes]
            render map as JSON
        }catch(Exception ex){
            log.error("Exception occurred while submitting resource rating" + ex)
            render "failure"
        }
    }
}
