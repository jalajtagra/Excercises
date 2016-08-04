<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    %{--<title><g:layoutTitle default="Grails"/></title>--}%
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="notify.js"/>
    <asset:javascript src="jquery.cookie.js"/>
    <g:layoutHead/>
    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Titillium+Web" rel="stylesheet">
    <base href="${request.getServletContext().getContextPath()}/">
</head>

<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="wrapper">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Link Sharing</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <li style="margin-right:10px;"><form action="user/searchAll" method="GET"><input type="search" class="nav-search" placeholder="Search" id="searchbox" name="q" value="${q}"> </form></li>


                <g:if test="${session.user!=null}">

                <li ><a href="" data-toggle="modal" data-target="#createTopic"><i class="fa fa-comment-o" ></i></a></li>
                <li><a href="" data-toggle="modal" data-target="#sendInvitation"><span class="glyphicon glyphicon-envelope " ></span></a></li>
                <li><a href="" data-toggle="modal" data-target="#shareLink"><span class="glyphicon glyphicon-paperclip " ></span></a></li>
                <li><a href=""  data-toggle="modal" data-target="#shareDocument"><span class="glyphicon glyphicon-file "></span></a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><g:if test="${session.user.photo}"><img src="user/image/${session.user.id}" height="20px" width="15px"></g:if><g:else><i class="fa fa-user"></i></g:else> ${session.user?.firstName}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="user/editProfile">Profile</a></li>
                        <g:if test="${session.user.username == 'Admin'}"> <li><a href="admin/users?searchString=&filter=all&max=10&offset=0">Users</a></li></g:if>
                        %{--<li><a href="#">Topics</a></li>
                        <li><a href="#">Posts</a></li>--}%
                        <li><g:link controller="user" action="logout">Logout</g:link></li>

                    </ul>
                </li>
                </g:if>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

%{--<div id="cover"></div>--}%


%{--<div class="panel panel-default head-panel" >
    <div class=" panel-heading row">
        <div class="col-md-4">
            <h2>Link Sharing</h2>

        </div>


        <div class="col-md-2 col-md-offset-3">
            <div class="form-group">
                <input type="search" class="topsearch form-control" placeholder="Search" id="searchbox">
            </div>

        </div>
        <div class="col-md-3 pull-right">

            <div class="user-dropdown">
                <img src="${asset.assetPath(src: 'defaults/profile.png')}" class="top-images" id="top-profileimage">

                <div class="dropdown" style="display: inline-block ;overflow:visible; ">
                    <a class=" dropdown-toggle" data-toggle="dropdown" style="overflow:visible;">
                        Uday<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Users</a></li>
                        <li><a href="#">Topics</a></li>
                        <li><a href="#">Posts</a></li>
                        <li><a href="#">Logout</a></li>
                    </ul>
                </div>
            </div>
            <div style="float:right;margin-bottom:10px">

                <img src="${asset.assetPath(src: 'defaults/chat.png')}" class="top-images topicons" data-toggle="modal" data-target="#createTopic">
                <span class="glyphicon glyphicon-envelope topicons" data-toggle="modal"
                      data-target="#sendInvitation"></span>
                <span class="glyphicon glyphicon-paperclip topicons" data-toggle="modal" data-target="#shareLink"></span>
                <span class="glyphicon glyphicon-file topicons" data-toggle="modal" data-target="#shareDocument"></span>
            </div>
        </div>
    </div>
</div>--}%



<div class="container">
    %{--<g:if test="${flash.message}">
        <div class="message" role="status" style="font-size: medium;color: green;text-align: center">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="message_error" style="font-size: medium;color: red;text-align: center">${flash.error}</div>

    </g:if>
    --}%
    <g:if test="${flash.message}">
    %{--<div class="message" role="status" style="font-size: medium;color: green;text-align: center">${flash.message}</div>--}%
        <script>
            $.notify('${flash.message}',"success")
        </script>
    </g:if>
    <g:if test="${flash.error}">
        <script>
            $.notify('${flash.error}',"failure")
        </script>
    </g:if>
    <g:layoutBody/>
</div>



<!---------------------------------MODALS-------------------------------------->

<div id="shareLink" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Link</h4>
            </div>

            <div class="modal-body">
                <div class="panel panel-default ">
                    <!-- <div class="panel-heading">
                            Share Link (Pop Up)
                        </div>-->
                    <div class="panel-body">
                        <form class="form-horizontal" id = "share-link" method="POST" action="linkResource/save">
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="link" style="text-align:left">Link* :</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="link" name="url">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="desc"
                                       style="text-align:left">Description* :</label>

                                <div class="col-sm-8">
                                    <textarea class="form-control" id="desc" rows="3" name="description"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="create-link-topic"
                                       style="text-align:left">Topic* :</label>

                                <div class="col-sm-8">
                                    <g:select optionKey="${{it?.topic?.id}}" optionValue="${{it?.topic?.name}}"
                                              id="create-link-topic" name="topicId" from="${allSubscriptions}" />
                                </div>
                            </div>

                            <div class="col-md-offset-4 col-md-8">
                                <button class="button button-default">Share</button>
                                <button class="button button-default cancel" type="button" data-toggle="modal" data-target="#shareLink">Cancel</button>
                                <button class="button button-default reset" type="reset" hidden></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>



<!---------------Share Document Modal------------>

<div id="shareDocument" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>

            <div class="modal-body">
                <div class="panel panel-default ">
                    <!-- <div class="panel-heading">
                            Share Document (Pop Up)
                        </div>-->
                    <div class="panel-body">
                        <form class="form-horizontal" role="form" id="share-document" action="documentResource/save" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="document"
                                       style="text-align:left">Document* :</label>

                                <div class="col-sm-8">
                                    <input type="file" class="form-control" id="document" name="document">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="description"
                                       style="text-align:left">Description* :</label>

                                <div class="col-sm-8">
                                    <textarea class="form-control" id="description" name = "description" rows="3"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topic"
                                       style="text-align:left">Topic* :</label>

                                <div class="col-sm-8">
                                  %{--  <g:select id="topic" name='topic.id' value="${topic?.name}"
                                              noSelection="${['null':'Select One...']}"
                                              from='${allSubscriptions}'
                                              optionKey="topic.id" optionValue="topic.name"></g:select>--}%
                                  <g:select optionKey="${{it?.topic?.id}}" optionValue="${{it?.topic?.name}}"
                                            id="topic" name="topicId" from="${allSubscriptions}" />
                                </div>
                            </div>

                            <div class="col-md-offset-4 col-md-8">
                                <button class="button button-default" type="submit">Share</button>
                                <button class="button button-default cancel" type="button" data-toggle="modal" data-target="#shareDocument">Cancel</button>
                                <button class="button button-default reset" type="reset" hidden></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>


<!----------------------Create Topic Modal ----------------------->


<div id="createTopic" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create Topic</h4>
            </div>

            <div class="modal-body">
                <div class="panel panel-default ">
                    <!--<div class="panel-heading">
                            Create Topic (Pop Up)
                        </div>-->
                    <div class="panel-body">
                        <form class="form-horizontal create-topic-form" >
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topicName" style="text-align:left">Name* :</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="topicName" name="topicName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topic"
                                       style="text-align:left">Visibility* :</label>

                                <div class="col-sm-8">
                                    <select class="form-control" id="visibility" name="visibility">
                                        <option value="Public">Public</option>
                                        <option value="Private">Private</option>
                                    </select>

                                </div>
                            </div>

                            <div class="col-md-offset-4 col-md-8">
                                <button class="button button-default" type="submit">Save</button>
                                <button class="button button-default cancel" type="reset"  data-dismiss="modal">Cancel</button>
                                <button class="button button-default reset" type="reset" hidden></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>

<!---------------------Send Invitation Modal----------------------->


<div id="sendInvitation" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>

            <div class="modal-body">
                <div class="panel panel-default">
                    <!--<div class="panel-heading">
                            Send Invitation
                        </div>-->
                    <div class="panel-body">
                        <form class="form-horizontal" role="form" id="send-invitation" action="user/sendEmail" method="POST">
                            <div class="form-group">
                                <label class="control-label col-md-4" for="email"
                                       style="text-align: left">Email :</label>

                                <div class=" col-sm-8">
                                    <input type="text" class="form-control " id="email" name="emailId">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topic"
                                       style="text-align: left">Topic :</label>

                                <div class="col-sm-8">
                                    %{--<input type="text" class="form-control " id="topic">--}%
                                    <g:select optionKey="${{it?.topic?.id}}" optionValue="${{it?.topic?.name}}"
                                              id="topic" name="topicId" from="${allSubscriptions}"  />
                                </div>
                            </div>
                            <button class="button button-default" type="submit" style="margin-left:190px">Invite</button>
                            %{--<input type="button" class="button button-default" value="Invite" type="submit"  >--}%
                            %{--<input type="button" class="button button-default" value="Cancel" type="reset">--}%
                            <button class="button button-default cancel" type="button" data-toggle="modal" data-target="#sendInvitation">Cancel</button>
                            <button class="button button-default reset" type="reset" hidden></button>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>

<div class="footer" role="contentinfo"></div>

%{--<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>--}%
</body>
</html>
