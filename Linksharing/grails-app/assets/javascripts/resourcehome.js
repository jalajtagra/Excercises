/**
 * Created by ttnd on 26/7/16.
 */


$(document).ready(function(){
    var setDefaultRating = function (){
        var colored = $(".fa-heart")
        colored.removeClass("fa-heart")
        colored.addClass("fa-heart-o")

        $this = $(".fa-heart-o").first()
        for(i=0;i<$(".rating").attr("rating");i++){
            $this.first().removeClass("fa-heart-o")
            $this.addClass("fa-heart")

            $this = $this.next()
        }

    }

    setDefaultRating()
    $("body").on("mouseover",".fa-heart-o",function(){

        $(this).removeClass("fa-heart-o")
        $(this).addClass("fa-heart")
        $(this).prevAll().removeClass("fa-heart-o")
        $(this).prevAll().addClass("fa-heart")

    })

    $("body").on("mouseover",".fa-heart",function () {
        $(this).nextAll("i").removeClass("fa-heart")
        $(this).nextAll("i").addClass("fa-heart-o")
    })

    $("body").on("mouseout",".fa-heart",function () {

        setDefaultRating()

    })
    $("body").on("click",".fa-heart",function () {
        $(this).nextAll("i").removeClass("fa-heart")
        $(this).nextAll("i").addClass("fa-heart-o")

        var rating = $(".fa-heart").length
        var $this = $(this)

        $.ajax({
            url:"resourceRating/saveRating",
            dataType:"json",
            data:{
                id:$(this).parent().attr("id"),
                rating:rating
            },
            success:function (data) {
                if(data.rating != null){
                    $(".rating").attr("rating",rating)
                    setDefaultRating()
                    $(".totalVotes").text(data.totalVotes)
                    $.notify("Rating submitted successfully","success")
                }
            },
            failure:function () {
                $.notify("Sorry,rating could not be submitted","failure")
                setDefaultRating()
            }
        })




    })

    $(".edit-description").click(function () {
        $("#updateDescription").modal()
    })


    var options = {
        success:function (data) {
            $.notify(data.message,"success");
        }
    }

    $(".update-description-form").ajaxForm()




})



