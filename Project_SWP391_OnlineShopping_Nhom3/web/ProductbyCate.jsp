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
        <jsp:include page="headerlinhbe.jsp"></jsp:include>

            <section id="advertisement">
                <div class="container">
                    <img src="images/shop/advertisement.PNG" alt="" />
                </div>
            </section>

            <section>
                <div class="container">
                    <div class="row">
                    <%@include file="category.jsp" %>
                    <% ArrayList<Product> pl = (ArrayList<Product>) request.getAttribute("ProductList"); %>
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
                                            <p><a href="${pageContext.request.contextPath}/ProductDetail?pid=<%=pl.get(i).getPid()%>"><%=pl.get(i).getPname()%></a></p>
                                            <form action="${pageContext.request.contextPath}/Cart?pid=<%=pl.get(i).getPid()%>&quantity=1" method="POST">
                                                <button type="submit" class="btn btn-fefault add-to-cart">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    Add to cart
                                                </button>
                                            </form>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2>$<%=pl.get(i).getPrice()%></h2>
                                                <p><a href="${pageContext.request.contextPath}/ProductDetail?pid=<%=pl.get(i).getPid()%>"><%=pl.get(i).getPname()%></a></p>
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
                                        <ul class="nav nav-pills nav-justified">
                                            <li><a href=""><i class="fa fa-plus-square"></i>Add to wishlist</a></li>

                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <%}
                            %>

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