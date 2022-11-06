<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.DAO"%>
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
       <% ArrayList<BillDetail> bi = (ArrayList<BillDetail>) request.getAttribute("listBill"); %>

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Order List</li>
                    </ol>
                </div>
                
                <a href="${pageContext.request.contextPath}/OrderListFilterDateAsc?page=1">Filter by Date ascend</a><br><br><br>
                
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td>STT</td>
                                <td class="user">UserName</td>
                                <td class="quantity">Quantity</td>
                                <td class="price">Total Price</td>
                                <td class="date">Date</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (int i = 0; i < bi.size(); i++) {%>
                                    <tr>
                                        <td> <%=bi.get(i).getRowNumber() %></td>
                                <td class="cart_product">
                                    <p> <%=bi.get(i).getUsername()%></p>
                                </td>
                                <td class="cart_description">
                                    <p><%=bi.get(i).getQuantity()%></p>
                                </td>
                                <td class="cart_price">
                                    <p>$<%=bi.get(i).getTotal() %></p>
                                </td>
                                <td >
                                    <p><%= bi.get(i).getDateCreate() %></p>
                                </td>
                                <td class="view"><a href="${pageContext.request.contextPath}/OrderListInformationController?bid=<%=bi.get(i).getBid()%>">View</a></td>
                            </tr>
                                <% }%>   
                        </tbody>
                    </table>
                </div>  
                        
                        <ul class="pagination">
                            <c:forEach begin="1" end="${endPage}" var="i">
                                <li ><a href="${pageContext.request.contextPath}/OrderList?page=${i}">${i}</a></li>
                            </c:forEach>
                            </ul>
                        
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