// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery.jqpagination.min
//= require_self


if (typeof jQuery !== 'undefined') {
	(function($) {
		$(document).ajaxStart(function(){
			$('#spinner').fadeIn();
		}).ajaxStop(function(){
			$('#spinner').fadeOut();
		});
	})(jQuery);
}

$(document).ready(function(){

	var searchboxVal;

	 function showNotification( message,status) {
		$.notify(message,status)
	}

	
	$("body").on("click",".mark-read",function () {
		$.post("readingItem/changeIsRead",{
			id:$(this).parent().parent().attr("id"),
			isRead:true
		},function (data,status) {
			$(".inbox-list").html(data)
			$.notify("Topic mark as read successfully","success")
		})
	})

	$("body").on("click",".resource-actions .download",function (e) {
		e.stopPropagation()
		e.preventDefault()
		var id = $(this).parents(".resource-actions").attr("id");
		window.location.href = "resource/download?id=" + id;

	})

	$("body").on("click",".resource-actions .full-site",function (e) {
		e.stopPropagation()
		e.preventDefault()
		window.open($(this).attr("id"))

	})

	$("body").on("click",".glyphicon-edit",function () {
		$(this).parents(".panel-body").children(".topic-info").children(".topic-name").hide()
		$(this).parents(".panel-body").children(".topic-info").children(".edit-topic-name").show()
		
	})

	$("body").on("click",".save-topic",function () {
		var it = $(this)
		$.ajax({
			url:"topic/editName",
			data :{
				id:$(this).parent().attr("id"),
				name:$(this).siblings("input").val()},
			type:"POST",
			dataType:"json",
			success: function (data) {
				if(data.name!=null){
					$.notify("Topic name edited successfully","success")
					it.parents(".panel-body").children(".topic-info").children(".topic-name").children("a").text( data.name)
					it.parents(".panel-body").children(".topic-info").children(".topic-name").show()
					it.parents(".panel-body").children(".topic-info").children(".edit-topic-name").hide()
				}
			},
			error: function(request,error){

					it.notify("Topic name exists","failure")

			},
			failure:function (data) {
				it.notify("Topic name exists","failure")
			}
		})
	})

	$(".menuprivate a").on("click",function () {
		$.ajax({
			url:
				"topic/updateVisibility",
			dataType:"json",
		data: {
			id:$(this).parent().attr("id"),
				visibility:$(this).text()

		},
		success: function(data, status){
			if(data.visibility != null){
				location.reload()
			}
			}
		})

	})

	$("body").on("click",".glyphicon-trash",function () {
		var it = $(this)
		$.ajax({
			url:"topic/delete",
			dataType:"json",
			data:{id:$(this).attr("id")},
			success:function (data) {
				if(data.message!=null){
					$.notify(data.message,"success")
					location.reload()
				}else{
					$.notify(data.error,"failure")
				}
			}
		})
	})

	$("body").on("click",".cancel-topic",function () {
		$(this).parents(".panel-body").children(".topic-info").children(".topic-name").show()
		$(this).parents(".panel-body").children(".topic-info").children(".edit-topic-name").hide()
	})
	
	$(".create-topic-form").on("submit",function (e) {
		e.preventDefault()
		var inputs = $('.create-topic-form :input')
		var values = {};
		inputs.each(function() {
			values[this.name] = $(this).val();
		});

		$.post("topic/save",
			values
		,function (data) {
				$(this).parents(".modal-content").find(".reset").click()
			$(".create-topic-form").parents(".modal-content").find(".cancel").click()

				if(data=="success"){
					$(".fa-comment-o").notify("Topic Created Successfully","success",{ position:"left" })
				}else{
					$(".fa-comment-o").notify("Could not create topic with this name","FAILURE",{ position:"left" })
				}



		})
	})

	/*$("#share-document").on("submit",function (e) {
		e.preventDefault()
		var data = new FormData();
		data.append('file', jQuery('#document')[0].files[0]);
		var inputs = $('#share-document :input')
		var values = {};
		inputs.each(function() {
			values[this.name] = $(this).val();
			if(this.name != 'file')
			data.append(this.name,$(this).val());
		});
		/!*$.post("documentResource/save",data,function () {
			$("#share-document").parents(".modal-content").find(".reset").click()
			$("#share-document").parents(".modal-content").find(".cancel").click()
			if(data=="success"){
				$(".glyphicon-file").notify("Document shared Successfully","success",{ position:"left" })
			}else{
				$(".glyphicon-file").notify("Could not share this document","failure",{ position:"left" })
			}
		})*!/
		//var data = $('#share-document').serialize();
		$.ajax({
			url:"documentResource/save",
			processData: false,
			cache:false,
			type: 'POST',
			contentType:false,
			data:data,
			success:function () {
				$("#share-document").parents(".modal-content").find(".reset").click()
				$("#share-document").parents(".modal-content").find(".cancel").click()
				if(data=="success"){
					$(".glyphicon-file").notify("Document shared Successfully","success",{ position:"left" })
				}else{
					$(".glyphicon-file").notify("Could not share this document","failure",{ position:"left" })
				}
			}

		})
	})*/

	var options = {
		beforeSubmit:function () {
			if(($("#document").val().match(/pdf$/) != null) || ($("#document").val().match(/PDF$/) != null)){
				$(".fileTypeError").remove()
				if($("#share-document #description").val() != ""){
					return true
				}else{
					return false
				}
			}else{
				if($(".fileTypeError").length==0)
				$("#document").after("<p class='fileTypeError'><b>File Type has to be pdf</p>")
				return false
			}
		}
		,success : function (data) {
			$("#share-document").parents(".modal-content").find(".reset").click()
			$("#share-document").parents(".modal-content").find(".cancel").click()
			if(data=="success"){
				$.notify("Document shared Successfully","success",{ position:"center" })
			}else{
				$.notify("Could not share this document","failure",{ position:"center" })
			}
		}
	}

	$("#share-document").ajaxForm(options)

	$("#share-document").validate({
		rules:{
			document :{
				required:true,
				accept : "application/pdf"
			},

			description:{
				required:true
			}
		}
	})
	
	$("#share-link").validate({
		rules:{
			url:{
				required:true,
				url:true
			},
			description :{
				required:true
			}

		}
	})
	
	var shareLinkOptions = {
		/*beforeSubmit:function () {
			if($("#share-link #link").val() != ""){
				if($("#share-link #desc").val() != ""){
					return true
				}else{
					return false
				}
			}else{
				return false;
			}
		}*/

		success: function (data) {
			$("#share-link").parents(".modal-content").find(".reset").click()
			$("#share-link").parents(".modal-content").find(".cancel").click()
			if(data=="success"){
				$.notify("Link shared Successfully","success",{ position:"center" })
			}else{
				$.notify("Could not share this link","failure",{ position:"center" })
			}
		}
	}
	
	$("#share-link").ajaxForm(shareLinkOptions)

	$("#send-invitation").validate({
		rules:{
			emailId:{
				required:true,
				email:true
			}
		}
	})

	var sendInvitationOptions= {
		success:function (data) {
			$("#send-invitation").parents(".modal-content").find(".reset").click()
			$("#send-invitation").parents(".modal-content").find(".cancel").click()
			$.notify(data,"success")
		}
	}

	$("#send-invitation").ajaxForm(sendInvitationOptions)




	$(".cancel").click(function () {
		$(this).parents(".modal-content").find(".reset").click()
	})


	$("#registerForm").validate({
		rules:{
			email:{
				required:true,
				email:true,
				remote:{
					dataType:"json",
					url: "user/validateEmail",
					data: {username : $("#email").val()}
				}
			},
			username:{
				required:true,
				// validUsername:true
				remote:{
					dataType:"json",
					url: "user/validateUsername",
					data: {username : $("#username").val()}
				}
			}


		},

		messages:{
			username :'Please type in a unique username',
			email:'Please type in a unique email'
		}
	});
	
	$(".glyphicon-envelope").click(function () {
		$("#send-invitation").find("select").children("option[value="+$(this).attr("id")+"]").attr("selected","true")
	})

	$("#searchbox").keyup(function (e) {
		if(e.which != 13){
			searchboxVal = $("#searchbox").val();
		}
	})

	$(".subscribe").click(function () {
		/*  $(window).load(function(){
		 $('#cover').fadeOut(1000);
		 })*/
		$.post("subscription/save",
			{
				id:$(this).attr("id")
			},
			function(data, status){
				var mes
				if(data.message == undefined){
					mes = data.error
					// Cookies.set("subscribed",data.error)

					location.reload()

				}else {
					// Cookies.set("subscribed",data.error)
					location.reload()

				}


			});
	});

	$(".unsubscribe").click(function () {
		/*  $(window).load(function(){
		 $('#cover').fadeOut(1000);
		 })*/
		$.post("subscription/delete",
			{
				id:$(this).attr("id")

			},
			function(data, status){
				var mes
				if(data.message == undefined){
					mes = data.error

					// Cookies.set("subscribed",data.message)

					location.reload()
				}else{
					mes = data.message
					// Cookies.set("subscribed",data.error)
					location.reload()
				}

				// alert("Data: " + mes + "\nStatus: " + status);

			});
	});

	$(".seriousness-dd a").click(function () {
		$.post("subscription/update",
			{
				id:$(this).closest(".seriousness-dd").attr("id"),
				seriousness:$(this).text()

			},
			function(data, status){
				// alert("Data: " + data.message + "\nStatus: " + status);
				location.reload()
			});
	});

	jQuery.validator.addMethod("matchesPassword",function(value,element){
		if(value == $("#changePassword").find("#pwdreg").val()){
			return true
		}else{
			return false
		}
	})

	$("#loginForm").validate({
		rules:{
			username:{
				required:true
			},
			password:{
				required:true,
				matchedPassword:true
			}
		}
	})




})
