<%-- 
    Document   : product
    Created on : Nov 9, 2020, 9:31:56 PM
    Author     : BVLT
--%>

<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <% %>
        <div class="col-sm-9 padding-right">
            <% ArrayList<Product> pl = (ArrayList<Product>) request.getAttribute("ProductList"); %>
            <div class="features_items"><!--features_items-->
                <h2 class="title text-center">Features Items</h2>
                <% for (int i = 0; i < pl.size(); i++) {%>
                <div class="col-sm-4">
                    <div class="product-image-wrapper">
                        <div class="single-products">
                            <div class="productinfo text-center">
                                <img src="<%=pl.get(i).getImage()%>" height="260px" width="160px" alt="" />
                                <h2>$<%=pl.get(i).getPrice()%></h2>
                                <a href="${pageContext.request.contextPath}/ProductDetail?pid=<%=pl.get(i).getPid()%>"><p><%=pl.get(i).getPname()%></p></a>
                                <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                            </div>
                            <div class="product-overlay">
                                <div class="overlay-content">
                                    <h2><%=pl.get(i).getPrice()%></h2>
                                    <a href="${pageContext.request.contextPath}/ProductDetail?pid=<%=pl.get(i).getPid()%>"><p><%=pl.get(i).getPname()%></p></a>
                                    <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <%}
                %>


            </div><!--features_items-->

        </div>

    </body>
</html>
