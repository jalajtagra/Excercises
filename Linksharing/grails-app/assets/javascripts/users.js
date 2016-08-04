/**
 * Created by ttnd on 1/8/16.
 */

$(document).ready(function(){

    $('#users-pagination.pagination').jqPagination({


        paged: function(page) {
            $this = $(this)

            // do something with the page variable
            $.ajax("admin/usersPaginate?offset="+5*(page-1) +"&max="+ 5 ,{
                dataType:"json",
                data:{searchString: $("#searchbox").val(),
                      filter:$("#filter").val()
                },
                success:function (data) {

                    var userData= "";
                    for(var i=0;i<data.users.length;i++){
                        var activate ;
                        if(data.users[i].active ==0){activate = "Activate"}else{activate = "Deactivate"}
                        userData = userData +"<tr class=\"user-row\">"+
                        "<td>"+ data.users[i].id +"</td>"+
                        "<td>"+data.users[i].username + "</td>"+
                        "<td>"+data.users[i].email+"</td>"+
                        "<td>"+data.users[i].firstName+"</td>"+
                        "<td>"+data.users[i].lastName+"</td>"+
                        "<td>" +data.users[i].active+"</td>"+
                        "<td ><span class=\"span-mouseover\">"+activate+"</span></td>"+
                            "</tr>";
                    }
                    $(".users-list").html(userData)
                    page.max_page = Math.ceil(data.totalUsers/1);


                    page.current_page = page.current_page+1;

                }
            })


          /*  $.ajax("admin/usersCount",{
                data:{
                    q:$("#searchposts").val()
                },
                success:function (data) {
                    page.max_page = Math.ceil(data/5)
                }
            })*/
        }
    });

    var optionss = {
        success:function (data) {

            var userData= "";
            for(var i=0;i<data.users.length;i++){
                var activate ;
                if(data.users[i].active ==0){activate = "Activate"}else{activate = "Deactivate"}
                userData = userData +"<tr class=\"user-row\">"+
                    "<td>"+ data.users[i].id +"</td>"+
                    "<td>"+data.users[i].username + "</td>"+
                    "<td>"+data.users[i].email+"</td>"+
                    "<td>"+data.users[i].firstName+"</td>"+
                    "<td>"+data.users[i].lastName+"</td>"+
                    "<td>" +data.users[i].active+"</td>"+
                    "<td ><span class=\"span-mouseover\">"+activate+"</span></td>"+
                    "</tr>";
            }
            $(".users-list").html(userData)

        }
    }

    $("#users-form").ajaxForm(optionss);

    $(".activate").click(function () {
        var action = $(this).text();
        $this = $(this)
        $.ajax({

            url:"admin/changeUserStatus",
            method:"POST",
            data:{
              id:$(this).attr("id"),
              status:$(this).text()
            },
            success:function (data) {
                if(data=="success"){
                    $.notify("User "+ action +"d successfully","success")
                    $this.text("")
                    $this.append("<span class = \"span-mouseover\">"+(action == "Activate"?"Deactivate":"Activate") +"</span>")
                }else{
                    $.notify("Sorry, could not "+ action +" user","failure")
                }

            }

        })
    })


})
