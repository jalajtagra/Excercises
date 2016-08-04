$(document).ready(function(){

   /* $.post("/newcontext/user/image/3",function (data,status) {
        $(".user-dp").prop("src","${asset.assetPath(src: 'defaults/"+data+"?"+Math.random()+"')}");
    });*//*
    var subscribed = Cookies.get('subscribed');
    if (subscribed != "") {
        $.notify(subscribed)
        // delete the cookie
        Cookies.set('subscribed', null);
    }*/



   /* function clickPaginate(event){
        $this = $(this)
        event.preventDefault()
        var link = event.toElement
        if(link.href == null){
            return;
        }

       /!* new Ajax.Updater(
            { success: $('ajax_wrap') },
            link.href,
            {
                evalScripts: true
            });*!/
        
        $.ajax(link.href,{
            success:function (data) {
                $("#ajax_wrap .list").html(data)
                $("#ajax_wrap .paginateButtons")
                if(link.href.split("?").split("&")){}
                $this.attr("enabled","true")

            }
        })
    }
    $(".step ,.prevLink, .nextLink").click(function (e) {


        clickPaginate(e)
    })*/

    $('#inbox.pagination').jqPagination({

        paged: function(page) {
            $this = $(this)

            // do something with the page variable
            $.ajax("user/getUnreadResources?offset="+5*(page-1) +"&max="+ 5 ,{
                data:{q:$("#searchposts").val()},
                success:function (data) {
                    $(".inbox-list").html(data)


                    page.current_page = page.current_page+1;

                }
            })

            $.ajax("user/getUnreadResourcesCount",{
                data:{
                    q:$("#searchposts").val()
                },
                success:function (data) {
                    page.max_page = Math.ceil(data/5)
                }
            })
        }
    });

    $('#trending-topic-pagination.pagination').jqPagination({

        paged: function(page) {
            $this = $(this)

            // do something with the page variable
            $.ajax("user/trendingTopics?offset="+5*(page-1) +"&max="+ 5 ,{
                success:function (data) {
                    $("#trending-topics .list").html(data)


                    page.current_page = page.current_page+1;

                }
            })
        }
    });

    $(' #subscriptions-pagination.pagination').jqPagination({
       
        paged: function(page) {
            $this = $(this)

            // do something with the page variable
            $.ajax("user/getTopSubscribedTopicsForUser?offset="+5*(page-1) +"&max="+ 5,{
                success:function (data) {
                    $("#subscriptions-pagination .list").html(data)


                    page.current_page = page.current_page+1;

                }
            })
        }
    });



   
    
    

    $(".glyphicon-trash .subscriptionicons").click(function () {
        $.post("topic/delete",
            {
                id:$(this).attr("id"),


            },
            function(data, status){
                var mes
                if(data.message == undefined){
                    mes = data.error
                }else{
                    mes = data.message
                }
                alert("Data: " + mes + "\nStatus: " + status);
                location.reload()
            });

    })

    /*jQuery.validator.addMethod("validUsername",function(value,element){
     $.ajax(
     {
     type: "POST",
     url: "user/validateUsername",
     dataType: "json",
     data: {username : value},
     async:false,
     success: function(data)
     {
     if (data.message !== true)
     {
     return false;
     }
     else
     {
     return true;
     }
     },
     error: function(xhr, textStatus, errorThrown)
     {
     alert('ajax loading error... ... '+url + query);
     return false;
     }
     })

     },"This username address is already registered.")
     */



    

    $('#searchposts').keyup(function(e){
        if(e.which == 13) {
            $.post("user/getUnreadResources",
                {
                    q: $("#searchposts").val(),
                    max:5,
                    offset:0
                }

                , function (data, status) {
                     $(".inbox-list").html(data)
                })

            $.get("user/getUnreadResourcesCount",
            {
                q: $("#searchposts").val()
            },
            function (data) {
                $(".inbox-list").siblings(".pagination").find("input").attr("data-max-page",Math.ceil(data/5))
               $(".inbox-list").siblings(".pagination").find("input").val("Page 1 of " + Math.ceil(data/5))
                if(Math.ceil(data/5) <2){
                    $(".inbox-list").siblings(".pagination").find("input").siblings().addClass("disabled")
                }else{
                    $(".inbox-list").siblings(".pagination").find("input").nextAll().removeClass("disabled")
                }
                
            })
        }
    })

    



})