<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Guarantee</title>
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
                    <form method="POST" action="ListGuarantee">
                        <div class="row" style="margin-top: 16px">
                            <div style="display: flex; align-items: center; flex-direction: row;margin-top: 16px">
                                <div style="margin-right: 16px;">Status</div>
                                <select class="form-select" name="status" style="margin-right: 16px;">
                                    <option value="" ${sessionScope.status == '' ? 'selected' : ''}>All</option>
                                    <option value="inprogress" ${sessionScope.status == 'inprogress' ? 'selected' : ''}>In Progress</option>
                                    <option value="done" ${sessionScope.status == 'done' ? 'selected' : ''}>Done</option>
                                    <option value="canceled" ${sessionScope.status == 'canceled' ? 'selected' : ''}>Canceled</option>
                                </select>
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>

                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Product</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Owner</th>
                                        <th scope="col">Pick up date</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="i" items="${listGuarantee}">
                                    <tr>
                                        <th scope="col">${i.id}</th>
                                        <td scope="col">${i.product}</td>
                                        <td scope="col">${i.description}</td>
                                        <td scope="col">${i.status}</td>
                                        <td scope="col">${i.owner}</td>
                                        <td scope="col">${i.pickUpDate}</td>
                                        <td scope="col">
                                            <c:if test="${sessionScope.roleName == 'Admin'}">
                                                <a href="EditGuarantee?gid=${i.id}">Edit</a>
                                            </c:if>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="flex text-center">
                        <c:if test="${numberPage != 1}">
                            <c:forEach var="page" begin="1" end="${numberPage}">
                                <c:if test="${pageCurrent == page}">
                                    <a href="ListGuarantee?page=${page}" class="mx-2" style="font-weight: bold">${page}</a>
                                </c:if>
                                <c:if test="${pageCurrent != page}">
                                    <a href="ListGuarantee?page=${page}" class="mx-2" style="color: black">${page}</a>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>

                    <c:if test="${sessionScope.roleName == 'User'}">
                        <a style="color: white;" class="btn btn-primary" href="SendRequestGuarantee">Send Request Guarantee</a>
                    </c:if>

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
