<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Import List</title>
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
        <script src="../js/pagger.js" type="text/javascript"></script>
        <link href="../css/pagger.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="super_container">
            <!-- Header -->

            <jsp:include page="HeaderCustom.jsp"></jsp:include>

                <div class="container">
                    <form method="POST" action="list">
                        <div class="row" style="margin: 16px 0;">
                            <div style="display: flex; flex-direction: row; align-items: center">
                                <div style="margin-right: 8px">Sort</div>
                                <select class="form-select" name="sort">
                                    <option value="a.importid" <c:if test="${requestScope.sort eq 'a.importid'}">selected="selected"</c:if>>Import ID</option> 
                                <option value="a.date" <c:if test="${requestScope.sort eq 'a.date'}">selected="selected"</c:if>>Date</option>
                                </select>
                            </div>

                            <div style="display: flex; flex-direction: row; align-items: center; margin-left: 5%;">
                                <div style="margin-right: 8px">Filter</div>
                                <select class="form-select" name="provider">
                                    <option value="" selected>---Provider---</option>
                                <c:forEach items="${requestScope.providers}" var="pr">
                                    <option value="${pr.provider_id}" <c:if test="${requestScope.provider eq pr.provider_id}">selected="selected"</c:if>>${pr.provider_name}</option>
                                </c:forEach>
                            </select>

                        </div>

                        <div style="display: flex; flex-direction: row; align-items: right; margin-left: 5%;">
                            <div style="margin-right: 8px">From</div>
                            <input type="date" name="from" value="${requestScope.from}">
                        </div>
                        <div style="display: flex; flex-direction: row; align-items: right; margin-left: 1%;">
                            <div style="margin-right: 8px">To</div>
                            <input type="date" name="to" value="${requestScope.to}">
                        </div>
                        <div style="display: flex; flex-direction: row; align-items: right; margin-left: 3%;">
                            <input style="margin-left: 5%" type="text" name="search" placeholder="Search" <c:if test="${requestScope.search ne null}">value="${requestScope.search}"</c:if>>
                            </div>

                            <button style="margin-left: 2%;" type="submit" class="btn btn-primary">Search</button>

                        </div>
                    </form>
                    <div class="row">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Import ID</th>
                                    <th scope="col">Product Name</th>
                                    <th scope="col">Provider Name</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Date</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="imp" items="${imports}">
                                <tr>
                                    <th scope="row">${imp.importId}</td>
                                    <td>${imp.product.pname}</td>
                                    <td>${imp.provider.provider_name}</td> 
                                    <td>$${imp.quantity}</td>
                                    <td>${imp.date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div style="text-align: right"><a href="${pageContext.request.contextPath}/Import/detail"><input type="button" value="Add new"></a></div>

                <div id="bot_pagger" class="pagger"></div>
                <script>
                    render('bot_pagger',${requestScope.pageindex}, ${requestScope.totalpage}, 1);
                </script>

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
