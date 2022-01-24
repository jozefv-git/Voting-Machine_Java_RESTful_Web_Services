<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>First Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9e29fb8a8c.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/styles-test.css">
</head>
<body>
  <!-- Navigation bar -->
<%@ include file="../jsp/components/navigationBar.jsp"%>
    <main>
        <div class="container-fluid">
            <div class="row background-row"> <!--Main row-->
                <div class="col-md-12 background-col"> <!--Main col-->
                    
                     <div class="row justify-content-center">
                             <div class="col-md-6 content-double">
                                     <p class="info-elect"> Kanta-Hämeen elections are held <b>13.6.2021</b>. Try the election machine to find candidates to vote for </p>
                             </div>
                             <div class="col-md-6 content-double">
                                     <a id="quiz-button" type="button" href="/rest/getdata/allquestions" class="btn btn-light">Start</a>
                             </div>
                     </div>
                                 
                </div>
            </div>   
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</body>
</html>