

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>UserList</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="../css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="../css/bootstrap.min.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="../css/templatemo-style.css">
        <link href="../css/toggle.css" rel="stylesheet">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="../js/pagger.js" type="text/javascript"></script>
        <link href="../css/pagger.css" rel="stylesheet" type="text/css"/>
    </head>
    <body id="reportsPage">
        <jsp:include page="header.jsp"/>
        <br><br><br><br>
        <form method="POST" action="userlist">
            <div class="container">
                <div class="row">
                    <div class="col-sm text-left align-middle">
                        <input type="button" value="Sort" class="btn btn-dark" disabled="disabled">
                        <select name="sort">
                            <option value="a.cid" <c:if test="${requestScope.sort eq 'a.cid'}">selected="selected"</c:if>>id </option> 
                            <option value="a.fullName" <c:if test="${requestScope.sort eq 'a.fullName'}">selected="selected"</c:if>>name</option>
                            <option value="a.email" <c:if test="${requestScope.sort eq 'a.email'}">selected="selected"</c:if>>email</option>
                            <option value="a.phone" <c:if test="${requestScope.sort eq 'a.phone'}">selected="selected"</c:if>>phone</option>
                            <option value="a.address" <c:if test="${requestScope.sort eq 'a.address'}">selected="selected"</c:if>>address</option>
                            <option value="a.status" <c:if test="${requestScope.sort eq 'a.status'}">selected="selected"</c:if>>status</option>
                            </select>
                        </div>
                        <div class="col-sm text-center align-middle">
                            <input type="button" value="Filter" class="btn btn-dark" disabled="disabled">
                            <select name="status">
                                <option value="-1" <c:if test="${requestScope.status eq '-1'}">selected="selected"</c:if>>---Status---</option>
                            <option value="0" <c:if test="${requestScope.status eq '0'}">selected="selected"</c:if>>Disable</option>
                            <option value="1" <c:if test="${requestScope.status eq '1'}">selected="selected"</c:if>>Enable</option>
                        </select>
                        <select name="role">
                            <option value="-1" <c:if test="${requestScope.role eq '-1'}">selected="selected"</c:if>>---Role---</option>
                            <c:forEach items="${requestScope.roles}" var="r">
                                <option value="${r.roleId}" <c:if test="${requestScope.role eq r.roleId}">selected="selected"</c:if>>${r.rname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm text-right align-middle">
                        <input type="text" name="search" <c:if test="${requestScope.search ne null}">value="${requestScope.search}"</c:if>>
                            <input type="submit" value="Search" class="btn btn-secondary">
                        </div>
                    </div>
                </div>
            </form>
            <br>
            <table class="table table-hover tm-table-small tm-product-table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">NAME</th>
                        <th scope="col">EMAIL</th>
                        <th scope="col">TITLE</th>
                        <th scope="col">PHONE</th>
                        <th scope="col">ADDRESS</th>
                        <th scope="col">ROLE</th>
                        <th scope="col">STATUS</th>
                        <th scope="col">DETAIL</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="u">  
                    <tr>                                        
                        <td>${u.cid}</td>
                        <td>${u.fullName}</td>                    
                        <td>${u.email}</td>
                        <td>${u.gender eq true?"Male":"Female"}</td>
                        <td>${u.phone}</td>
                        <td>${u.address}</td>
                        <td>${u.role.rname}</td>
                        <td>
                            <c:if test="${u.status eq false}">Disabled</c:if>
                            <c:if test="${u.status eq true}">Enabled</c:if>
                        </td>
                        <td>
                            <a href="" class="text-primary"> View  Detail</a>
                        </td>
                    </tr>
                </c:forEach>                  
            </tbody>
        </table>
        <div id="bot_pagger" class="pagger"></div>
        <script>
            render('bot_pagger',${requestScope.pageindex}, ${requestScope.totalpage}, 1);
        </script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
