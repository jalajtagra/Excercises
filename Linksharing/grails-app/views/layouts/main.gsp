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
    <g:layoutHead/>
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
                <li style="margin-right:10px;"><input type="search" class="nav-search" placeholder="Search" id="searchbox"></li>


                <g:if test="${session.user!=null}">

                <li ><a href="#"><i class="fa fa-comment-o" data-toggle="modal"
                                    data-target="#createTopic"></i></a></li>
                <li><a href="#about"><span class="glyphicon glyphicon-envelope " data-toggle="modal"
                                           data-target="#sendInvitation"></span></a></li>
                <li><a href="#contact"><span class="glyphicon glyphicon-paperclip " data-toggle="modal" data-target="#shareLink"></span></a></li>
                <li><a href="#contact"><span class="glyphicon glyphicon-file " data-toggle="modal" data-target="#shareDocument"></span></a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user"></i> ${session.user?.firstName}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Users</a></li>
                        <li><a href="#">Topics</a></li>
                        <li><a href="#">Posts</a></li>
                        <li><g:link controller="user" action="logout">Logout</g:link></li>

                    </ul>
                </li>
                </g:if>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

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
    <g:if test="${flash.message}">
        <div class="message" role="status" style="font-size: medium;color: green;text-align: center">${flash.message}</div>
    </g:if>
    <g:if test="${flash.warning}">
        <div class="message_error" style="font-size: medium;color: red;text-align: center">${flash.warning}</div>
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
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="link" style="text-align:left">Link* :</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="link">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="description"
                                       style="text-align:left">Description* :</label>

                                <div class="col-sm-8">
                                    <textarea class="form-control" id="" rows="3"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topic"
                                       style="text-align:left">Topic* :</label>

                                <div class="col-sm-8">
                                    <select class="form-control" id=""></select>
                                </div>
                            </div>

                            <div class="col-md-offset-4 col-md-8">
                                <button class="button button-default">Share</button>
                                <button class="button button-default">Cancel</button>
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
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="link"
                                       style="text-align:left">Document* :</label>

                                <div class="col-sm-8">
                                    <input type="file" class="form-control" id="document">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="description"
                                       style="text-align:left">Description* :</label>

                                <div class="col-sm-8">
                                    <textarea class="form-control" id="description" rows="3"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topic"
                                       style="text-align:left">Topic* :</label>

                                <div class="col-sm-8">
                                    <select class="form-control" id=""></select>
                                </div>
                            </div>

                            <div class="col-md-offset-4 col-md-8">
                                <button class="button button-default">Share</button>
                                <button class="button button-default">Cancel</button>
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
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="name" style="text-align:left">Name* :</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topic"
                                       style="text-align:left">Visibility* :</label>

                                <div class="col-sm-8">
                                    <select class="form-control" id=""></select>
                                </div>
                            </div>

                            <div class="col-md-offset-4 col-md-8">
                                <button class="button button-default">Save</button>
                                <button class="button button-default">Cancel</button>
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
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="email"
                                       style="text-align: left">Email :</label>

                                <div class=" col-sm-8">
                                    <input type="text" class="form-control " id="email">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="topic"
                                       style="text-align: left">Topic :</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control " id="topic">
                                </div>
                            </div>
                            <input type="button" class="button button-default" value="Invite" style="margin-left:160px">
                            <input type="button" class="button button-default" value="Cancel">

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
