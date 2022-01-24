<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*" errorPage="" %>
<%@page import="data.Status"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9e29fb8a8c.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/styles-test.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
 // Checking empty inputs and password match
    $(document).ready(function(){
    	document.getElementById("subutton1").disabled = true;
    	document.getElementById("subutton1").hidden = true;
    	document.getElementById("ptag").hidden = true;
    	document.getElementById("formId").hidden = true;
    	//Checks if all inputs have values
    	$("#subutton").on("click", function(){
	    	if ($("#code").val()!=""){
	    		if ($("#fname").val()!=""){
	    			if ($("#lname").val()!=""){
	    				if ($("#email").val()!=""){ 
	    					if ($("#party").val()!=""){
	    						if ($("#psswrd").val()!=""){
	    							if ($("#repsswrd").val()!=""){
	    								//Checks if both password fields have the same value
	    								if ($("#repsswrd").val() == $("#psswrd").val()){
	    									document.getElementById("formId").hidden = false;
	    									document.getElementById("ptag").hidden = true;
	    									//Sends data from the first form to the modal form inputs
	    									$("#code1").val($("#code").val());
	    									$("#fname1").val($("#fname").val());
	    									$("#lname1").val($("#lname").val());
	    									$("#email1").val($("#email").val());
	    									$("#party1").val($("#party").val());
	    									$("#psswrd1").val($("#psswrd").val());
	    									document.getElementById("subutton1").disabled = false;
	    									document.getElementById("subutton1").hidden = false;
	    								}
	    								
	    							} else {
	    								document.getElementById("ptag").hidden = false;
	    								$("#ptag").text("Please retype the password");
	    							}
	    						} else {
	    							document.getElementById("ptag").hidden = false;
	    							$("#ptag").text("Please type a password");
	    						}
	    					} else {
	    						document.getElementById("ptag").hidden = false;
	    						$("#ptag").text("Please fill in your party");
	    					}
	    				} else {
	    					document.getElementById("ptag").hidden = false;
	    					$("#ptag").text("Please fill in your email");
	    				}
	    			} else {
	    				document.getElementById("ptag").hidden = false;
	    				$("#ptag").text("Please fill in your last name");
	    			}
				} else {
					document.getElementById("ptag").hidden = false;
					$("#ptag").text("Please fill in your first name");
				}
	    	} else {
	    		document.getElementById("ptag").hidden = false;
	    		$("#ptag").text("Please fill in the invitation code");
	    	}
			
    	});
    });
		
	</script>
</head>
<body>
    <!-- Navigation bar -->
<%@ include file="../jsp/components/navigationBar.jsp"%>
    <div class="container-fluid">
        <div class="row background-row"> <!--Main row-->
            <div class="col-md-12 background-col"> <!--Main col-->
                <div class="row">
                    <div class="col-md-12 header-toppage">
                        <p class="head-lbl">REGISTRATION</p>
                    <div class="row">

                                <div class="col-md-12 card">
                                    <div class="row align-items-center">
                                            <div class="col-md-6 content">
                                            <!-- Content - Form for registering -->
                                                <form class="reg-form" name="registration-form1">
                                                    <label class="reg-lbl">Registration</label>
                                                    <div class="form-group">
                                                        <input class="inputs" id="code" type="text" name="code" placeholder='Entry Code'><br>
                                                        </div>
                                                    <div class="form-group">
                                                        <input class="inputs" id="fname" type="text" name="fname" placeholder='First Name'><br>
                                                        </div>
                                                    <div class="form-group">
                                                        <input class="inputs" id="lname" type="text" name="lname" placeholder='Last Name'><br>
                                                        </div>
                                                    <div class="form-group">
                                                        <input class="inputs" id="email" type="email" name="email" placeholder='Email'><br>
                                                        </div>
                                                    <div class="form-group">
                                                        <input class="inputs" id="party" type="text" name="party" placeholder='Party'><br>
                                                        </div>
                                                    <div class="form-group">
                                                        <input class="inputs" id="psswrd" type="password" name="password" placeholder='Password'><br>
                                                        </div>
                                                    <div class="form-group">
                                                        <input class="inputs" id="repsswrd" type="password" name="repassword" placeholder='Retype Password' ><br>
                                                        </div>
                                                    <button id="subutton" type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#exampleModal">SIGN UP</button>
                                                    <%	//Gets the status attribute for the message for the user
                                                    	Status status = (Status) request.getAttribute("message");
	                                                		if(status != null){
	                                                			out.println("<p class='modalStatus'>" + status.getCode() + "</p>");
	                                                		}
		                                                %>
                                                </form>
                                                <!-- Modal for checking inputs and final registration -->
												 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
												  <div class="modal-dialog modal-dialog-centered">
												    <div class="modal-content">
												      <div class="modal-header">
												        <h5 class="modal-title">CHECK YOUR INPUT</h5>
												        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												      </div>
												      <div class="modal-body">
												      <p id="ptag"></p>
														<form class="reg-form"  id="formId" action="/rest/login/registraion" method="post" name="registration-form">
		                                                    <label class="reg-lbl">Registration</label>
		                                                    <div class="form-group">
		                                                        <input class="inputs" id="code1" type="text" name="code" placeholder='Entry Code' readonly><br>
		                                                        </div>
		                                                    <div class="form-group">
		                                                        <input class="inputs" id="fname1" type="text" name="fname" placeholder='First Name' readonly><br>
		                                                        </div>
		                                                    <div class="form-group">
		                                                        <input class="inputs" id="lname1" type="text" name="lname" placeholder='Last Name' readonly><br>
		                                                        </div>
		                                                    <div class="form-group">
		                                                        <input class="inputs" id="email1" type="email" name="email" placeholder='Email' readonly><br>
		                                                        </div>
		                                                    <div class="form-group">
		                                                        <input class="inputs" id="party1" type="text" name="party" placeholder='Party' readonly><br>
		                                                        </div>
		                                                    <div class="form-group">
		                                                        <input class="inputs" id="psswrd1" type="password" name="password" placeholder='Password' readonly><br>
		                                                        </div>                                              
		                                                 
		                                                </form>
		                                                
												      </div>
												      <div class="modal-footer">
												        <button type="button" class="btn btn-secondary closeBtn" data-bs-dismiss="modal">CLOSE</button>
												        <button id="subutton1" type="submit" class="btn btn-primary subbtn" form="formId">SUBMIT</button>
												      </div>
												    </div>
												  </div>
												</div>
										
                                                
                                            </div>
                                    </div>
                                </div>
                    </div>
                    </div> 
                </div>
            </div>
        </div>   
    </div> 
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
	
</body>
</html>