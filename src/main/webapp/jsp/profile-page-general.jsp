<!-- 
Profile page
@author Samu Uunonen
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
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
		crossorigin="anonymous">
	</script>
	<link href="/css/profilePageStyle.css" rel="stylesheet">
	<link href="/css/profilepage2.css" rel="stylesheet">
</head>
<body>
	<!--  Navigation bar -->
	<%@ include file="../jsp/components/navigationBar.jsp"%>
	<!-- Main content -->
	<div class="container main-container">
		<h1 class="page-header">PROFILE</h1>
		<!-- Profile bio -->
		<div class="row main-profile-row">
			<div class="col-lg-7">
				<div class="container container-profile">
					
					<h2 id="candidate-name" name="candidate-name">${requestScope.politicians.firstName} ${requestScope.politicians.lastName}</h2>
					
					<div class="row profile-row">
						<div class="container-fluid">
							
								<label for="nation">Nation: 
									<span class="profile-bio-infos" id="nation" name="nation">${requestScope.politicians.nation}</span>
								</label> 
								<label for="city">City: 
									<span class="profile-bio-infos" id="city" name="city">${requestScope.politicians.city}</span> 
								</label> 
								<label for="Age">Age: 
									<span class="profile-bio-infos" id="age" name="age">${requestScope.politicians.age}</span> 
								</label> 
								<label for="sex">Sex: 
									<span class="profile-bio-infos" id="sex" name="sex">${requestScope.politicians.sex}</span> 
								</label> 
								<label for="martal-status">Party: 
									<span class="profile-bio-infos" id="marital-status" name="marital-status">${requestScope.politicians.party}</span> 
								</label>
							
						</div>
					</div>
					<!-- Candidate description-->
					<div class="row">
						<p class="profile-page-text" id="profile-page-text">${requestScope.politicians.description}</p>
					</div>

				</div>
			</div>
			<!-- profile image -->
			<div class="col-lg-5 profile-image-col">
				<div class="container container-profile-image">
					<img class="img-fluid profile-image" src="/images/${requestScope.politicians.picture}" max-width="227px" max-height="305" alt="profile image" onerror=this.src="/images/no-image-icon.png"> 
					<span class="container-fluid">
						<p id="candidate-number" name="candidate-number">${requestScope.politicians.politicNumber}</p>
					</span>
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
							 	<br>Left circle = Candidate doesn't agree at all with the question.
							  	<br>Right circle = Candidate totally agree with the question.
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
					
					<c:if test="${requestScope.questionsanswers.size() == 0 }">
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-1" value="1" disabled> 
						<label for="answer-radio-${questionloop.index+1}-1" class="answer-mark" id="answer-mark-${questionloop.index+1}-1"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-2" value="2" disabled> 
						<label for="answer-radio-${questionloop.index+1}-2" class="answer-mark" id="answer-mark-${questionloop.index+1}-2"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-3" value="3" disabled> 
						<label for="answer-radio-${questionloop.index+1}-3" class="answer-mark" id="answer-mark-${questionloop.index+1}-3"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-4" value="4" disabled> 
						<label for="answer-radio-${questionloop.index+1}-4" class="answer-mark" id="answer-mark-${questionloop.index+1}-4"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-5" value="5" disabled> 
						<label for="answer-radio-${questionloop.index+1}-5" class="answer-mark" id="answer-mark-${questionloop.index+1}-5"></label>
						
					</c:if>
					
					<c:if test="${requestScope.questionsanswers.size() > 0 }">
					<c:forEach var="answers" items="${requestScope.questionsanswers}" begin="${questionloop.index}" end="${questionloop.index}" varStatus="answerloop">						
					<c:set var="useranswer" scope="session" value="${answers.answer}"/>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-1" value="1" <c:if test="${useranswer == 1}">checked</c:if> disabled> 
						<label for="answer-radio-${questionloop.index+1}-1" class="answer-mark" id="answer-mark-${questionloop.index+1}-1"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-2" value="2" <c:if test="${useranswer == 2}">checked</c:if> disabled> 
						<label for="answer-radio-${questionloop.index+1}-2" class="answer-mark" id="answer-mark-${questionloop.index+1}-2"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-3" value="3" <c:if test="${useranswer == 3}">checked</c:if> disabled> 
						<label for="answer-radio-${questionloop.index+1}-3" class="answer-mark" id="answer-mark-${questionloop.index+1}-3"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-4" value="4" <c:if test="${useranswer == 4}">checked</c:if> disabled> 
						<label for="answer-radio-${questionloop.index+1}-4" class="answer-mark" id="answer-mark-${questionloop.index+1}-4"></label>
						
						<input type="radio" name="answer-radio-${questionloop.index+1}" class="answer-radio"
							id="answer-radio-${questionloop.index+1}-5" value="5" <c:if test="${useranswer == 5}">checked</c:if> disabled> 
						<label for="answer-radio-${questionloop.index+1}-5" class="answer-mark" id="answer-mark-${questionloop.index+1}-5"></label>
						
					</c:forEach>
					</c:if>
		
					
					</div>
					<hr class="question-row-hr">
				</div>
				
			</c:forEach>
			
			
		</div>

		
	</div>
	<!-- Implementing jquery for bootsrap -->
	<%@ include file="../scripts/bootstrap_jq.html"%>
</body>
</html>