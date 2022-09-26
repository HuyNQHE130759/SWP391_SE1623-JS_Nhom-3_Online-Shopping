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

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%User us = (User) request.getSession().getAttribute("user");%>
        <div id="contact-page" class="container">
            <div class="bg">
                <div class="row">    		
                    <div class="col-sm-12">    			   			
                        <h2 class="title text-center">Welcome <strong><%=us.getFullName()%></strong></h2>    			    				    				
                            
                        <table border="1" width=50%>
                           
                            <tr>
                                <td>FullName</td>
                                <td><%=us.getFullName() %></td>
                            </tr>
                            <tr>
                                <td>address</td>
                                <td><%=us.getAddress() %></td>
                            </tr>
                            <tr>
                                <td>phone</td>
                                <td><%=us.getPhone() %></td>
                            </tr>
                            <tr>
                                <td>username</td>
                                <td><%=us.getUsername() %></td>
                            </tr>
                            <tr>
                                <td>password</td>
                                <td><%=us.getPassword() %><br><a href="${pageContext.request.contextPath}/ChangePass"><input type="button" value="ChangePassword"></a></td>
                            
                            </tr>
                            
                        </table>
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