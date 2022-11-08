

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
        <link rel="stylesheet" type="text/css" href="styles/responsive.css">
    </head>
    <body>
        <!-- Header -->

        <header class="trans_300">
            <!-- Main Navigation -->

            <div class="main_nav_container">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 text-right">

                            <div class="logo pull-left">
                                <a href="${pageContext.request.contextPath}/HomePage"><img src="https://webmedia.com.vn/images/2021/07/logo-gearvn.png"  alt="" /></a>
                            </div>
                            <ul class="navbar_menu">
                                <li><a href="${pageContext.request.contextPath}/HomePage">home</a></li>
                                <li><a href="#">shop</a></li>
                                    <c:if test="${sessionScope.user != null && sessionScope.user.username != 'Huyy'}">
                                    <li><a href="${pageContext.request.contextPath}/OrderList?page=1">order list</a></li>
                                    <li><a href="${pageContext.request.contextPath}/OrderHistory">order history</a></li>
                                    </c:if>

                                <li><a href="${pageContext.request.contextPath}/About-Us">about us</a></li>
                                <li><a href="ListUser">list user</a></li>
                            </ul>
                            <nav class="navbar">

                                <ul class="navbar_user">
                                    <li><a href="#"><i class="fa fa-search" aria-hidden="true"></i></a></li>
                                    <li><a href="${pageContext.request.contextPath}/Login"><i class="fa fa-lock"></i></a></li>

                                    <li><a href="${pageContext.request.contextPath}/AccountInfo"><i class="fa fa-user"></i></a></li>

                                    <li class="checkout">
                                        <a href="#">
                                            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                </ul>
                                <div class="hamburger_container">
                                    <i class="fa fa-bars" aria-hidden="true"></i>
                                </div>

                            </nav>
                            <nav class="navbar">
                                <ul class="navbar_menu">
                                    <c:if test="${sessionScope.user != null && sessionScope.user.username == 'Huyy'}">
                                        <li><a href="${pageContext.request.contextPath}/AdminProduct/list">Product List</a></li>
                                        <li><a href="${pageContext.request.contextPath}/Category/list">Category List</a></li>
                                        <li><a href="${pageContext.request.contextPath}/Provider/list">Provider List</a></li>
                                        <li><a href="${pageContext.request.contextPath}/Import/list">Import History</a></li>
                                        </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>

        </header>
    </body>
</html>
