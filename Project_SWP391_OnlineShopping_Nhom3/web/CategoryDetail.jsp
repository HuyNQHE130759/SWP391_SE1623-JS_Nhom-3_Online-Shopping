<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Detail</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="../styles/bootstrap4/bootstrap.min.css">
        <link href="../plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="../plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="../plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="../plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="../styles/main_styles.css">
        <link rel="stylesheet" type="text/css" href="../styles/responsive.css">
    </head>
    <body>
        <div class="super_container">
            <!-- Header -->

            <jsp:include page="HeaderCustom.jsp"></jsp:include>

            <div class="container" style="margin-top: 16px;">
                <form method="post" action="Category/detail" style="margin: auto; width: 50%">
                    <div class="form-group">
                        <label for="cid">Category ID:</label>
                        <input type="text" class="form-control" name="cid" placeholder="Category ID" readonly="readonly" value="${requestScope.cid}">
                    </div>
                    <div class="form-group">
                        <label for="cname">Category Name:</label>
                        <input type="text" class="form-control" name="cname" placeholder="Category Name" required="true" value="${requestScope.category.cateName}">
                    </div>
                    <div class="form-group">
                        <label for="image">Image:</label>
                        <input type="text" class="form-control" name="image" placeholder="Image" required="true" value="${requestScope.category.image}">
                    </div>
                    <div class="form-group">
                        <label for="status">Status</label>
                         <select class="form-control" name="status">
                             <option value="1" <c:if test="${requestScope.category.status eq true}">selected="selected"</c:if>>Enable</option>
                            <option value="0" <c:if test="${requestScope.category.status ne true}">selected="selected"</c:if>>Disable</option>
                        </select>
                    </div>
                    <c:if test="${flag == false}">
                        <small id="error" class="form-text" style="color: red">${msg}</small>
                    </c:if>
                    
                    <button type="submit" class="btn btn-primary" style="margin: 0 auto">Save</button>
                    <a href="${pageContext.request.contextPath}/Category/list"><input type="button" class="btn" value="Cancel"></a>
                </form>

            </div>


            <!-- Footer -->
            <jsp:include page="FooterCustom.jsp"></jsp:include>

        </div>

        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="styles/bootstrap4/popper.js"></script>
        <script src="styles/bootstrap4/bootstrap.min.js"></script>
        <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
        <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
        <script src="plugins/easing/easing.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>
