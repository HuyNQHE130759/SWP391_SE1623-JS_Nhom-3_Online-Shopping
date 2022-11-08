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
                        
                        
                    <form method="get" action="SliderListInformation">
                    <div class="row" style="margin: 16px 0; display: flex;width: 100vw">
                        <div style="display: flex; flex-direction: row; align-items: center; margin-left: 20%;">
                            <div style="margin-right: 8px">Filter</div>
                            <select class="form-select" name="selectStatus">
                                <option value="" selected >---Status---</option>
                                <option value="1">Hide</option>
                                <option value="0">UnHide</option>
                            </select>
                            <select class="form-select" name="selectRating">
                                <option value="" selected>---Rating---</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        
                        <div style="display: flex; flex-direction: row; align-items: right; margin-left: 10%;">
                            <input style="margin-left: 5%" type="text" name="search" placeholder="Search by username" value="">
                            </div>
                        <input  type="hidden" name="pid" value="${pid}">
                        <button style="margin-left: 2%;" type="submit" class="btn btn-primary">Search</button>

                    </div>
                </form>
                        
                </div>
                    
                    <h2>The number of comments is  hidden: ${requestScope.countHide}</h2>
                    <h2>The number of comments is not hidden: ${requestScope.countUnHide}</h2>
                    
                    <c:if test="${requestScope.list.size() != 0}">
                    <div class="table-responsive cart_info">
                        <table class="table table-condensed" >
                            <thead>
                                <tr><th class="cart_menu">STT</th>
                                    <th class="image">Name User</th>
                                    <th class="image">Comment</th>
                                    <th class="image">Rating</th>
                                    <th class="description">Time comment</th>
                                    <th class="description"></th>
                                    <th class="description"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.list}" var="i" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td class="cart_price">
                                            <p>${i.fullname}</p>
                                        </td>
                                        <td class="cart_total">
                                            <p class="cart_total_price" style="max-width: 300px; overflow: hidden">${i.user_comment}</p>
                                        </td>
                                        <td class="cart_total">
                                            <p class="cart_total_price" style="text-align: center">${i.rating}</p>
                                        </td>
                                        <td class="cart_total">
                                            <p class="cart_total_price">${i.usertime_comment}</p>
                                        </td>
                                        <c:if test="${i.isHide==false}">
                                        <td class="view"><a href="hide-slider?id=${i.reviewid}&hide=1&pid=${i.pid}">Hide</a></td>
                                        </c:if>
                                        <c:if test="${i.isHide==true}">
                                        <td class="view"><a href="hide-slider?id=${i.reviewid}&hide=0&pid=${i.pid}">UnHide</a></td>
                                        </c:if>
                                        
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

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
