<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add New User</title>
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
                <form method="post" action="AddUser" style="margin: auto; width: 50%">
                    <div class="form-group">
                        <label for="userName">Username<span style="color: red">*</span></label>
                        <input type="text" class="form-control" name="userName" placeholder="Enter username" required="true" value="${username != "" ? username : ""}">
                    </div>
                    <div class="form-group">
                        <label for="password">Password<span style="color: red">*</span></label>
                        <input type="password" class="form-control" name="password" placeholder="Password" required="true">
                    </div>
                    <div class="form-group">
                        <label for="repeatPassword">Repeat Password<span style="color: red">*</span></label>
                        <input type="password" class="form-control" name="repeatPassword" placeholder="Repeat Password" required="true">
                    </div>
                    <div class="form-group">
                        <label for="fullName">Full name</label>
                        <input type="text" class="form-control" name="fullName" placeholder="Enter full name" value="${fullName != "" ? fullName : ""}">
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select class="form-control" name="gender">
                            <option value="1" ${isMale == true ? 'selected' : ''}>Male</option>
                            <option value="0" ${isMale == false ? 'selected' : ''}>Female</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="email">Email<span style="color: red">*</span></label>
                        <input type="email" class="form-control" name="email" placeholder="Enter email" required="true" value="${email != "" ? email : ""}">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="tel" class="form-control" name="phone" placeholder="Enter phone" value="${phone != "" ? phone : ""}">
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" class="form-control" name="address" placeholder="Enter address" value="${address != "" ? address : ""}">
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select class="form-control" name="role">
                            <option value="User" ${rolename == "User" ? 'selected' : ''}>User</option>
                            <option value="Admin" ${rolename == "Admin" ? 'selected' : ''}>Admin</option>
                            <option value="Provider" ${rolename == "Provider" ? 'selected' : ''}>Provider</option>
                            <option value="Manager" ${rolename == "Manager" ? 'selected' : ''}>Manager</option>
                        </select>
                    </div>
                    <c:if test="${not empty msg}">
                        <ul>
                            ${msg}
                        </ul>
                    </c:if>
                    
                    <button type="submit" class="btn btn-primary" style="margin: 8px 0">Save</button>
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
