<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Voucher</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
        <link rel="stylesheet" type="text/css" href="styles/responsive.css">
    </head>
    <body>
        <div class="super_container">
            <!-- Header -->

            <jsp:include page="HeaderCustom.jsp"></jsp:include>

                <div class="container" style="margin-top: 16px;">
                    <form method="post" action="AddVoucher" style="margin: auto; width: 50%">
<!--                        <div class="form-group">
                            <label for="code">Voucher Code<span style="color: red">*</span></label>
                            <input id="code" type="text" class="form-control" name="code" disabled="true" value="${code}">
                        </div>-->
                        <div class="form-group">
                            <label for="discount">Discount<span style="color: red">*</span></label>
                            <select class="form-control" name="discount" required="true">
                                <option value="10" ${discount == "10" ? 'selected' : ''}>10%</option>
                                <option value="20" ${discount == "20" ? 'selected' : ''}>20%</option>
                                <option value="30" ${discount == "30" ? 'selected' : ''}>30%</option>
                                <option value="40" ${discount == "40" ? 'selected' : ''}>40%</option>
                                <option value="50" ${discount == "50" ? 'selected' : ''}>50%</option>
                                <option value="60" ${discount == "60" ? 'selected' : ''}>60%</option>
                                <option value="70" ${discount == "70" ? 'selected' : ''}>70%</option>
                                <option value="80" ${discount == "80" ? 'selected' : ''}>80%</option>
                                <option value="90" ${discount == "90" ? 'selected' : ''}>90%</option>
                                <option value="100" ${discount == "100" ? 'selected' : ''}>100%</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <input type="text" class="form-control" name="description" placeholder="Enter description" value="${description != "" ? description : ""}">
                        </div>
                        <div class="form-group">
                            <label for="fullName">Time End</label>
                            <input type="date" class="form-control" name="timeEnd" placeholder="Select time end" required="true" value="${timeEnd != "" ? timeEnd : ""}">
                        </div>
                    <c:if test="${not empty msg}">
                        <small id="error" class="form-text" style="color: red">${msg}</small>
                    </c:if>
                    <button type="submit" class="btn btn-primary" style="margin: 0 auto">Add</button>
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
