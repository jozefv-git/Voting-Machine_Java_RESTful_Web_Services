<%@page import="java.util.List"%>
<%@page import="data.GetStatisticControll"%>
<%@page import="data.Questions"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Implementing bootsrap -->
<%@ include file="/html/bootstrap.html"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<meta charset="ISO-8859-1">
<title>Statistics</title>
</head>
<body>
	<!-- Navigation bar -->
	<%@ include file="/jsp/components/navigationBar.jsp"%>
	<div class="container" style="text-align: center">
		<div class="container" style="text-align: left">
			<p style="padding-left: 24px; margin-bottom: 8px; margin-top: 72px;">STATISTICS</p>
		</div>
		<div class="card rounded_card">
			<div class="container">
				<div class="row">
					<div class="container">
						<table class="table table-responsive-stack">
							<thead>
								<tr>
									<th scope="col" style="text-align: center"><h2>Questions</h2></th>
									<th scope="col" style="text-align: center"><h2>Distribution</h2></th>
									<th class="color_column" scope="col" style="text-align: center"><h2>Answered</h2></th>
								</tr>
							</thead>
							<tbody>
								<%
								//Creation of statistic object
									GetStatisticControll statistic = new GetStatisticControll();
								List<Questions> questions = new ArrayList<Questions>();
								//getting questions from statistic servlet
								questions = (List<Questions>) request.getAttribute("question_list");
								//Looping thru all questions
								for (Questions question : questions) {
									//if(statistic.getQuestion1Count(question.getQuestionID()))
								%>
								<tr>
									<td width="auto"><p>
											<%
												out.println(question.getQuestionID());
											%>
										</p></td>
									<td style="width: 100%; margin-top: auto; margin-bottom: auto;"><p>
											<%
												out.println(question.getQuestionText());
											%>
										</p></td>
									<td style="margin-top: auto; margin-bottom: auto; width: 100%;">


										<%
										//Calculations of opacity based on dividation (count of selected answer) and total number of how many 
										//times answer was answered
										float circleOpacity1 = 0;
										float circleOpacity2 = 0;
										float circleOpacity3 = 0;
										float circleOpacity4 = 0;
										float circleOpacity5 = 0;
										//We are calculating of how many times was answer selected compared to sum of how many times question was answered
										if (statistic.getQuestion1Count(question.getQuestionID()) != 0) {
											circleOpacity1 = statistic.getQuestion1Count(question.getQuestionID())
											/ (float) statistic.getQuestionCount(question.getQuestionID());
										}
										if (statistic.getQuestion2Count(question.getQuestionID()) != 0) {
											circleOpacity2 = statistic.getQuestion2Count(question.getQuestionID())
											/ (float) statistic.getQuestionCount(question.getQuestionID());
										}
										if (statistic.getQuestion3Count(question.getQuestionID()) != 0) {
											circleOpacity3 = statistic.getQuestion3Count(question.getQuestionID())
											/ (float) statistic.getQuestionCount(question.getQuestionID());
										}
										if (statistic.getQuestion4Count(question.getQuestionID()) != 0) {
											circleOpacity4 = statistic.getQuestion4Count(question.getQuestionID())
											/ (float) statistic.getQuestionCount(question.getQuestionID());
										}
										if (statistic.getQuestion5Count(question.getQuestionID()) != 0) {
											circleOpacity5 = statistic.getQuestion5Count(question.getQuestionID())
											/ (float) statistic.getQuestionCount(question.getQuestionID());
										}
										%>
										<div class="panel">
											<div class="statistic_circle"
												style="background-color: #3D5A80;opacity: <%out.println(circleOpacity1);%>"></div>
											<div class="statistic_circle"
												style="background-color: #3D5A80; opacity: <%out.println(circleOpacity2);%>"></div>
											<div class="statistic_circle"
												style="background-color: #3D5A80; opacity: <%out.println(circleOpacity3);%>"></div>
											<div class="statistic_circle"
												style="background-color: #3D5A80; opacity: <%out.println(circleOpacity4);%>"></div>
											<div class="statistic_circle"
												style="background-color: #3D5A80; opacity: <%out.println(circleOpacity5);%>"></div>
										</div>
									</td>
									<td class="color_column"
										style="margin-top: auto; margin-bottom: auto; width: 100%"><p
											style="margin-bottom: 0px">
											<%
												out.println(statistic.getQuestionCount(question.getQuestionID()));
											%>x
										</p></td>
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