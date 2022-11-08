<%@page import="entity.CheckOut"%>
<%@page import="entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Admin Console</title>
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
        <%ArrayList<Product> pl = (ArrayList<Product>) request.getAttribute("productList");%>
        <%ArrayList<CheckOut> cl = (ArrayList<CheckOut>) request.getAttribute("CheckOutList");%>
        <div id="contact-page" class="container">
            <div class="bg">
                <div class="row">    		
                    <div class="col-sm-12">    			   			
                        <h2 class="title text-center">Admin <strong>Console</strong></h2>    			    				    				
                        Product:
                        <table border="1">  
                            <tr>
                                <td>pid</td>
                                <td>ProductName</td>
                                <td>Quantity</td>
                                <td>Price</td>
                                <td>IMG</td>
                                <td>Description</td>
                                <td>CateID</td>
                                <td>Status</td>

                            </tr>
                            <%for (int i = 0; i < pl.size(); i++) {%>
                            <tr>
                                <td><%=pl.get(i).getPid()%></td>
                                <td><%=pl.get(i).getPname()%></td>
                                <td><%=pl.get(i).getQuantity()%></td>
                                <td><%=pl.get(i).getPrice()%></td>
                                <td><img class="two" src="<%=pl.get(i).getImage()%>" height="150px" width="200px"> </td>
                                <td><%=pl.get(i).getDescription()%></td>
                                <td><%=pl.get(i).getCateId()%></td>
                                <td><input type="checkbox" disabled="disabled" <%if (pl.get(i).isStatus() == true) {%> checked="checked"> <%}%></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/RemoveProduct?pid=<%=pl.get(i).getPid()%>"> <input type="button" value="Delete"> </a>
                                    <a href="${pageContext.request.contextPath}/UpdateProduct?pid=<%=pl.get(i).getPid()%>"> <input type="button" value="Update"> </a>
                                </td>
                            </tr>
                            <% }%>
                            <a href="${pageContext.request.contextPath}/InsertProduct"> <input type="button" value="Insert"> </a>
                        </table><br><br>
                        <h2 class="title text-center">Checkout<strong></strong></h2>
                        
                        <table border="1">
                            <tr>
                                <td>bid</td>
                                <td>cid</td>
                                <td>createDate</td>
                                <td>totalPrice</td>
                                

                            </tr>
                            <%for (int i = 0; i < cl.size(); i++) {%>
                            <tr>
                                <td><%=cl.get(i).getBId()%></td>
                                <td><%=cl.get(i).getCId()%></td>
                                <td><%=cl.get(i).getCrateDate()%></td>
                                <td><%=cl.get(i).getTotalPrice()%></td>
                                
                            </tr>
                            <% }%>
                            
                        </table>
                    </div>
                </div>			 		
            </div>    	
            <div class="row">  	
                <div class="col-sm-8">
                    <h2 class="title text-center">Get In Touch</h2>
                    <div class="status alert alert-success" style="display: none"></div>
                    <form action="${pageContext.request.contextPath}/About-Us"  method="POST">
                        <div class="form-group col-md-6">
                            <input type="text" name="name" class="form-control"  placeholder="Name">
                        </div>
                        <div class="form-group col-md-6">
                            <input type="email" name="email" class="form-control"  placeholder="Email">
                        </div>
                        <div class="form-group col-md-12">
                            <input type="text" name="subject" class="form-control"  placeholder="Subject">
                        </div>
                        <div class="form-group col-md-12">
                            <textarea name="message" id="imessage"   rows="10" cols="50" placeholder="Your Message Here"></textarea>
                        </div>                        
                        <div class="form-group col-md-12">
                            <input type="submit" name="submit" class="btn btn-primary pull-right" value="Submit">

                        </div>
                    </form>

                    <div class="contact-form">


                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="contact-info">
                        <h2 class="title text-center">Contact Info</h2>
                        <address>
                            <p>GearVN</p>
                            <p>Hoa Lac High Tech Park, Ha Noi</p>
                            <p>HaNoi VietNam</p>
                            <p>Email: CSKH@GEARVN.com</p>
                        </address>
                        <div class="social-networks">
                            <h2 class="title text-center">Social Networking</h2>
                            <ul>
                                <li>
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-google-plus"></i></a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-youtube"></i></a>
                                </li>
                            </ul>
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