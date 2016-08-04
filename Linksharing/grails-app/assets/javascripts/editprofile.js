/**
 * Created by ttnd on 25/7/16.
 */
$(document).ready(function(){
    $("#updateProfile").validate({
        rules:{
            firstName:{
                required:true
            },
            lastName:{
                required:true
            },
            username:{
                required:true
            }
        }
    })

    jQuery.validator.addMethod("matchesPassword",function(value,element){
        if(value == $("#changePassword").find("#pwdreg").val()){
            return true
        }else{
            return false
        }
    })

    $("#changePassword").validate({
        rules:{
            password:{
                required:true,
                minlength:5,
                maxlength:15
            },
            confirmPassword:{
                required:true,
                matchesPassword:true,
                minlength:5,
                maxlength:15
            }

        }
        ,
        messages:{
            confirmPassword:"Confirm Password should match Password"
        }
    })
})