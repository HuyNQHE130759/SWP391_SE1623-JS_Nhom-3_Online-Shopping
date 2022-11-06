<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Provider Detail</title>
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
                <form method="post" action="/Provider/detail" style="margin: auto; width: 50%">
                    <div class="form-group">
                        <label for="pid">Provider ID:</label>
                        <input type="text" class="form-control" name="pid" placeholder="Provider ID" readonly="readonly" value="${requestScope.pid}">
                    </div>
                    <div class="form-group">
                        <label for="pname">Provider Name:<i style="color: red;">*</i></label>
                        <input type="text" class="form-control" name="pname" placeholder="Provider Name" required="true" maxlength="50" value="${requestScope.provider.provider_name}">
                    </div>
                    <div class="form-group">
                        <label for="email">Email:<i style="color: red;">*</i></label>
                        <input type="text" class="form-control" name="email" placeholder="Email" required="true" maxlength="50" value="${requestScope.provider.provider_email}">
                    </div>
                    <div class="form-group">
                        <label for="address">Address:<i style="color: red;">*</i></label>
                        <input type="text" class="form-control" name="address" placeholder="Address" required="true" maxlength="50" value="${requestScope.provider.provider_address}">
                    </div>
                    <div class="form-group">
                        <label for="status">Status<i style="color: red;">*</i></label>
                         <select class="form-control" name="status">
                             <option value="1" <c:if test="${requestScope.provider.status eq true}">selected="selected"</c:if>>Enable</option>
                            <option value="0" <c:if test="${requestScope.provider.status eq false}">selected="selected"</c:if>>Disable</option>
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
