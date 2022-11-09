<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : FeedbackList
    Created on : Oct 16, 2022, 5:19:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.2/js/bootstrap.min.js" integrity="sha512-5BqtYqlWfJemW5+v+TZUs22uigI8tXeVah5S/1Z6qBLVO7gakAOtkOzUtgq6dsIo5c0NJdmGPs0H9I+2OHUHVQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script><!-- comment -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.2/css/bootstrap.min.css" integrity="sha512-CpIKUSyh9QX2+zSdfGP+eWLx23C8Dj9/XmHjZY2uDtfkdLGo0uY12jgcnkX9vXOgYajEKb/jiw67EYm+kBf+6g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <jsp:include page="HeaderCustom_1.jsp"></jsp:include>
    <body>
        <h2>Feedback List</h2>
        
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <form method="get" action="FeedbackList">
                            <div class="row">
                                <div class="col-md-3">
                                    <label >Status</label>
                                    <select  class="form-select" name="status">
                                        <option value="">All</option>
                                        <option value="1">Active</option>
                                        <option value="0">Inactive</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label >Product</label>
                                    <select   class="form-select" name="pid">
                                        <option value="">All</option>
                                        <c:forEach var="p" items="${pl}">
                                            <option value="${p.pid}">${p.pname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label >Rating</label>
                                    <select  class="form-select" name="rating">
                                        <option value="">All</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label > </label>
                                    <button type="submit" class="btn btn-primary" style="display: block;" >Search</button>
                                </div>
                            </div>  
                        </form>
                    </div>
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Full Name</th>
                                    <th>Email</th>
                                    <th>Mobile</th>
                                    <th>Product Name</th>
                                    <th>Rated</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                    <th>Action</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="f" items="${fl}">
                                    <tr>
                                        <td><a href="FeedbackDetail?fid=${f.reviewid}"> ${f.reviewid}</a></td>
                                        <td>${f.user.fullName}</td>
                                        <td>${f.user.email}</td>
                                        <td>${f.user.phone}</td>
                                        <td>${f.product.pname}</td>
                                        <td>${f.user_rating}</td>
                                        <td>${f.status?"Active":"Inactive"}</td>
                                        <c:if test="${f.status}">
                                            <td><a href="ChangeFeedbackStatus?fid=${f.reviewid}&status=0">Switch Status</a></td>
                                        </c:if>
                                        <c:if test="${!f.status}">
                                            <td><a href="ChangeFeedbackStatus?fid=${f.reviewid}&status=1">Switch Status</a></td>
                                        </c:if>
                                        <td><a href="EditFeedback?fid=${f.reviewid}">Edit</a></td>
                                        <td><a href="DeleteFeedback?fid=${f.reviewid}"> Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link" href="./FeedbackList?status=${param.status}&pid=${param.pid}&rating=${param.rating}&pageindex=${param.pageindex-1}">Previous</a></li>
                                <li class="page-item"><a class="page-link" href="./FeedbackList?status=${param.status}&pid=${param.pid}&rating=${param.rating}&pageindex=${param.pageindex}">${pageindex}</a></li>
                                <li class="page-item"><a class="page-link" href="./FeedbackList?status=${param.status}&pid=${param.pid}&rating=${param.rating}&pageindex=${param.pageindex+1}">Next</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <jsp:include page="FooterCustom.jsp"></jsp:include>
</html>
