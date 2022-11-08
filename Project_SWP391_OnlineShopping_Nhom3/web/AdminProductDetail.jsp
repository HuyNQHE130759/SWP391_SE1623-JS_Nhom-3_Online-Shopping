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

            <jsp:include page="HeaderCustom_1.jsp"></jsp:include>

            <div class="container" style="margin-top: 16px;">
                <form method="post" action="/AdminProduct/detail" style="margin: auto; width: 50%">
                    <div class="form-group">
                        <label for="pid">Product ID:</label>
                        <input type="text" class="form-control" name="pid" placeholder="Product ID" readonly="readonly" value="${requestScope.pid}">
                    </div>
                    <div class="form-group">
                        <label for="pname">Product Name:<i style="color: red;">*</i></label>
                        <input type="text" class="form-control" name="pname" placeholder="Product Name" required="true" maxlength="100" value="${requestScope.product.pname}">
                    </div>
                    <div class="form-group">
                        <label for="Price">Price:<i style="color: red;">*</i></label>
                        <input type="number" class="form-control" pattern="[1-9][0-9]*" name="price" placeholder="Price" min="0" required="true" value="${requestScope.product.price}">$
                    </div>
                    <div class="form-group">
                        <label for="image">Image:<i style="color: red;">*</i></label>
                        <input type="text" class="form-control" name="image" placeholder="Image" required="true" value="${requestScope.product.image}">
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="description" placeholder="Description">${requestScope.product.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="status">Status:<i style="color: red;">*</i></label>
                         <select class="form-control" name="status">
                             <option value="1" <c:if test="${requestScope.product.status eq true}">selected="selected"</c:if>>Enable</option>
                            <option value="0" <c:if test="${requestScope.product.status ne true}">selected="selected"</c:if>>Disable</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="category">Category:<i style="color: red;">*</i></label>
                        <select class="form-control" name="category">
                            <c:forEach items="${requestScope.categoryList}" var="c">
                                <option value="${c.cateId}" <c:if test="${requestScope.product.cateId eq c.cateId}">selected="selected"</c:if>>${c.cateName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="category">Provider:<i style="color: red;">*</i></label>
                        <select class="form-control" name="provider">
                            <c:forEach items="${requestScope.providers}" var="pv">
                                <option value="${pv.provider_id}" <c:if test="${requestScope.product.provider.provider_id eq pv.provider_id}">selected="selected"</c:if>>${pv.provider_name}</option>
                            </c:forEach>
                        </select>
                    </div>    
                    <c:if test="${flag == false}">
                        <small id="error" class="form-text" style="color: red">${msg}</small>
                    </c:if>
                    
                    <button type="submit" class="btn btn-primary" style="margin: 0 auto">Save</button>
                    <a href="${pageContext.request.contextPath}/AdminProduct/list"><input type="button" class="btn" value="Cancel"></a>
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
