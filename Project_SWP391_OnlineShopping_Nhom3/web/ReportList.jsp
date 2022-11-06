<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Report</title>
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
                <div class="container">
                    <div style="display: flex; align-items: center; flex-direction: row;margin-top: 16px">
                        <div style="margin-right: 16px;">Status</div>
                        <select class="form-select" name="selectStatus" style="margin-right: 16px;">
                            <option value="" ${selectedStatusShipping == "" ? 'selected' : ''}>All</option>
                        <option value="inprogress" ${sessionScope.selectedStatus == "inprogress" ? 'selected' : ''}>In Progress</option>
                        <option value="done" ${sessionScope.selectedStatus == "done" ? 'selected' : ''}>Done</option>
                        <option value="canceled" ${sessionScope.selectedStatus == "canceled" ? 'selected' : ''}>Canceled</option>
                    </select>
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>


                <div class="row" style="margin-top: 16px">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Bill ID</th>
                                <th scope="col">Product</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Price</th>
                                <th scope="col">Total</th>
                                <th scope="col">Shipper account</th>
                                <th scope="col">Address</th>
                                <th scope="col">Phone</th> 
                                <th scope="col">Date Created</th> 
                                <th scope="col">Status Shipping</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${listReport}">
                                <tr>
                                    <th scope="col">${i.billId}</th>
                                    <td scope="col">${i.productName}</td>
                                    <td scope="col">${i.quantity}</td>
                                    <td scope="col">${i.price}</td>
                                    <td scope="col">${i.totalPrice}</td>
                                    <td scope="col">${i.shipperAccount}</td>
                                    <td scope="col">${i.address}</td>
                                    <td scope="col">${i.phone}</td> 
                                    <td scope="col">${i.dateCreated}</td> 
                                    <td scope="col">${i.statusShipping}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="flex text-center">
                    <c:if test="${numberPage != 1}">
                        <c:forEach var="page" begin="1" end="${numberPage}">
                            <c:if test="${pageCurrent == page}">
                                <a href="Report?page=${page}" class="mx-2" style="font-weight: bold">${page}</a>
                            </c:if>
                            <c:if test="${pageCurrent != page}">
                                <a href="Report?page=${page}" class="mx-2" style="color: black">${page}</a>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>

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