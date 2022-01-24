<%@page import="data.Politicians"%>
<%@page import="data.QueAnsRanUser" %>
<%@page import="java.util.List"%>
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
<title>Results</title>
</head>
<body>
	<!-- Navigation bar -->
	<%@ include file="/jsp/components/navigationBar.jsp"%>
	<div class="container" style="text-align: center">
		<div class="container" style="text-align: left">
			<p style="padding-left: 24px; margin-bottom: 8px; margin-top: 72px;">RESULTS</p>
		</div>

		<%
		//Create of objects via getAtribute
		Politicians politic1 = new Politicians();
		Politicians politic2 = new Politicians();
		Politicians politic3 = new Politicians();
		politic1 = (Politicians) request.getAttribute("politic1");
		politic2 = (Politicians) request.getAttribute("politic2");
		politic3 = (Politicians) request.getAttribute("politic3");
		
		//getting questions from GetData
		//List<QueAnsRanUser> questions = new ArrayList<QueAnsRanUser>();
		//questions = (List<QueAnsRanUser>) request.getAttribute("results");		
		%>

		<div class="card rounded_card_results">
			<div class="container">
				<div class="row">
					<div class="col-md 6">
						<div class="board">
							<div class="d-flex justify-content-center"
								style="height: 100%; width: 100%">
								<div class="row" style="margin-top: auto; margin-bottom: auto;">
									<div class="col-sm 6"
										style="margin-top: auto; margin-bottom: auto;">

										<div class="div_circle_result_win">
											<p
												style="margin-top: 23%; margin-left: 2%; color: #FAFAFA; font-size: 90px">
												<%
														out.println(politic1.getPoliticNumber());
													%>
												</p>
										</div>

									</div>



									<div class="col-sm 6" style="margin: auto;">
										<div class="result_text">
											<h2 style="padding-top: 16px; padding-bottom: 16px">
											<!-- Send and redirect politic id to result detail servlet -->
												<a
													
													href='/rest/getresultsdetail/getpoliticiandetail/<%out.println(politic1.getPoliticID());%>'>
													<%
														out.println(politic1.getFirstName() + " " + politic1.getLastName());
													%>
												</a>
											</h2>

											<div>
												<p style="display: inline-block;">Party:</p>
												<p style="font-weight: bold; display: inline-block;">
													<%
														out.println(politic1.getParty());
													%>
												</p>
											</div>
											<div>
												<p style="display: inline-block;">Nation:</p>
												<p style="display: inline-block; font-weight: bold;">
													<%
														out.println(politic1.getParty());
													%>
												</p>
											</div>
											<div>
												<p style="display: inline-block;">City:</p>
												<p style="display: inline-block; font-weight: bold;">
													<%
														out.println(politic1.getCity());
													%>
												</p>
											</div>
											<div>
												<p style="display: inline-block;">Age:</p>
												<p style="display: inline-block;; font-weight: bold;">
													<%
														out.println(politic1.getAge());
													%>
												</p>
											</div>


										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md 6" style="height: auto; margin: auto;">
						<div class="board">
							<div class="d-flex justify-content-center"
								style="height: 100%; width: 100%">
								<div class="row" style="margin: auto;">
									<div class="col-sm 6">
										<div class="div_circle_result_win_secondary circle_margin">
											<p
												style="margin-top: 30%; margin-left: 2%; color: #FAFAFA; font-size: 40px">
												<%
														out.println(politic2.getPoliticNumber());
													%>
												</p>
										</div>
									</div>
									<div class="col-sm 6" style="margin: auto;">
										<div class="result_text" style="width: 130px; margin: auto">
											<h2
												style="font-weight: normal; font-size: 24px; width: 100%;">
												<a
													href='/rest/getresultsdetail/getpoliticiandetail/<%out.println(politic2.getPoliticID());%>'>
													<%
														out.println(politic2.getFirstName() + " " + politic2.getLastName());
													%>
												</a>
											</h2>
										</div>
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-center"
								style="height: 100%; width: 100%">
								<div class="row" style="margin: auto;">
									<div class="col-sm 6">
										<div class="div_circle_result_win_secondary">
											<p
												style="margin-top: 30%; margin-left: 2%; color: #FAFAFA; font-size: 40px;">
												<%
														out.println(politic3.getPoliticNumber());
													%>
												</p>
										</div>
									</div>
									<div class="col-sm 6" style="margin: auto;">
										<div class="result_text" style="width: 130px; margin: auto">
											<h2 style="font-weight: normal; font-size: 24px; width: 100%">
												<a
													href='/rest/getresultsdetail/getpoliticiandetail/<%out.println(politic3.getPoliticID());%>'>
													<%
														out.println(politic3.getFirstName() + " " + politic3.getLastName());
													%>
												</a>

											</h2>
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
</body>
<!-- Implementing jquery for bootsrap -->
<%@ include file="../scripts/bootstrap_jq.html"%>
</html>