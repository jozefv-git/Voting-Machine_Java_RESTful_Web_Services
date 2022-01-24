<%@page import="java.text.Normalizer.Form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="data.CountOfCand"%>
<%@page import="data.Politicians"%>
<%@page import="data.Status"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="/css/styles1.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/12ffd7b83e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>Politician list</title>
	<script>
		$(document).ready(function(){
			
			const inviteCode = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ0123456789+-";
			
			$(".addPolitician").on('click', function(){
				var actualInviteCode = shuffle(inviteCode).slice(0, 10);
				console.log(actualInviteCode);
				//document.getElementById('invitecodeForCandidate').innerHTML = actualInviteCode;
				$('#invitecodeForCandidate').val(actualInviteCode);
				//document.getElementById('invitecodeForCandidate').value = actualInviteCode;
			})
	
		});
		
		window.onload = function(){
		 document.getElementById("invitecodeForCandidate").value = "";
		 document.getElementById("inputforCode").value = "";
		}
		
		function shuffle(theStr){
			var s = theStr.split("");
			s.sort(function() {
				return 0.5 - Math.random();
			});
			theStr = s.join('');                
				return theStr;                       
			}
		
		
		
	</script>
</head>
<body>
<!-- Navigation bar -->
<%@ include file="../jsp/components/navigationBar.jsp"%>
	<div class="container-fluid">
		<div class="row mainRow">
			<div class="col-md-12 mainColumn">
				<div class="row">
					<div class="col-md-12">
						<article class="questionArticle">
							<p class="topLine">POLITICIANS</p>
						</article>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 mainMainColumn">
						<article class="forMainBox">
							<div class="mainBox">
								<div class="row">
									<div class="col-md-12">
										<p class="aTitle">INVITATION CODE FOR A POLITICIAN</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 addPoliticianCol">
										<button class="btn addPolitician">generate politician code</button>
									</div>
								</div>
								<form class="row formRow" method="POST" action="/rest/getdata1/storecode">
								<div class="row">
									<div class="col-md-12 someCol">
										<input type="email" class="polCode" id="invitecodeForCandidate" name="invitationCode" readonly/>
									</div>
								</div>
								
									<div class="col-md-6 inputColumns">
										<input id="inputforCode" class="form-control emailInput" type="text" name="emailAddress" placeholder="Type an email here...">
									</div>
									
									<div class="col-md-6 submitBtnColumns">
										<button type="submit" class="btn submitEmail">send</button>
									</div>
								</form>
								<% Status status = (Status) request.getAttribute("status");
									if (status != null){
										out.println("<p class='pForInvCode2'>" + status.getCode() + "</p>");
									}
								%>
								<hr class="firstLine">
								<div class="row">
									<div class="col-md-12">
										<p class="aTitle">ALL POLITICIANS PARTICIPATING IN THE QUIZ (<% List<CountOfCand> nrOfCands = (ArrayList<CountOfCand>) request.getAttribute("nrOfCands");
										for(CountOfCand nrOfCand : nrOfCands){
											out.print(nrOfCand.getNr());
										}
										%>)</p>
									</div>
								</div>
									<% List<Politicians> candidateList =  (List<Politicians>) request.getAttribute("allCands");
										int i = 1;
										for (Politicians individualCand : candidateList){
											out.print("<div class='row politicianRow'>");
											out.print("<div class='col-md-6 politicianCols'>");
											out.print("<b class='candNrB'>" + i + ". </b>" + "<a class='politicianParag' href='/rest/profile/readprofile/"+individualCand.getPoliticID()+"'>" +  individualCand.getFirstName() + " " + individualCand.getLastName() + "</a>");
											out.print("</div>");
											out.print("<div class='col-md-6 buttonsColumns1 delBtnCol1'>");
											out.print("<button type='button' class='btn deleteBtn1' data-bs-toggle='modal' data-bs-target='#exampleModal" + individualCand.getPoliticID() + "'>delete</button>");
											out.print("</div>");
											out.print("</div>");


											out.print("<div class='modal fade' id='exampleModal" + individualCand.getPoliticID() + "' tabindex='-1' aria-labelledby='exampleModalLabel' aria-hidden='true'>");
											out.print("<div class='modal-dialog'>");
											out.print("<div class='modal-content'>");
											out.print("<div class='modal-header'>");
											out.print("<h5 class='modal-title' id='exampleModalLabel'>Delete a candidate</h5>");
											out.print("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>");
											out.print("</div>");
											out.print("<div class='modal-body'>");
									        out.print("<form action='/rest/getdata/deletecandidate' method='POST' id='deleteACand" + individualCand.getPoliticID() + "'>");
									        out.print("<p>");
									        out.print("Are you sure you want to delete " + individualCand.getFirstName() + " " + individualCand.getLastName() + " from the list?");
									        out.print("</p>");
									        out.print("<p>");
									        out.print("This action cannot be undone!");
									        out.print("</p>");
									        out.print("<input class='inputToHide' name='candidateIdDel' value='" + individualCand.getPoliticID() + "' hidden/>");
									        out.print("</form>");
									        out.print("</div>");
							        		out.print("<div class='modal-footer'>");
					        				out.print("<button type='button' class='btn btn-secondary' data-bs-dismiss='modal'>Close</button>");
			        						out.print("<a type='submit' class='btn deleteBtn1' href='/rest/getdata1/deletecandidate/" + individualCand.getPoliticID() + "'>Delete</a>");
	        								out.print("</div>");
       										out.print("</div>");
											out.print("</div>");
											out.print("</div>");

											i++;
										}
									%>
								
							</div>
						</article>
					</div>
					
				</div>
				
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</body>
</html>
