package com.ttnd.linksharing

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

/**
 * Created by ttnd on 12/7/16.
 */


@Validateable
class UserCommand {

    String email
    String username
    String password
    String confirmPassword
    String firstName
    String lastName
    String name
    MultipartFile profilePhoto
    byte[] photo
    Boolean admin
    Boolean active


    static constraints={
        importFrom User, exclude: ['confirmPassword']
        confirmPassword(bindable:true, validator: {val,obj->
            if(val==null){
                return false
            }
            if(val == obj.password){


                return true
            }else{

                return  'registeruser.confirmPassword.error'
            }
        })
    }
}
