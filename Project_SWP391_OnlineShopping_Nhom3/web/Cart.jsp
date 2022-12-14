<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="entity.User"%>
<%@page import="dao.DAO"%>
<%@page import="entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Cart"%>
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
                            <li class="active">Shopping Cart/${sessionScope.cart.getItems().size()}</li>
                    </ol>
                </div>
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <th >Product Name</th>
                                <th class="product-name">Product Image</th>
                                <th class="product-price">Price</th>
                                <th class="product-quantity">Quantity</th>
                                <th class="product-subtotal">Total</th>
                                <th class="product-subtotal">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cart" items="${sessionScope.cart.getItems()}">
                                <tr>
                                    <td >
                                        <a href="#">${cart.getProduct().getPname()}</a>
                                    </td>
                                    <td class="product-thumbnail" data-title="Product Name">

                                        <a class="prd-thumb" href="#">
                                            <figure><img width="113" height="113" src="${cart.getProduct().getImage()}" alt="shipping cart"></figure>
                                        </a>
                                    </td>

                                    <td class="product-price" data-title="Price">
                                        <div class="price price-contain">
                                            <ins><span class="price-amount"><span class="currencySymbol">$</span>${cart.getProduct().getPrice()}</span></ins>
                                        </div>
                                    </td>
                                    <td class="product-quantity" data-title="Quantity">
                                        <div class="quantity-box type1">
                                            <div class="qty-input">
                                                <a href="ChangeCartQuantity?pid=${cart.getProduct().getPid()}&quantity=${cart.getQuantity()-1}">-</a>
                                                ${cart.getQuantity()}
                                                <a href="ChangeCartQuantity?pid=${cart.getProduct().getPid()}&quantity=${cart.getQuantity()+1}">+</a>

                                            </div>
                                        </div>
                                    </td>
                                    <td class="product-subtotal" data-title="Total">
                                        <div class="price price-contain">
                                            <ins><span class="price-amount"><span class="currencySymbol">$</span>${cart.getProduct().getPrice()*cart.getQuantity()}</span></ins>
                                        </div>
                                    </td>
                                    <td class="product-subtotal" data-title="Total">
                                        <div class="action">
                                            <a href="./DeleteCartItem?pid=${cart.getProduct().getPid()}"style="color: red; font-size: 40px;" class="remove"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <c:if test="${sessionScope.cart.getItems().size()!=0}">
                    <p class="cart_total_price clearfix "> Total price : $${sessionScope.cart.getTotalBill()}</p>
                    <form action="${pageContext.request.contextPath}/CheckOut"  method="get">
                        <!--<input type="hidden" name="total" value="${sessionScope.cart.getTotalBill()}">-->
                        <button class="btn btn-default check_out clearfix pull-right">Check Out </button>
                    </form>
                </c:if>


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