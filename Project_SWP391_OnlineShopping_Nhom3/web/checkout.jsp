<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="entity.User"%>
<%@page import="dao.DAO"%>
<%@page import="entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Check Hehehe -->
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
        <% ArrayList<Cart> cl = (ArrayList<Cart>) request.getAttribute("CartList"); %>
        <% ArrayList<Product> pl = (ArrayList<Product>) request.getAttribute("ProductList"); %>
        <%User us = (User) request.getSession().getAttribute("user"); %>
        <% DAO dao = new DAO(); double all = 0;  %>

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Checkout</li>
                    </ol>
                </div>
                <a class="btn btn-default check_out " href="Cart">Change</a>
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <th > Id</th>
                                <th >Product Name</th>
                                <th class="product-name">Product Image</th>
                                <th class="product-price">Price</th>
                                <th class="product-quantity">Quantity</th>
                                <th class="product-subtotal">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cart" items="${sessionScope.cart.getItems()}">
                                <tr>
                                    <td >
                                        <a href="#">${cart.getProduct().getPid()}</a>
                                    </td>
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
                                                ${cart.getQuantity()}
                                            </div>
                                        </div>
                                    </td>
                                    <td class="product-subtotal" data-title="Total">
                                        <div class="price price-contain">
                                            <ins><span class="price-amount"><span class="currencySymbol">$</span>${cart.getProduct().getPrice()*cart.getQuantity()}</span></ins>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-md-3"> 
                        <form action="${pageContext.request.contextPath}/CheckOut"  method="get">
                            <input class="form-control" type="text" name="code" value="${param.code}" required="" placeholder="Code" maxlength="10" ><br>
                            <button class="btn btn-default check_out " type="submit">Check Coupon</button>
                        </form>
                        <p class="cart_total_price clearfix "> Original price : $${sessionScope.cart.getTotalBill()}</p> 
                        <p class="cart_total_price clearfix "> Discount  : ${discount}%</p> 
                        <p class="cart_total_price clearfix "> Discount price : $${discountTotal}</p> 

                    </div>
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <h2>Enter Information of receiver</h2>
                        <form action="${pageContext.request.contextPath}/CheckOut"  method="POST">
                            <input class="form-control" type="hidden" name="discountTotal" value="${discountTotal}"  ><br>
                            <input class="form-control" type="text" name="Name" required="" placeholder="Name" maxlength="50" ><br>
                            <input class="form-control" type="text" name="Address" required placeholder="Address"  maxlength="50" ><br>
                            <input class="form-control" type="text" required name="Phone" placeholder="Phone"  maxlength="10" ><br>
                            <button class="btn btn-default check_out " type="submit">Check Out</button>
                        </form>
                    </div>
                </div>

                <br><br><br><br><br><br>
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