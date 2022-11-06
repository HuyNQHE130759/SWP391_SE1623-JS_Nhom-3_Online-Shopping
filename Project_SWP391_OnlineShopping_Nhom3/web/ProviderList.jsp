<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Provider List</title>
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
                    <form method="POST" action="/Provider/list">
                        <div class="row" style="margin: 16px 0;">
                            <div style="display: flex; flex-direction: row; align-items: center">
                                <div style="margin-right: 8px">Sort</div>
                                <select class="form-select" name="sort">
                                    <option value="provider_id" <c:if test="${requestScope.sort eq 'provider_id'}">selected="selected"</c:if>>id</option> 
                                <option value="provider_name" <c:if test="${requestScope.sort eq 'provider_name'}">selected="selected"</c:if>>name</option>
                                </select>
                            </div>

                            <div style="display: flex; flex-direction: row; align-items: center; margin-left: 10%;">
                                <div style="margin-right: 8px">Filter</div>
                                <select class="form-select" name="status">
                                    <option value="-1" <c:if test="${requestScope.status eq -1}">selected="selected"</c:if> >---Status---</option>
                                    <option value="1"<c:if test="${requestScope.status eq 1}">selected="selected"</c:if>>Enable</option>
                                    <option value="0"<c:if test="${requestScope.status eq 0}">selected="selected"</c:if>>Disable</option>
                                </select>
                            </div>
                            <div style="display: flex; flex-direction: row; align-items: right; margin-left: 10%;">
                                <input style="margin-left: 5%" type="text" name="search" placeholder="Search" <c:if test="${requestScope.search ne null}">value="${requestScope.search}"</c:if>>
                            </div>

                            <button style="margin-left: 5%;" type="submit" class="btn btn-primary">Search</button>

                        </div>
                    </form>
                    <div class="row">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">ProviderID</th>
                                    <th scope="col">ProviderName</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Status</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="p" items="${providers}">
                                <tr>
                                    <th scope="row">${p.provider_id}</td>
                                    <td>${p.provider_name}</td>
                                    <td>${p.provider_email}</td>
                                    <td>${p.provider_address}</td>
                                    <td>
                                        <c:if test="${p.status eq true}">Enable</c:if>
                                        <c:if test="${p.status eq false}">Disable</c:if>
                                    </td> 
                                    <td style="display: flex; flex-direction: row;">
                                        <a href="${pageContext.request.contextPath}/Provider/detail?pid=${p.provider_id}"> <input type="button" value="Update"> </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                                <div style="text-align: right;"><a href="${pageContext.request.contextPath}/Provider/detail"> <input type="button" value="Add new"> </a></div>
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
