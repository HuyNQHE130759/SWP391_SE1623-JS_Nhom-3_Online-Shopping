<!--
 Recordofchange:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 1/10/2022      2.0                LinhNT           First Implement
 
-->

<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>ProductbyCate</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">

    </head>

    <body>
        
       

            <section id="advertisement">
               
            </section>

            <section>
                <div class="container">
                    <div class="row">
                    <%@include file="category.jsp" %>
                    <% ArrayList<Product> pl = (ArrayList<Product>) request.getAttribute("ProductList"); 
                       double totalPage = (double) request.getAttribute("totalPage");
                       String strCid = request.getParameter("cid");
                       int cid = Integer.parseInt(strCid);
                    %>
                    <div class="col-sm-9 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">All Product</h2>
                            <% for (int i = 0; i < pl.size(); i++) {%>
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="<%=pl.get(i).getImage()%>" alt="" />
                                            <h2>$<%=pl.get(i).getPrice()%></h2>
                                            <a href="${pageContext.request.contextPath}/ProductDetail?pid=<%=pl.get(i).getPid()%>"><p><%=pl.get(i).getPname()%></p></a>
                                            <form action="${pageContext.request.contextPath}/Cart?pid=<%=pl.get(i).getPid()%>&quantity=1" method="POST">
                                                <button type="submit" class="btn btn-fefault add-to-cart">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    Add to cart
                                                </button>
                                            </form>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2><%=pl.get(i).getPrice()%></h2>
                                                <a href="${pageContext.request.contextPath}/ProductDetail?pid=<%=pl.get(i).getPid()%>"><p><%=pl.get(i).getPname()%></p></a>
                                                <form action="${pageContext.request.contextPath}/Cart?pid=<%=pl.get(i).getPid()%>&quantity=1" method="POST">
                                                    <button type="submit" class="btn btn-fefault add-to-cart">
                                                        <i class="fa fa-shopping-cart"></i>
                                                        Add to cart
                                                    </button>  
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="choose">

                                    </div>
                                </div>
                            </div>

                            <%}
                            %>
                            <!--phan trang-->
                            
                            
                            <div class="justify-content-center" style="text-align: center">
                                <ul class="pagination pagination-sm">
                                    <%for (int i = 1; i <= totalPage; i++) {
                                    %>
                                    <li class="page-item">                          
                                        <a href="${pageContext.request.contextPath}/Category?index=<%=i%>&cid=<%=cid%>"><%=i%></a>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>
                        </div><!--features_items-->
                    </div>
                </div>
            </div>
        </section>

                                    <jsp:include page="footer.jsp"></jsp:include>


        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>