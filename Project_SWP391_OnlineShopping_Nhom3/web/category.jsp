<%@page import="dao.DAO"%>
<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CA TE GO RY</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <style>
            h4:hover {
                background-color: #d4d4d4;
            }
        </style>
    </head> 
    <body>
        <div class="col-sm-3">
            <div class="left-sidebar">
                <h2>Category</h2>
                <div class="panel-group category-products" id="accordian"><!--category-productsr-->

                    <% ArrayList<Category> cl = (ArrayList<Category>) request.getAttribute("categoryList");
                        DAO dao = new DAO();
                        cl = dao.getCategory();
                    %>
                    <% for (int i = 0; i < cl.size(); i++) {%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a href="${pageContext.request.contextPath}/Category?cid=<%=cl.get(i).getCateId()%>&index=1"><%=cl.get(i).getCateName()%></a></h4>
                        </div>
                    </div>         
                    <% }
                    %>
                </div><!--/category-products-->

                <div class="shipping text-center"><!--shipping-->
                    <img src="images/home/shipping.jpg" alt="" />
                </div><!--/shipping-->

            </div>
        </div>


    </body>
</html>
