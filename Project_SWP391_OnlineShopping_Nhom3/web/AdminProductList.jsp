<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Product List</title>
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
                <form method="POST" action="AdminProduct/list">
                    <div class="row" style="margin: 16px 0;">
                        <div style="display: flex; flex-direction: row; align-items: center">
                            <div style="margin-right: 8px">Sort</div>
                            <select class="form-select" name="sort">
                                <option value="a.pid" <c:if test="${requestScope.sort eq 'a.pid'}">selected="selected"</c:if>>id</option> 
                                <option value="a.pname" <c:if test="${requestScope.sort eq 'a.pname'}">selected="selected"</c:if>>name</option>
                                <option value="a.price" <c:if test="${requestScope.sort eq 'a.price'}">selected="selected"</c:if>>price</option>
                                </select>
                            </div>

                            <div style="display: flex; flex-direction: row; align-items: center; margin-left: 10%;">
                                <div style="margin-right: 8px">Filter</div>
                                <select class="form-select" name="selectStatus">
                                    <option value="" <c:if test="${requestScope.status eq ''}">selected="selected"</c:if> >---Status---</option>
                                    <option value="1"<c:if test="${requestScope.status eq 1}">selected="selected"</c:if>>Enable</option>
                                    <option value="0"<c:if test="${requestScope.status eq 0}">selected="selected"</c:if>>Disable</option>
                                </select>
                                <select class="form-select" name="category">
                                    <option value="" selected>---Category---</option>
                                <c:forEach items="${requestScope.categories}" var="c">
                                    <option value="${c.cateId}" <c:if test="${requestScope.category eq c.cateId}">selected="selected"</c:if>>${c.cateName}</option>
                                </c:forEach>
                            </select>
                            <select class="form-select" name="provider">
                                <option value="" selected>---Provider---</option>
                                <c:forEach items="${requestScope.providers}" var="pr">
                                    <option value="${pr.provider_id}" <c:if test="${requestScope.provider eq pr.provider_id}">selected="selected"</c:if>>${pr.provider_name}</option>
                                </c:forEach>
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
                                    <th scope="col">pid</th>
                                    <th scope="col">ProductName</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">IMG</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Category</th>
                                    <th scope="col">Status</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="p" items="${products}">
                                <tr>
                                    <th scope="row">${p.pid}</td>
                                    <td>${p.pname}</td>
                                    <td>${p.quantity}</td> 
                                    <td>${p.price}</td>
                                    <td><img class="two" src="${p.image}" height="150px" width="200px"></td>
                                    <td>${p.description}</td> 
                                    <td>${p.category.cateName}</td>
                                    <td>
                                        <c:if test="${p.status eq true}">Enable</c:if>
                                        <c:if test="${p.status eq false}">Disable</c:if>
                                        </td> 
                                        <td style="display: flex; flex-direction: row;">
                                            <a href="${pageContext.request.contextPath}/AdminProduct/detail?pid=${p.pid}"> <input type="button" value="Update"> </a>
                                            <a href="${pageContext.request.contextPath}/RemoveProduct?pid=${p.pid}"> <input type="button" value="Delete"> </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="bot_pagger" class="pagger"></div>
                <a href="${pageContext.request.contextPath}/AdminProduct/detail"> <input style="display: flex; flex-direction: row;float: right;" type="button" value="Add new"> </a>
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
