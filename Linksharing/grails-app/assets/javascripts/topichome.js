
/**
 * Created by ttnd on 29/7/16.
 */

$(document).ready(function(){
    $("#searchposts").keyup(function (e) {
        $this = $(this)
        if(e.which == 13) {
            $.ajax("resource/search",{
                data:{
                    q: e.target.value,
                    topicId:$(".post-list").attr("id")
                    
                },
                success:function (data) {
                    $(".post-list").html(data)
                }
            })
        }
       
    })
})
