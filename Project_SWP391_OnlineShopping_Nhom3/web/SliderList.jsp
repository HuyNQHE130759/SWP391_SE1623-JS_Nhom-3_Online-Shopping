<%-- 
    Document   : OrderHistory
    Created on : Oct 24, 2022, 1:15:19 PM
    Author     : ngoclong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.FeedbackDAO"%>
<%@page import="entity.Review"%>
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
                            <li >Slider List</li>
                        </ol>
                    </div>
                    
                    
                    <div class="breadcrumbs" style="width: 300px;margin-bottom: 30px">                  
<!--                        <a href="${pageContext.request.contextPath}/OrderHistory?sort=1">Filter by Total Price ascend</a><br>
                    <a href="${pageContext.request.contextPath}/OrderHistory?sort=2">Filter by Date ascend</a><br>                    -->
                </div>

                <c:if test="${requestScope.listproduct.size() != 0}">
                    <div class="table-responsive cart_info">
                        <table class="table table-condensed">
                            <thead>
                                <tr><th class="cart_menu">STT</th>
                                    <th class="image">Product Name</th>
                                    <th class="image">Image</th>
                                    <th class="description"></th>       
                                </tr>
                            </thead>
                            <tbody>
                                    <c:forEach items="${requestScope.listproduct}" var="i" varStatus="loop">
                                        
                                    <tr>
                                        <td>${(loop.index + 1)}</td>
                                        
                                        <td class="cart_price">
                                            <p>${i.pname}</p>
                                        </td>
                                        <td class="cart_price">
                                            <img src="${i.image}" width="200px" height="200px" alt="alt"/>
                                        </td>
                                        <td class="view"><a href="${pageContext.request.contextPath}/SliderListInformation?pid=${i.pid}">View</a></td>
                                    </tr>

                                </c:forEach>
                                    
                            
                            </tbody>
                        </table>
                        
                    </div>
                </c:if>
                <c:if test="${requestScope.listproduct.size() == 0}">
                    <div>No data</div>
                </c:if>

               <ul class="pagination">
                    <c:forEach begin="1" end="${requestScope.numPage}" var="i">
                        <li><a href="${pageContext.request.contextPath}/SliderList?page=${i}">${i}</a></li>
                        </c:forEach>
                </ul>

                <br><br><br><br><br><br><br><br>
            </div>
        </section> 

        <jsp:include page="footer.jsp"></jsp:include>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
