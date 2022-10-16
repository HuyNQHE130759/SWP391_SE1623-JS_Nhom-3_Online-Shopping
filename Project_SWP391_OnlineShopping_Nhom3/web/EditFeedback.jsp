<%-- 
    Document   : EditFeedback
    Created on : Oct 16, 2022, 5:22:39 PM
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
        <h1>Feedback Detail</h1>
        <div class="card">
            <div class="card-body">
                <form method="" action="">
                    <div class="row">
                        <div class="col-md-6">
                            <label>Contact full name</label>
                            <input type="text" readonly="" class="form-control" name="id" value="${f.user.fullName}">
                            <input   class="form-control" name="cid" type="hidden" value="${f.cid}">
                            <input  class="form-control" name="pid" type="hidden" value="${f.pid}">
                            <input   class="form-control" name="fid" type="hidden" value="${f.reviewid}">
                        </div>
                        <div class="col-md-6">
                            <label>Email</label>
                            <input type="text"readonly  class="form-control" name="email" value="${f.user.email}">
                        </div>
                        <div class="col-md-6">
                            <label>Phone</label>
                            <input type="text" readonly class="form-control" name="phone" value="${f.user.phone}">
                        </div>
                        <div class="col-md-6">
                            <label>Product Name</label>
                            <input type="text" readonly class="form-control" name="pname" value="${f.product.pname}">
                        </div>

                        <div class="col-md-6">
                            <label >Rating</label>
                            <select  class="form-select" name="rating" >
                                <option value="1" ${f.user_rating==1?"selected":""}>1</option>
                                <option value="2"${f.user_rating==2?"selected":""}>2</option>
                                <option value="3" ${f.user_rating==3?"selected":""}>3</option>
                                <option value="4" ${f.user_rating==4?"selected":""}>4</option>
                                <option value="5"${f.user_rating==5?"selected":""}>5</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Comment</label>
                            <input type="text"  class="form-control" name="comment" value="${f.user_comment}">
                        </div>
                        <div class="col-md-6">
                            <label>Status</label>
                            <select  class="form-select" name="status">
                                <option value="1" ${f.status?"selected":""}>Active</option>
                                <option value="0" ${!f.status?"selected":""}>Inactive</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Image</label>
                            <input type="text"  class="form-control" name="img" value="${f.img}">
                            <img width="50%" src="${f.img}" alt="anh feedback">
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit">Update</button>
                </form>
            </div>
        </div>
    </body>
</html>
