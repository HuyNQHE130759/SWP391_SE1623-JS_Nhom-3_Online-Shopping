<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:include page="headerlinhbe.jsp"></jsp:include>
        <!--<form action="" method="POST" class="form" id="form-1"><!--/#contact-page-->
        <%User us = (User) request.getSession().getAttribute("user");
          String isEditSuccess = (String) request.getAttribute("isEditSuccess");
        %>
        <div id="contact-page" class="container">
            <div class="bg">
                <div class="row">    		
                    <div class="col-sm-12">    			   			
                        <h2 class="title text-center">Welcome <strong><%=us.getFullName()%></strong></h2>
                        <p class="text-center" style="color: green"> <strong><%=isEditSuccess == null ? "" : isEditSuccess%></strong></p>
                            <form action="${pageContext.request.contextPath}/editUserInfo" method="POST" id="form-1">
                                <table border="1" width=50%>
                                    <div class="row">
                                        <div class="row g-3">
                                            <div class="col-md-6">
                                                <fieldset disabled>
                                                    <label for="inputEmail4" class="form-label">User name</label>
                                                    <input type="username" class="form-control" id="usename" name="username" value="<%=us.getUsername()%>" >
                                                </fieldset>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="fullname" class="form-label">Full name</label>
                                                <input type="fullname" class="form-control" id="fullname" name="fullname" value="<%=us.getFullName()%>">
                                                <span class="form-message" id="validateFullName"></span>
                                            </div>
                                            <div class="col-md-12" >
                                                <label for="address" class="form-label">Address</label>
                                                <input type="text" class="form-control" id="address" name="address" value="<%=us.getAddress()%>"> 
                                                <span class="form-message" id="validateAddress"></span>
                                            </div>
                                            <div class="col-md-12">
                                                <label for="phone" class="form-label">Phone</label>
                                                <input type="text" class="form-control" id="phone" name="phone" value="<%=us.getPhone()%>">
                                                <span class="form-message" id="validatePhone"></span>
                                            </div>
                                            <div class="col-md-12">
                                                <label for="email" class="form-label">Email</label>
                                                <input type="text" class="form-control" id="email" name="email" value="<%=us.getEmail()%>" >
                                                <span class="form-message" id="validateEmail"></span>
                                            </div>
                                            <div class="col-md-12">
                                                <label for="inputAddress2" class="form-label">Gender</label>
                                                <select class="form-select " aria-label="Default select example" name="gender">
                                                    <option value="true" <c:if test="${us.isGender eq true}">selected="selected"</c:if>>Male</option>
                                                    <option value="false" <c:if test="${us.isGender eq false}">selected="selected"</c:if>>Female</option>
                                                </select>
                                            </div>

                                        </div>
                                    </div>
                                </table>
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-default btn-primary" id="changeProfile"  value="Change Profile">
                                    <a href="${pageContext.request.contextPath}/ChangePass" class="btn btn-default btn-warning" style="margin-top: 18px;">Change Password</a>
                                </div>    
                            </form>
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
    <script 
    <script src="js/validation.js" type="text/javascript"></script>
    <script>
        Validator({
            form: '#form-1',
            errorSelector: '.form-message',
            rules:[
                Validator.isRequired('#fullname'),
                //Validator.isAddress('#address'),
                Validator.isEmail('#email'),
                Validator.isPhone('#phone'),
                Validator.addressMaxLength('#address')
                
            ]
        });
        
        </script>
           
</body>
</html>