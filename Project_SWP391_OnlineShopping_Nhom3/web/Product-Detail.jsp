<%@page import="entity.Review"%>
<%@page import="entity.User"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Product Details | E-Shopper</title>
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

        <section>
            <div class="container">
                <div class="row">
                    <%@include file="category.jsp" %>
                    <% ArrayList<Product> pl = (ArrayList<Product>) request.getAttribute("ProductList"); %>
                     <% ArrayList<Review> rl = (ArrayList<Review>) request.getAttribute("ReviewList"); %>
                     <%User us = (User) request.getSession().getAttribute("user"); %>
                    <% for (int i = 0; i < pl.size(); i++) {%>


                    <div class="col-sm-9 padding-right">
                        <div class="product-details"><!--product-details-->

                            <div class="col-sm-5">
                                <div class="view-product">
                                    <img src="<%=pl.get(i).getImage()%>" alt="" />

                                </div>
                                <div id="similar-product" class="carousel slide" data-ride="carousel">

                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner">


                                    </div>

                                    
                                </div>

                            </div>
                            <div class="col-sm-7">
                                <div class="product-information"><!--/product-information-->
                                    <img src="images/product-details/new.jpg" class="newarrival" alt="" />
                                    <h2><%=pl.get(i).getPname()%></h2>
                                    <p>Web ID: <%=pl.get(i).getPid()%></p>
                                    <img src="images/product-details/rating.png" alt="" />
                                    <span>
                                        <span>US $<%=pl.get(i).getPrice()%></span>
                                        
                                        <%if (us != null) { %>
                                        <form action="${pageContext.request.contextPath}/Cart"  method="POST">
                                            <label>Quantity:</label>
                                            <input type="number" name="quantity" min="1"  value="0" />
                                            
                                            <input type="hidden" name="pid" value="<%=pl.get(i).getPid()%>">
                                            <input type="hidden" name="cid" value="<%=us.getCid()%>">
                                            <button type="submit" class="btn btn-fefault cart">
                                            <i class="fa fa-shopping-cart"></i>
                                            Add to cart
                                        </button>
                                        </form>
                                            
                                        
                                        <% }else{ %>
                                            You have to log in in order to purchase this item
                                        <% } %>
                                        
                                    </span>
                                    <p><b>Availability:</b> In Stock</p>
                                    <p><b>Condition:</b> New</p>
                                    
                                    <a href=""><img src="images/product-details/share.png" class="share img-responsive"  alt="" /></a>
                                </div><!--/product-information-->
                            </div>
                        </div><!--/product-details-->

                        <div class="category-tab shop-details-tab"><!--category-tab-->
                            <div class="col-sm-12">
                                <ul class="nav nav-tabs">
                                    <li><a href="#details" data-toggle="tab">Details</a></li>
                                    <li class="active"><a href="#reviews" data-toggle="tab">Reviews (<%= rl.size() %>)</a></li>
                                </ul>
                            </div>
                            <div class="tab-content">
                                <div class="tab-pane fade" id="details" >
                                    <%=pl.get(i).getDescription()%>
                                </div>

                                <div class="tab-pane fade" id="companyprofile" >
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery1.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery3.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery2.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery4.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane fade" id="tag" >
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery1.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery2.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery3.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery4.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane fade active in" id="reviews" >
                                    <div class="col-sm-12">
                                        <% for (int y = 0; y < rl.size(); y++) {%>
                                        <%  User u = new User();
                                            u = dao.getSingleUser(rl.get(y).getCid()); %>
                                                <ul>
                                                    <li><a href=""><i class="fa fa-user"></i><%=u.getUsername() %></a></li>
                                                    <li><a href=""><i class="fa fa-calendar-o"></i><%=rl.get(y).getUsertime_comment()  %></a></li>
                                        </ul>
                                                    <p><%=rl.get(y).getUser_comment() %></p>
                                                    -------------------------------------------------------------------------
                                            <%}
                                        %>
                                        
                                        <p><b>Write Your Review</b></p>
                                        
                                        <%if (us == null) {%>
                                        <p>You have to Log In in order to post comment!!</p>
                                        <% } else { %>
                                        <form action="${pageContext.request.contextPath}/ReviewController" method="POST">
                                            <span>
                                                <%=us.getFullName() %>
                                                <input type="hidden" name="username" value="<%=us.getCid() %>">
                                                <input type="hidden" name="pid" value="<%=pl.get(i).getPid() %>">
                                            </span>
                                            <textarea name="comment" ></textarea>
                                            <label for="Rating">Rating: </label>
                                        <select name="rating" id="rating">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                            <option value="7">7</option>
                                            <option value="8">8</option>
                                            <option value="9">9</option>
                                            <option value="10">10</option>
                                        </select>
                                        <br>
                                        <input type="submit" name="Save" value="PostComment">
                                        </form>
                                        <% } %>


                                    </div>
                                </div>

                            </div>
                        </div><!--/category-tab-->

                        <div class="recommended_items"><!--recommended_items-->
                            
                        </div><!--/recommended_items-->

                    </div>
                    <% }%>
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
