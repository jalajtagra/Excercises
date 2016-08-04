/**
 * Created by ttnd on 28/7/16.
 */
$(document).ready(function () {



    $("#forgot-passwd").click(function () {
        $("#registration-form").html("<form class= \"form-horizontal\" id=\"forgot-password-form\" role=\"form\" action=\"login/forgotPassword\">" +

            "<div class=\"form-group\" style = \"padding-left: 5px;padding-right:5px\" >" +
            "<label class = \"control-label col-md-4\" for=\"email\" style = \"padding: 5px ; text-align: left\">Username:</label>" +
            "<div class=\"col-md-8\">" +
            "<input type=\"text\" class=\"form-control\" name=\"username\" id=\"email\" style = \"padding: 5px\"  >" +
            "</div>" +
            "</div>" +

            "<div class=\"col-md-4\"></div>" +
            "<div classs=\"col-md-8\">" +
            // "<a style = \"padding: 5px;align-items: center\" id=\"forgot-passwd\">Forgot Password</a>"+
            "<button type=\"submit\" class=\"btn btn-default\" style = \"margin: 5px \">Submit</button>" +

            "</div>" +
            "</form>")




        $("#forgot-password-form").validate({
            rules:{
                username:{
                    required:true
                }

            }
        })

    })




})
