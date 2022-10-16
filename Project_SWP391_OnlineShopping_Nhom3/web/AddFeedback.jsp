<%-- 
    Document   : AddFeedback
    Created on : Oct 16, 2022, 5:17:54 PM
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
    <body>
        <h1>Add Feedback </h1>
        <div class="card">
            <div class="card-body">
                <form method="post" action="AddFeedback">
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
                                <option value="1" ${f.status==1?"selected":""}>1</option>
                                <option value="2"${f.status==2?"selected":""}>2</option>
                                <option value="3" ${f.status==3?"selected":""}>3</option>
                                <option value="4" ${f.status==4?"selected":""}>4</option>
                                <option value="5"${f.status==5?"selected":""}>5</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Comment</label>
                            <input readonly class="form-control" name="comment" value="${f.user_comment}">
                        </div>
                        <div class="col-md-6">
                            <label>Status</label>
                            <input readonly class="form-control" name="status" value="${f.user_rating}">
                        </div>
                        <div class="col-md-6">
                            <label>Image</label>
                            <input readonly class="form-control" name="img" value="${f.img}">
                            <img src="${f.img}" alt="anh feedback">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
