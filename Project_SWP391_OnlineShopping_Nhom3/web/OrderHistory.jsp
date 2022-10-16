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
        <% ArrayList<BillDetail> cl = (ArrayList<BillDetail>) request.getAttribute("history"); %>
        <%int number = cl.get(0).getCid();%>
        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li >Order History</li>
                    </ol>
                </div>
                <div class="breadcrumbs" style="width: 300px;margin-bottom: 30px">
                    
                        <a href="${pageContext.request.contextPath}/OrderHistoryFilterTotalAsc?page=1&cid=<%=number%>">Filter by Total Price ascend</a><br>
                        <a href="${pageContext.request.contextPath}/OrderHistoryFilterDateAsc?page=1&cid=<%=number%>">Filter by Date ascend</a><br>
                      
                        
                </div>
                
                <div class="table-responsive cart_info">
                    
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">STT</td>
                                <td class="image">Product</td>
                                <td class="description">Image</td>
                                 <td class="quantity">Quantity</td>
                                <td class="price">Total Price</td>
                                <td class="quantity">Date</td>
                                 <td class="quantity"></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < cl.size(); i++) {%>
                          
                                    <tr>
                                        <td> <%=cl.get(i).getRowNumber() %></td>
                                        <td class="cart_description">
                                    <p><%=cl.get(i).getPname() %></p>
                                </td>
                                <td class="cart_product">
                                    <a href=""><img src="<%=cl.get(i).getImage() %>" height="150px" width="200px" alt=""></a>
                                </td>
                                 <td class="cart_quantity">
                                    <div class="cart_quantity_button">
                                        
                                        <p><%=cl.get(i).getQuantity()%></p>
                                        
                                    </div>
                                </td>
                                <td class="cart_price">
                                    <p>$<%=cl.get(i).getTotal() %></p>
                                </td>
                               
                                
                                <td class="cart_total">
                                    <p class="cart_total_price"><%=cl.get(i).getDateCreate() %></p>
                                </td>
                                 <td class="view"><a href="${pageContext.request.contextPath}/OrderInformation?cid=<%=cl.get(i).getCid()%>&bid=<%=cl.get(i).getBid()%>">View</a></td>
                            </tr>
                            
                            <% } %>
                              
                            
                            
                        </tbody>
                    </table>
                </div>
                            
                            <ul class="pagination">
                                <c:forEach begin="1" end="${endPage}" var="i">
                                <li><a href="${pageContext.request.contextPath}/OrderHistory?page=${i}&cid=<%=number%>">${i}</a></li>
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