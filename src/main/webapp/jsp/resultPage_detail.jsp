<%@page import="data.Politicians"%>
<%@page import="data.QuestionAnswerPoliticObj"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<!-- Implementing bootsrap -->
<%@ include file="/html/bootstrap.html"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<meta charset="ISO-8859-1">
<title>Results detail</title>
</head>
<body>
	<!-- Navigation bar -->
	<%@ include file="/jsp/components/navigationBar.jsp"%>
	<div class="container" style="text-align: center">
		<div class="container" style="text-align: left">
			<p style="padding-left: 24px; margin-bottom: 8px; margin-top: 72px;">RESULTS</p>
		</div>
		<% //Creating politician object
			Politicians politicDetail = new Politicians();
		politicDetail = (Politicians) request.getAttribute("politicDetail");
		%>
		<div class="card rounded_card">
			<div class="container">
				<div class="row flex-column-reverse flex-md-row">
					<div class="col-md 6 col-sm 12">
						<h2 class="top_margin">YOUR CANDINATE</h2>
						<h1 style="padding-bottom: 24px">
							<%
								out.println(politicDetail.getFirstName() + " " + politicDetail.getLastName());
							%>
						</h1>
						<p style="padding-bottom: 16px">
							<%
								out.println(politicDetail.getDescription());
							%>
						</p>
						<button type="button" class="btn btn-primary btn_rounded_primary"
							style="margin-bottom: 40px;background-color:white">
							<p style="margin-bottom: 0px"> <!-- Sending id of politician to profile page -->
								<a href="/rest/profile/readprofile/<%out.println(politicDetail.getPoliticID());%>">MORE</a>
							</p>
						</button>
					</div>
					<div class="col-md 6 col-xs" style="height: 375px;">
						<div class="d-flex justify-content-center"
							style="height: 100%; width: 100%">
							<div class="img_div">
								<div class="profile_div">
									<img alt="profile_img"
										src="/images/<%out.println(politicDetail.getPicture());%>">
								</div>
								<div class="div_circle">
									<p class="medium_big_t" style="margin-top: 20%">
										<%
											out.println(politicDetail.getPoliticNumber());
										%>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="container">
						<hr />
					</div>
				</div>
				<div class="row">
					<div class="container" style="padding: 24px">
						<table class="table table-responsive-stack">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col" style="text-align: center"><h2>Questions</h2></th>
									<th scope="col" style="text-align: center"><h2>You</h2></th>
									<th class="color_column" scope="col" style="text-align: center"><h2>
											<%
												out.println(politicDetail.getFirstName());
											%>
										</h2></th>
								</tr>
							</thead>
							<tbody>

								<tr>
									<!-- First rows only for descriptions -->
									<td scope="row"><p></p></td>
									<td style="width: 100%"><p></p></td>
									<td class="disagre_agree"><< Disagree -- Agree >></td>
									<td class="color_column disagre_agree"><< Disagree --
										Agree >></td>
								</tr>


								<%
									//creation of array based on our atribute which we are getting from detail servlet
								ArrayList<QuestionAnswerPoliticObj> answers = (ArrayList<QuestionAnswerPoliticObj>) request
										.getAttribute("questionsAnswersUserPoliticList");

								// looping thrue all objects
								for (QuestionAnswerPoliticObj questionAnswersObj : answers) {
								%>

								<tr>

									<td scope="row"><p>
											<%
												out.println(questionAnswersObj.getQuestionId());
											%>
										</p></td>
									<td style="width: 100%"><p>
											<%
												out.println(questionAnswersObj.getQuestion());
											// for test out.println(questionAnswersObj.getQuestionAnswer());
											//for test out.println(questionAnswersObj.getPoliticianAnswer());
											%>
										</p></td>
									<td>
										<%-- 	<%@ include --%> <%--		file="../jsp/components/yourAnswer_radio.jsp"%> --%>
										<%
										//answers selected by user
											String circleOpacity1 = "";
										String circleOpacity2 = "";
										String circleOpacity3 = "";
										String circleOpacity4 = "";
										String circleOpacity5 = "";
										switch (questionAnswersObj.getQuestionAnswer()) {
										case 1:
											circleOpacity1 = "#3D5A80";
											break;
										case 2:
											circleOpacity2 = "#3D5A80";
											break;
										case 3:
											circleOpacity3 = "#3D5A80";
											break;
										case 4:
											circleOpacity4 = "#3D5A80";
											break;
										case 5:
											circleOpacity5 = "#3D5A80";
											break;
										}
										%>
										<div class="panel">
											<div class="statistic_circle"
												style="background-color: <%out.println(circleOpacity1);%>;"></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circleOpacity2);%>;"></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circleOpacity3);%>;"></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circleOpacity4);%>; "></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circleOpacity5);%>; "></div>
										</div>


									</td>

									<%
									//Answers selected by politician
										String circlePoliticOpacity1 = "";
									String circlePoliticOpacity2 = "";
									String circlePoliticOpacity3 = "";
									String circlePoliticOpacity4 = "";
									String circlePoliticOpacity5 = "";
									switch (questionAnswersObj.getPoliticianAnswer()) {
									case 1:
										circlePoliticOpacity1 = "#3D5A80";
										break;
									case 2:
										circlePoliticOpacity2 = "#3D5A80";
										break;
									case 3:
										circlePoliticOpacity3 = "#3D5A80";
										break;
									case 4:
										circlePoliticOpacity4 = "#3D5A80";
										break;
									case 5:
										circlePoliticOpacity5 = "#3D5A80";
										break;
									}
									%>

									<td class="color_column">

										<div class="panel">
											<div class="statistic_circle"
												style="background-color: <%out.println(circlePoliticOpacity1);%>;"></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circlePoliticOpacity2);%>;"></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circlePoliticOpacity3);%>;"></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circlePoliticOpacity4);%>; "></div>
											<div class="statistic_circle"
												style="background-color: <%out.println(circlePoliticOpacity5);%>; "></div>
										</div>

									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- Implementing jquery for bootsrap -->
<%@ include file="../scripts/bootstrap_jq.html"%>
</html>