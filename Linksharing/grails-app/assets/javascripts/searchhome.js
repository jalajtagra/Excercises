/**
 * Created by ttnd on 31/7/16.
 */
$(document).ready(function(){



    $('#posts-pagination.pagination').jqPagination({


        paged: function(page) {
            $this = $(this)

            // do something with the page variable
            $.ajax("resource/search?offset="+5*(page-1) +"&max="+ 5 ,{
                data:{q: $("#searchbox").val()},
                success:function (data) {
                    $(".posts").html(data)


                    page.current_page = page.current_page+1;

                }
            })

            $.ajax("resource/searchCount",{
                data:{
                    q:$("#searchposts").val()
                },
                success:function (data) {
                    page.max_page = Math.ceil(data/5)
                }
            })
        }
    });


    $('#topics-pagination.pagination').jqPagination({


        paged: function(page) {
            $this = $(this)

            // do something with the page variable
            $.ajax("topic/search?offset="+5*(page-1) +"&max="+ 5 ,{
                data:{q: $("#searchbox").val()},
                success:function (data) {
                    $(".topics").html(data)


                    page.current_page = page.current_page+1;

                }
            })

            $.ajax("topic/searchCount",{
                data:{
                    q:$("#searchposts").val()
                },
                success:function (data) {
                    page.max_page = Math.ceil(data/5)
                }
            })
        }
    });

})

