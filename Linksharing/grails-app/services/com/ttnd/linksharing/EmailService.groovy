package com.ttnd.linksharing

import grails.transaction.Transactional

@Transactional
class EmailService {

    def mailService

    def serviceMethod() {

    }

    def sendMail(String emailId,String fromEmailId,Long topicId){
        Topic topic = Topic.findById(topicId)
        mailService.sendMail {
            from fromEmailId
            to emailId
            subject "Invitation for subscription of topic ${topic.name}"
//body
            html "<b>Hi</b> <br/>" +
                    "Click <a href='http://localhost:8080/LinkSharing/subscription/subscribeViaMail?emailId=${emailId}&topicId=${topicId}'> here </a> to subscribe to ${topic.name}"
        }
        return "Mail sent successfully"
    }

    def sendPasswordMail(String emailId,String username, String password) {
        mailService.sendMail {

            to emailId
            subject "Password Information for Linksharing"
//body
            html "<b>Hi ${username}</b> <br/>" +
                    " Your account password is ${password}"
        }
        return "Mail sent successfully"
    }
}
