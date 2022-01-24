<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="/css/styles1.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/12ffd7b83e.js" crossorigin="anonymous"></script>
	<title>Dashboard</title>
</head>
<body>
<!-- Navigation bar -->
<%@ include file="../jsp/components/navigationBar.jsp"%>
	<div class="container-fluid">
		<div class="row mainRow"  style="text-align: left;">
			<div class="col-md-12 mainColumn">
				<div class="row">
					<div class="col-md-12">
						<article class="questionArticle">
							<p class="topLine">DASHBOARD</p>
						</article>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 mainMainColumn">
						<article class="forMainBoxDashboard">
							<div class="mainBoxDashboard">
								<div class="row justify-content-center mainRowInDashboard">
									<div class="col-md-6 buttonColumns">
										<a class="btn questionList" href="/rest/questionlist/questionsedit">question list</a>
									</div>
									<div class="col-md-6 buttonColumns1">
										<a class="btn politicianList" href="/rest/getdata1/getcandidates">politician list</a>
									</div>
								</div>
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