<!-- 
Edit profile page
@author Samu Uunonen
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Profile</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
		crossorigin="anonymous">
	<script src="https://kit.fontawesome.com/9e29fb8a8c.js"
		crossorigin="anonymous"></script>
	<link href="/css/profilePageStyle.css" rel="stylesheet">
	<script src="/js/script-profile-page.js" defer></script>
</head>
<body>
	<!--  Navigation bar -->
	<%@ include file="../jsp/components/navigationBar.jsp"%>
	<!-- Main content -->
	<div class="container main-container">
		<h1 class="page-header">PROFILE</h1>
		<!-- Profile bio -->
		<form class="bio-form" id="bio-form" name="bio-form" action="/rest/profile/editprofile/${requestScope.politicians.politicID }" method="POST" enctype="multipart/form-data">
		<div class="row main-profile-row">
			<div class="col-lg-7">
				<div class="container container-profile">
					
					<h2 id="candidate-name" name="candidate-name">${requestScope.politicians.firstName} ${requestScope.politicians.lastName}</h2>
					
					<div class="row profile-row">
						<div class="container-fluid">
								<label for="firstNameTextField">Firstname:
									<br>
									<input type="text" class="bio-textfields" id="firstNameTextField" name="firstNameTextField" value="${requestScope.politicians.firstName}" maxlength="150" placeholder="Firstname">
								</label>
								<label for="lastNameTextField">Lastname:
									<br>
									<input type="text" class="bio-textfields" id="lastNameTextField" name="lastNameTextField" value="${requestScope.politicians.lastName}" maxlength="150" placeholder="Lastname">
								</label>
								<label for="nation">Nation: 
									<br>
									<input type="text" class="bio-textfields" id="nationTextField" name="nationTextField" value="${requestScope.politicians.nation}" maxlength="150"> 
								</label> 
								<label for="city">City: 
									<br> 
									<input type="text" class="bio-textfields" id="cityTextField" name="cityTextField" value="${requestScope.politicians.city}" maxlength="50">
								</label> 
								<label for="Age">Age:
									<div class="tooltip">
										<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
										  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
										  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"></path>
										</svg>
										  <span class="tooltiptext">
										  	Minimum value for age is 18 and max is 130
										  </span>
									</div>
									<br> 
									<input type="number" class="bio-textfields" id="ageTextField" name="ageTextField" value="${requestScope.politicians.age}" min="18" max="130">
								</label> 
								<label for="sex">Sex: 
									<br> 
									<input type="text" class="bio-textfields" id="sexTextField" name="sexTextField" value="${requestScope.politicians.sex}" maxlength="50">
								</label> 
								<label for="party">Party: 
									<br> 
									<input type="text" class="bio-textfields" id="partyTextField" name="partyTextField" value="${requestScope.politicians.party}" maxlength="50">
								</label>
								
								<label for="profile-text">Description:
									<div class="tooltip">
										<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
										  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
										  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"></path>
										</svg>
									  <span class="tooltiptext">
									  	Describe yourself briefly as politic, what voters are getting if they vote for you.<br>(max 250 characters)
									  </span>
									</div>
								<br>
								<textarea class="profile-textarea bio-textfields" id="profile-textarea" name="profile-text" rows="10" form="bio-form" maxlength="250" placeholder="Short description about you..">${requestScope.politicians.description}</textarea>
								</label>
							
						</div>
					</div>
					<!-- Candidate description-->
					<div class="row">
					
					</div>

				</div>
			</div>
			<!-- profile image -->
			<div class="col-lg-5 profile-image-col">
				<div class="container container-profile-image">
					<img class="img-fluid profile-image" src="/images/${requestScope.politicians.picture}" max-width="227px" max-height="305" alt="profile image" onerror=this.src="/images/no-image-icon.png">					
					<span class="container-fluid">
						<input type="number" class="candidate-number-input" id="candidate-number-input" name="candidateNumber" min="0" max="1000" value="${requestScope.politicians.politicNumber}">
					</span>
				</div>
				<input type="file" class="file-form bio-textfields" id="file-textField" name="file-textField" form="bio-form" accept="image/*"  maxlength="250">
			</div>
		</div>
		
		
		
		<hr>
		<!-- Email and pass section -->
		<div class="container container-profile">
			<div class="row profile-row">
				<div class="col-md-6">
					<label for="email">Email: 
					<div class="tooltip">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
						  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
						  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"></path>
						</svg>
						  <span class="tooltiptext">
						  	Email is mandatory field, you need it to log in.
						  </span>
						</div>
						<br>
						<input form="bio-form" type="email" class="bio-textfields" id="email-textField" name="email-textField" value="${requestScope.politicians.email}"  maxlength="255" required>
					</label>
				</div>
				<div class="col-md-6">
					<label for="password">Password: 
						<div class="tooltip">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
							  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
							  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"></path>
							</svg>
							  <span class="tooltiptext">
							  	Password is mandatory field, you need it to log in.
							  </span>
						</div>
						<br> 
						<input form="bio-form" type="password" class="bio-textfields" id="password-textField" name="password-textField" value="${requestScope.politicians.pssword}" maxlength="50" required>
					</label>
				</div>
			</div>

		</div>
		<hr>
		<!-- Questions section -->
		<div class="container container-profile">
			
			<div class="row profile-row">
				<div class="col-md-6 question-col">
					<h2 class="profile-heading">Questions</h2>
				</div>
				<div class="col-md-6 question-col">
					<h2 class="profile-heading">Answers
					<div class="tooltip">
						<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
						  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
						  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"></path>
						</svg>
						  <span class="tooltiptext">
						  	It is mandatory to answer every question before saving profile.
						  	<br>Left circle = I don't agree at all
						  	<br>Right circle = I totally agree
						  </span>
						</div>
					</h2>
					
						
					
				</div>
			</div>
			
			<c:forEach var="question" items="${requestScope.questions}" varStatus="questionloop" >
			
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-${questionloop.index+1}">${questionloop.index+1}. <span class="profile-question" id="span-question-${questionloop.index+1}">${question.questionText}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
					
					<!--  If user has not answered any questions yet, show empty answer radios -->
					<c:if test="${requestScope.questionsanswers.size() == 0 }">
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-1" value="1" required> 
						<label for="answer-radio-${questionloop.index+1}-1" class="answer-mark" id="answer-mark-${questionloop.index+1}-1"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-2" value="2" > 
						<label for="answer-radio-${questionloop.index+1}-2" class="answer-mark" id="answer-mark-${questionloop.index+1}-2"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-3" value="3" > 
						<label for="answer-radio-${questionloop.index+1}-3" class="answer-mark" id="answer-mark-${questionloop.index+1}-3"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-4" value="4" > 
						<label for="answer-radio-${questionloop.index+1}-4" class="answer-mark" id="answer-mark-${questionloop.index+1}-4"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-5" value="5" > 
						<label for="answer-radio-${questionloop.index+1}-5" class="answer-mark" id="answer-mark-${questionloop.index+1}-5"></label>
						
					</c:if>
					
					<!-- If user has already answered questions, mark answered radios -->
					<c:if test="${requestScope.questionsanswers.size() > 0 }">
					<c:forEach var="answers" items="${requestScope.questionsanswers}" begin="${questionloop.index}" end="${questionloop.index}" varStatus="answerloop">						
					<c:set var="useranswer" scope="session" value="${answers.answer}"/>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-1" value="1" <c:if test="${useranswer == 1}">checked</c:if> > 
						<label for="answer-radio-${questionloop.index+1}-1" class="answer-mark" id="answer-mark-${questionloop.index+1}-1"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-2" value="2" <c:if test="${useranswer == 2}">checked</c:if> > 
						<label for="answer-radio-${questionloop.index+1}-2" class="answer-mark" id="answer-mark-${questionloop.index+1}-2"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-3" value="3" <c:if test="${useranswer == 3}">checked</c:if> > 
						<label for="answer-radio-${questionloop.index+1}-3" class="answer-mark" id="answer-mark-${questionloop.index+1}-3"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-4" value="4" <c:if test="${useranswer == 4}">checked</c:if> > 
						<label for="answer-radio-${questionloop.index+1}-4" class="answer-mark" id="answer-mark-${questionloop.index+1}-4"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-5" value="5" <c:if test="${useranswer == 5}">checked</c:if>> 
						<label for="answer-radio-${questionloop.index+1}-5" class="answer-mark" id="answer-mark-${questionloop.index+1}-5"></label>
						
					</c:forEach>
					</c:if>
		
					
					</div>
					<hr class="question-row-hr">
				</div>
				
			</c:forEach>
			 
		</div>

		</form>
		<div class="container-fluid">
			<div class="row row-confirm-delete hide" id="confirm-delete-profile">
				<label>Are you sure you want to delete your profile?</label>
				<a class="btn btn-yes" id="btn-yes" href="/rest/profile/deletepolitician/${requestScope.politicians.politicID}">YES</a>	<!-- By clicking this button, delete profile -->
				<button class="btn btn-no" id="btn-no">NO</button>
			</div>
			<div class="row">
				<div class="container-button">
					<button class="btn btn-delete" id="btn-delete">DELETE PROFILE</button>
					<a class="btn btn-reset" href="/rest/profile/resetanswers/${requestScope.politicians.politicID}">RESET ANSWERS</a>
					<button type="submit" form="bio-form" class="btn btn-save" id="btn-save">SAVE PROFILE</button> <!-- By clicking this button, save data into database -->
				</div>
			</div>
		</div>
		
	</div>
	<!-- Implementing jquery for bootsrap -->
	<%@ include file="../scripts/bootstrap_jq.html"%>
</body>
</html>