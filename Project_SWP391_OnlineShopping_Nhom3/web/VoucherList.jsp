<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Voucher List</title>
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
        <script>
            function copyToClipboard($event: Event) {
                // Copy text to clipboard
                console.log($event);
                navigator.clipboard.writeText(code);
                // Alert the copied text
                alert("Copied the text: " + code);
            }
        </script>

        <div class="super_container">
            <!-- Header -->
            <jsp:include page="HeaderCustom.jsp"></jsp:include>
                <div class="container" style="margin-top: 16px;">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Voucher Code</th>
                                <th scope="col">Description</th>
                                <th scope="col">Time End</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="i" items="${listVoucher}">
                            <tr>
                                <td style="display: flex; flex-direction: row; justify-content: space-between;">
                                    <span id="code-${i.code}">${i.code}</span>
                                    <span style="cursor: pointer; color: blue;" onclick="copyToClipboard($event)">Copy</span>
                                </td>
                                <td>${i.description}</td>
                                <td>${i.timeEnd}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a style="color: white;" class="btn btn-primary" href="/AddVoucher">Add Voucher</a>
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
