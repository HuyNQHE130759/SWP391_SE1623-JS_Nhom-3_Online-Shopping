<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Lien He</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%User us = (User) request.getSession().getAttribute("user");%>
        <div id="contact-page" class="container">
            <div class="bg">
                <div class="row">    	
                    <div class="col-sm-4">    			   			
                    </div>
                    <div class="col-sm-4">    			   			
                        <h2 class="title text-center">Change your password</h2>    			    				    				
                        <form class="login-form" action="${pageContext.request.contextPath}/ChangePass"  method="POST">
                            <label class="label-control" >Old pass </label><input pattern="\S(.*\S)?" class="form-control" type="password" name="oldpass" required><br>
                            <label class="label-control" >New Pass </label><input pattern="\S(.*\S)?"  class="form-control" type="password" name="newpass" required><br>
                                <label class="label-control" >Confirm Pass </label><input class="form-control"  type="password" name="renewpass" required><br>
                                <p style="color: red;"> ${mess}</p>
                            <input type="submit" class="btn btn-default" value="Change"><br>
                        </form>   
                    </div>
                </div>			 		
            </div>    	

        </div>	
    </div><!--/#contact-page-->

    <jsp:include page="footer.jsp"></jsp:include>

        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
</body>
</html>