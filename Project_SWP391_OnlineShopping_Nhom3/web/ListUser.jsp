<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>List User</title>
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
                <form method="get" action="ListUser">
                    <div class="row" style="margin: 16px 0;">
                        <div style="display: flex; flex-direction: row; align-items: center">
                            <div style="margin-right: 8px">Sort</div>
                            <select class="form-select" name="selectSort">
                                <option value="cid" selected>Id</option>
                                <option value="fullName">Full Name</option>
                            </select>
                        </div>

                        <div style="display: flex; flex-direction: row; align-items: center; margin-left: 20%;">
                            <div style="margin-right: 8px">Filter</div>
                            <select class="form-select" name="selectStatus">
                                <option value="" selected >---Status---</option>
                                <option value="1">Enable</option>
                                <option value="0">Disable</option>
                            </select>
                            <select class="form-select" name="selectRole">
                                <option value="" selected>---Role---</option>
                                <option value="User">User</option>
                                <option value="Admin">Admin</option>
                                <option value="Provider">Provider</option>
                                <option value="Manager">Manager</option>
                            </select>
                        </div>
                        
                        <button style="margin-left: 10%;" type="submit" class="btn btn-primary">Search</button>

                    </div>
                </form>
                <div class="row">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Username</th>
                                <th scope="col">Gender</th>
                                <th scope="col">Address</th>
                                <th scope="col">Email</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Role</th>
                                <th scope="col">Status</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${listUser}">
                                <tr>
                                    <th scope="row">${i.cid}</td>
                                    <td>${i.fullName}</td>
                                    <td>${i.username}</td> 
                                    <td>${i.genderr}</td>
                                    <td>${i.address}</td>
                                    <td>${i.email}</td> 
                                    <td>${i.phone}</td>
                                    <td>${i.role}</td>
                                    <td>${i.stringStatus}</td> 
                                    <td style="display: flex; flex-direction: row;">
                                        <a style="margin-right: 5px" href="/EditUser?cid=${i.cid}">Edit</a>
                                        <a href="#">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <a style="color: white;" class="btn btn-primary" href="/AddUser">Add new</a>
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
