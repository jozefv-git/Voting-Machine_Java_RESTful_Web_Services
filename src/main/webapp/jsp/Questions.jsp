<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>

<%@ page import="data.Questions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="/css/styles1.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/12ffd7b83e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
    
    $(document).ready(function(){
    	document.getElementById('buttonForDownload').disabled = true;
		document.getElementById('buttonForShowResults').disabled = true;
		document.getElementById('finalUserAnswersForm').hidden = true;
		document.getElementById('finalUserAnswersForm1').hidden = true;
		var userAnswers = [];
		var userAnswersStr = "";
		var questions1 = $("#articleWithQuestions").data('questions');//get all questions
		questions1 = questions1.slice(0, -1);

		const questions2 = questions1.split('_');
		var questions3 = [];
		for(var i = 0; i < questions2.length; i++)	{
			var question4 = questions2[i].split(".");
			questions3.push([question4[0], question4[1]]);
		}//separate each question from their nr
		

		var j = 0;
		document.getElementById('pForQuestions').innerHTML = questions3[j][1];
		document.getElementById('inlineRadio0').checked = true;
    	//listener for the left arrow
    	$('.leftArrow').on('click', function(){
    		if (j > 0) {//checks if there are questions behind
    			document.getElementById('buttonForDownload').disabled = true;
	   			document.getElementById('buttonForShowResults').disabled = true;//disable buttons
    			var prevAnswer = userAnswers.pop();//deletes the last answer
    			document.getElementById('inlineRadio' + prevAnswer.slice(-1)).checked = true;//restores the input of the user for previous question
    			j--;
		   		document.getElementById('pForQuestions').innerHTML = questions3[j][1];//changes the nr of question displayed
		   		document.getElementById("theTopLineTitle").innerHTML = "Question " + (j+1) + "/" + questions3.length;
		   		
    		}
    		
    	});
    	//listener for the right arrow
    	$('.rightArrow').on('click', function(){
    		if (j < questions3.length-1){//checks if there are questions ahead
				for (var i = 0; i < 6; i++){
    				if($("#inlineRadio" + i).is(":checked")){
    					var theValue = i;
    					break;
    				}//gets the checked input
    			}
    			userAnswers.push(questions3[j][0] +  "_" + theValue);//adds the answer
		   		j++;
		   		document.getElementById('pForQuestions').innerHTML = questions3[j][1];//changes the nr of question displayed
		   		document.getElementById("theTopLineTitle").innerHTML = "Question " + (j+1) + "/" + questions3.length;
		   		document.getElementById('inlineRadio0').checked = true;
		   		if (j == questions3.length-1) {
		   			document.getElementById('buttonForDownload').disabled = false;
		   			document.getElementById('buttonForShowResults').disabled = false;
		   		}//enables last buttons
    		}
   			
    	});
    	//listener for download button
    	$('#buttonForDownload').on('click', function(){
    		for (var i = 0; i < 6; i++){
				if($("#inlineRadio" + i).is(":checked")){
					var theValue = i;
					break;
				}//takes the value of the checked button
			}
    		
    		userAnswers.push(questions3[j][0] + "_" + theValue);//adds the answer
    		for (var i = 0; i < userAnswers.length; i++) {
    			userAnswersStr = userAnswersStr + questions3[i][0] + "_" + questions3[i][1] + "_" + userAnswers[i].slice(-1) + "-";
    		}
    		userAnswersStr = userAnswersStr.slice(0, -1);
    		document.getElementById('finalUserAnswers').value = userAnswersStr;
    		document.getElementById('finalUserAnswers1').value = userAnswersStr;//stores questionID, question, answers for all questions
    		


    	});
    	//listener for show results button
    	$('#buttonForShowResults').on('click', function(){
    		for (var i = 0; i < 6; i++){
				if($("#inlineRadio" + i).is(":checked")){
					var theValue = i;
					break;
				}//takes the value of the checked button
			}
    		userAnswers.push(questions3[j][0] + "_" + theValue);//adds the answer
    		for (var i = 0; i < userAnswers.length; i++) {
    			userAnswersStr = userAnswersStr + questions3[i][0] + "_" + questions3[i][1] + "_" + userAnswers[i].slice(-1) + "-";
    		}
    		userAnswersStr = userAnswersStr.slice(0, -1);
    		document.getElementById('finalUserAnswersForm').hidden = false;
    		document.getElementById('finalUserAnswersForm1').hidden = false;
    		document.getElementById('finalUserAnswers').value = userAnswersStr;//stores questionID, question, answers for all questions
    		document.getElementById('finalUserAnswers1').value = userAnswersStr;

    	});
    	
    });
    	
    </script>
	<title>Questions</title>
</head>
<body>
<!-- Navigation bar -->
<%@ include file="../jsp/components/navigationBar.jsp"%>
	<div class="container-fluid">
		<div class="row mainRow">
			<div class="col-md-12 mainColumn">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-11">
						<article class="questionArticle">
						<%//gets all questions 
									List<Questions> questionsList=(List<Questions>)request.getAttribute("allQuestions"); 
							out.println("<p class='topLine' id='theTopLineTitle' data-id='" + questionsList.size() + "'>Question 1/" + questionsList.size() + "</p>");
							%>
						</article>
					</div>
				</div>
				<div class="row">
					<div class="col-md-1 arrowColumn firstArrowColumn">
						<a class="arrow leftArrow" href="javascript:void(0)"><i class="fas fa-chevron-left"></i></a>
					</div>
					<div class="col-md-10 mainMainColumn">
						
						<article class='forMainBox' id='articleWithQuestions' data-questions='<% 
								for (int i=0; questionsList!=null && i<questionsList.size();i++){//loops throung question objects
									Questions q = questionsList.get(i);
									out.print(q.getQuestionID() + "." + q.getQuestionText() + "_");
								}
						%>'>
							<div class='mainBox' id='div'>
								<p class='question' id="pForQuestions"></p>

								<div class='botMainBox'>
								
								<label class='agreement' for='inlineRadio1'>Totally disagree</label>
								<label class='agreement1' for='inlineRadio1'>1</label>
								<input class='form-check-input mt-0' type='radio' name='inlineRadioOptions' id='inlineRadio1' value='1'>
								<input class='form-check-input mt-0' type='radio' name='inlineRadioOptions' id='inlineRadio2' value='2'>
								<input class='form-check-input mt-0' type='radio' name='inlineRadioOptions' id='inlineRadio3' value='3'>
								<input class='form-check-input mt-0' type='radio' name='inlineRadioOptions' id='inlineRadio4' value='4'>
								<input class='form-check-input mt-0' type='radio' name='inlineRadioOptions' id='inlineRadio5' value='5'>
								<label class='agreement' for='inlineRadio5'>Totally agree</label>
								<label class='agreement1' for='inlineRadio5'>5</label>
								
								</div>
								<div>
									
									<input class='form-check-input mt-0' type='radio' name='inlineRadioOptions' id='inlineRadio0' value='0' hidden="">
								</div>
							 </div>
						</article>
						
					</div>
					<div class="col-md-1 arrowColumn secondArrowColumn">
						<a class="arrow leftArrow" href="javascript:void(0)"><i class="fas fa-chevron-left"></i></a>
					</div>
					<div class="col-md-1 arrowColumn thirdArrowColumn">
						<a class="arrow rightArrow" href="javascript:void(0)"><i class="fas fa-chevron-right"></i></a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<form id="finalUserAnswersForm" name="printAnswers" action="/printanswers" method="POST">
							<input id="finalUserAnswers" name="values" hidden=""/>
						</form>
						<form id="finalUserAnswersForm1" name="printAnswers1" action="/rest/getdata/storeresults" method="POST">
							<input id="finalUserAnswers1" name="values" hidden=""/>
						</form>
						
						
					</div>
					<div class="col-md-3 forShowResults">
						<button id="buttonForDownload" type="submit" class="btn showResults" form="finalUserAnswersForm">print results</button>
						<button id="buttonForShowResults" type="submit" class="btn showResults" form="finalUserAnswersForm1">show results</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</body>
</html>
