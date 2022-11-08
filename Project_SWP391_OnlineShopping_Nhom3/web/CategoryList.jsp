<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Category List</title>
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

            <jsp:include page="HeaderCustom_1.jsp"></jsp:include>

                <div class="container">
                    <form method="POST" action="/Category/list">
                        <div class="row" style="margin: 16px 0;">
                            <div style="display: flex; flex-direction: row; align-items: center">
                                <div style="margin-right: 8px">Sort</div>
                                <select class="form-select" name="sort">
                                    <option value="cateId" <c:if test="${requestScope.sort eq 'cateId'}">selected="selected"</c:if>>id</option> 
                                <option value="cateName" <c:if test="${requestScope.sort eq 'cateName'}">selected="selected"</c:if>>name</option>
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
                                    <th scope="col">CategoryID</th>
                                    <th scope="col">CategoryName</th>
                                    <th scope="col">IMG</th>
                                    <th scope="col">Status</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="c" items="${categories}">
                                <tr>
                                    <th scope="row">${c.cateId}</td>
                                    <td>${c.cateName}</td>
                                    <td><img class="two" src="${c.image}" height="150px" width="200px"></td>
                                    <td>
                                        <c:if test="${c.status eq true}">Enable</c:if>
                                        <c:if test="${c.status eq false}">Disable</c:if>
                                        </td> 
                                        <td style="display: flex; flex-direction: row;">
                                            <a href="${pageContext.request.contextPath}/Category/detail?cid=${c.cateId}"> <input type="button" value="Update"> </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div style="text-align: right;"><a href="${pageContext.request.contextPath}/Category/detail"> <input type="button" value="Add new"> </a>
                </div>
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
