<%-- 
    Document   : OrderHistoryInformation
    Created on : Oct 24, 2022, 1:33:48 PM
    Author     : ngoclong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.OrderDAO"%>
<%@page import="entity.BillDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart | E-Shopper</title>
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
            <section id="cart_items">
                <div class="container">
                    <div class="breadcrumbs">
                        <ol class="breadcrumb">
                            <li><a href="#">Home</a></li>
                            <li class="active">Order Information</li>
                        </ol>
                    </div>

                <c:forEach items="${requestScope.billDetail}" var="i">
                    <div class="card">
                        <a href="ProductDetail?pid=${i.key.pid}"><img src="${i.key.image}" alt="Product image" style="width:300px;height:300px"></a>
                        <h1><span style="font-weight: bold">Product:</span>${i.key.pname}</h1>
                        <p><span style="font-weight: bold">Price:</span>${i.key.price}$</p>
                        <p><span style="font-weight: bold">Quantity:</span>${i.value}</p>
                        <p><span style="font-weight: bold">Total:</span>${i.key.price * i.value}$</p>
                    </div>
                </c:forEach>


                <br><br><br><br><br><br><br><br>
            </div>
        </section> <!--/#cart_items-->

        <jsp:include page="footer.jsp"></jsp:include>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
