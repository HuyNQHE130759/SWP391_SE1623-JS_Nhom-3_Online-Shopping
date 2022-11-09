<%-- 
    Document   : FeedbackDetail
    Created on : Oct 16, 2022, 5:08:52 PM
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
        <h2>Feedback Detail</h2>
        <a href="EditFeedback?fid=${f.reviewid}" class="btn btn-primary">Edit</a>
        <div class="card">
            <div class="card-body">
                <form method="" action="">
                    <div class="row">
                        <div class="col-md-6">
                            <label>Contact full name</label>
                            <input readonly class="form-control" name="id" value="${f.user.fullName}">
                            <input readonly class="form-control" name="cid" type="hidden" value="${f.cid}">
                            <input readonly class="form-control" name="pid" type="hidden" value="${f.pid}">
                        </div>
                        <div class="col-md-6">
                            <label>Email</label>
                            <input readonly class="form-control" name="email" value="${f.user.email}">
                        </div>
                        <div class="col-md-6">
                            <label>Phone</label>
                            <input readonly class="form-control" name="phone" value="${f.user.phone}">
                        </div>
                        <div class="col-md-6">
                            <label>Product Name</label>
                            <input readonly class="form-control" name="pname" value="${f.product.pname}">
                        </div>

                        <div class="col-md-6">
                            <label >Rating</label>
                            <select  class="form-select" name="rating" >
                                <option value="1" ${f.user_rating==1?"selected":"disabled"}>1</option>
                                <option value="2"${f.user_rating==2?"selected":"disabled"}>2</option>
                                <option value="3" ${f.user_rating==3?"selected":"disabled"}>3</option>
                                <option value="4" ${f.user_rating==4?"selected":"disabled"}>4</option>
                                <option value="5"${f.user_rating==5?"selected":"disabled"}>5</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Comment</label>
                            <input readonly class="form-control" name="comment" value="${f.user_comment}">
                        </div>
                        <div class="col-md-6">
                            <label>Status</label>
                            <label>Status</label>
                            <select  class="form-select" name="status">
                                <option value="1" ${f.status?"selected":"disabled"}>Active</option>
                                <option value="0" ${!f.status?"selected":"disabled"}>Inactive</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Image</label>
                            <!--<input readonly class="form-control" name="img" value="${f.img}">-->
                            <img width="50%" src="${f.img}" alt="anh feedback">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="FooterCustom.jsp"></jsp:include>
</html>
