<%@page import="model.User"%>
<%@page import="dal.DAO"%>
<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cart"%>
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
        <% ArrayList<Cart> cl = (ArrayList<Cart>) request.getAttribute("CartList"); %>
        <% ArrayList<Product> pl = (ArrayList<Product>) request.getAttribute("ProductList"); %>
        <%User us = (User) request.getSession().getAttribute("user"); %>
        <% DAO dao = new DAO(); double all = 0;  %>

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Shopping Cart</li>
                    </ol>
                </div>
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">Item</td>
                                <td class="description"></td>
                                <td class="price">Price</td>
                                <td class="quantity">Quantity</td>
                                <td class="total">Total</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < cl.size(); i++) {%>
                            <%pl = dao.getSingleProduct(cl.get(i).getpID()); %>
                            <% for (int y = 0; y < pl.size(); y++) {%>
                                    <tr>
                                <td class="cart_product">
                                    <a href=""><img src="<%=pl.get(y).getImage() %>" height="150px" width="200px" alt=""></a>
                                </td>
                                <td class="cart_description">
                                    <h4><a href=""><%=pl.get(y).getPname() %></a></h4>
                                    <p>Web ID: <%=pl.get(y).getPid() %></p>
                                </td>
                                <td class="cart_price">
                                    <p>$<%=pl.get(y).getPrice() %></p>
                                </td>
                                <td class="cart_quantity">
                                    <div class="cart_quantity_button">
                                        
                                        <input disabled="disabled" class="cart_quantity_input" type="text" name="quantity" value="<%=cl.get(i).getpQuantity() %>" autocomplete="off" size="2">
                                        
                                    </div>
                                </td>
                                <%double total = pl.get(y).getPrice() * cl.get(i).getpQuantity() ; %>
                                <td class="cart_total">
                                    <p class="cart_total_price">$<%=total %></p>
                                </td>
                                <td class="cart_delete">
                                    <a class="cart_quantity_delete" href="${pageContext.request.contextPath}/removeCart?pid=<%=pl.get(y).getPid() %>"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                            <%all += total; %>
                            <% } %>
                               <% }
                            %>
                            
                            
                            
                            
                        </tbody>
                    </table>
                </div>
                            <p class="cart_total_price clearfix "> Total price : $<%=all %></p> 
                            <form action="${pageContext.request.contextPath}/CheckOut"  method="POST">
                                <input type="hidden" name="total" value="<%=all %>">
                                <input type="hidden" name="cid" value="<%=us.getCid() %>">
                                <button class="btn btn-default check_out clearfix pull-right">Check Out</button>
                            </form>
                            
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