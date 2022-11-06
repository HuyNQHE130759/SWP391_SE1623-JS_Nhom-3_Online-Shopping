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

    </head>
    <style>
        input[type=text] {

            border: 0px solid #ccc;

        }

        input[type=text]:focus {
            border: 3px solid #555;
        }
    </style>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%User us = (User) request.getSession().getAttribute("user");%>
        <div id="contact-page" class="container">
            <div class="bg">
                <div class="row">    		
                    <div class="col-sm-12">    			   			
                        <h2 class="title text-center">Welcome <strong><%=us.getFullName()%></strong></h2>    			    				    				
                        <div>
                            <form action="${pageContext.request.contextPath}/editUserInfo" method="POST">
                                <table border="1" width=50%>
                                    <div class="row">
                                        <div class="row g-3">
                                            <div class="col-md-6">
                                                <label for="inputEmail4" class="form-label">User name</label>
                                                <input type="username" class="form-control" id="usename" name="username" value="<%=us.getUsername()%>">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="inputPassword4" class="form-label">Full name</label>
                                                <input type="fullname" class="form-control" id="fullname" name="fullname" value="<%=us.getFullName()%>">
                                            </div>
                                            <div class="col-md-12" >
                                                <label for="inputAddress" class="form-label">Address</label>
                                                <input type="text" class="form-control" id="inputAddress" name="address" value="<%=us.getAddress()%>"> 
                                            </div>
                                            <div class="col-md-12">
                                                <label for="inputAddress2" class="form-label">Phone</label>
                                                <input type="text" class="form-control" id="inputAddress2" name="phone" value="<%=us.getPhone()%>">
                                            </div>
                                            <div class="col-md-12">
                                                <label for="inputAddress2" class="form-label">Email</label>
                                                <input type="text" class="form-control" id="email" name="email" value="<%=us.getEmail()%>" >
                                            </div>
                                            <div class="col-md-12">
                                                <label for="inputAddress2" class="form-label">Gender</label>
                                                <select class="form-select " aria-label="Default select example" name="gender">
                                                    <option value="true" selected=${us.isGender() ? "true" : "false"}>Male</option>
                                                    <option value="false" selected=${us.isGender() ? "true" : "false"}>Female</option>
                                                </select>
                                            </div>

                                        </div>
                                    </div>
                                </table>
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-default btn-primary"  value="Change Profile">
                                    <a href="${pageContext.request.contextPath}/ChangePass" class="btn btn-default btn-warning" style="margin-top: 18px;">Change Password</a>
                                </div>    
                            </form>
                        </div>
                    </div>
                </div>			 		
            </div>    	

        </div>	
    </div><!--/#contact-page-->

    <jsp:include page="footer.jsp"></jsp:include>

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script type="text/javascript" src="js/gmaps.js"></script>
    <script src="js/contact.js"></script>
    <script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>