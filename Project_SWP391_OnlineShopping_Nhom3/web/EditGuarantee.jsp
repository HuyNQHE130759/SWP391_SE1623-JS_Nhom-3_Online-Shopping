<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Guarantee</title>
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
                    <form method="post" action="EditGuarantee" style="margin: auto; width: 50%">
                        <div class="form-group">
                            <input type="text" disabled="" class="form-control" name="guaranteeID" value="${guaranteeID}">
                        </div>

                        <div class="form-group">
                            <label for="status">Status<span style="color: red">*</span></label>
                            <select class="form-control" name="status" required="true">
                                <option value="inprogress" ${guarantee.status == "inprogress" ? 'selected' : ''}>In Progress</option>
                                <option value="done" ${guarantee.status == "done" ? 'selected' : ''}>Done</option>
                                <option value="canceled" ${guarantee.status == "canceled" ? 'selected' : ''}>Canceled</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="pickUpDate">Pick Up Date</label>
                            <input type="date" class="form-control" name="pickUpDate" required="true" value="${guarantee.pickUpDate != "" ? guarantee.pickUpDate : ""}">
                        </div>
                    <c:if test="${not empty msg}">
                        <small id="error" class="form-text" style="color: red">${msg}</small>
                    </c:if>
                    <button type="submit" class="btn btn-primary" style="margin: 0 auto">Update</button>
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
