<%-- 
    Document   : OrderListInformation
    Created on : Oct 24, 2022, 2:15:25 PM
    Author     : ngoclong
--%>

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
        <% ArrayList<BillDetail> bd = (ArrayList<BillDetail>) request.getAttribute("billDetail"); %>

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Order detail</li>
                    </ol>
                </div>
    <% for (int i = 0; i < bd.size(); i++) {%>
    <div class="card">
    <img src="<%=bd.get(i).getImage()%>" alt="Product image" style="width:300px;height:300px">
    <h1><span style="font-weight: bold">Product:</span> <%=bd.get(i).getPname()%></h1>
    <p><span style="font-weight: bold">Price:</span> <%=bd.get(i).getPrice()%> $</p>
    <p><span style="font-weight: bold">Quantity:</span> <%=bd.get(i).getQuantity()%></p>
    <p><span style="font-weight: bold">Total:</span> <%=bd.get(i).getTotal()%> $</p>
    <p><span style="font-weight: bold">Date:</span> <%=bd.get(i).getDateCreate()%></p>
    <p><span style="font-weight: bold">Username:</span> <%=bd.get(i).getUsername()%></p>
    <p><span style="font-weight: bold">Full Name:</span> <%=bd.get(i).getFullName()%></p>
    <p><span style="font-weight: bold">Phone:</span> <%=bd.get(i).getPhone()%></p>
    <p><span style="font-weight: bold">Email:</span> <%=bd.get(i).getEmail()%></p>
    <p><span style="font-weight: bold">Address:</span> <%=bd.get(i).getAddress()%></p>
</div>
         <% } %>                    
                            
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
