<%-- 
    Document   : header
    Created on : Nov 6, 2020, 11:35:08 PM
    Author     : BVLT
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
    </head>
    <body>
        <%User us = (User) request.getSession().getAttribute("user"); %>
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="contactinfo">
                                <ul class="nav nav-pills">
                                    <li><a href="#"><i class="fa fa-phone"></i> +016 731 2838</a></li>
                                    <li><a href="#"><i class="fa fa-envelope"></i> huynq14@gmail.com</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="social-icons pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header_top-->

            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 clearfix">
                            <div class="logo pull-left">
                                <a href="${pageContext.request.contextPath}/HomePage"><img src="https://webmedia.com.vn/images/2021/07/logo-gearvn.png"  alt="" /></a>
                            </div>
                            
                        </div>
                        <div class="col-md-8 clearfix">
                            <div class="shop-menu clearfix pull-right">
                                <ul class="nav navbar-nav">
                                    
                                    
                                    <li><a href="${pageContext.request.contextPath}/Cart"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                                        <% if (us != null) {%>
                                    <li>Welcome <%=us.getUsername()%></li>
                                    <li><a href="${pageContext.request.contextPath}/AccountInfo"><i class="fa fa-user"></i> Account</a></li>
                                    <li><a href="${pageContext.request.contextPath}/Bye"><input type="button" value="Logout"></a></li>
                                    <%if(us.isStatus() == true ){ %>
                                    <a href="${pageContext.request.contextPath}/AdminConsole"><input type="button" value="AdminConsole"></a>
                                    <% } %>
                                        <% } else { %>
                                    <li><a href="${pageContext.request.contextPath}/Login"><i class="fa fa-lock"></i> Login</a></li>
                                        <% }%>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->

            <div class="header-bottom"><!--header-bottom-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="${pageContext.request.contextPath}/HomePage" class="active">Home</a></li>
                                    <li class="dropdown"><a href="#">View Products<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="${pageContext.request.contextPath}/Product">List Our Products</a></li>
                                             
                                            
                                            <li><a href="${pageContext.request.contextPath}/Cart">Cart</a></li> 
                                            
                                        </ul>
                                    </li> 

                                    <li><a href="${pageContext.request.contextPath}/About-Us">About Us</a></li>
                                </ul>
                            </div>
                        </div>
<!--                        <div class="col-sm-3">
                            <div class="search_box pull-right">
                                <input type="text" placeholder="Search"/>
                            </div>
                        </div>-->
                    </div>
                </div>
            </div><!--/header-bottom-->
        </header><!--/header-->
    </body>
</html>
