<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%ArrayList<Category> cal = (ArrayList<Category>) request.getAttribute("categoryList");%>
        <h1>Hello World!</h1>

        <form action="${pageContext.request.contextPath}/InsertProduct" method="POST">
            Product ID: <input type="text" name="id"><br/>
            Product Name: <input type="text" name="name"><br/>
            Quantity: <input type="number" name="quantity"><br/>
            Price: <input type="number" name="money"><br/>
            Image: <input type="text" name="Image"><br/>
            Description: <input type="text" name="description"><br/>
            Status: <input type="checkbox" name="status" value="true"> <br/>
            CateID: <select name ="cateID">
                <%for (int i = 0; i < cal.size(); i++) {%>          
                <option value="<%=cal.get(i).getCateId()%>"><%=cal.get(i).getCateId()%>-<%=cal.get(i).getCateName() %></option> 
                <%}%>
            </select>
            <br/>
            <input type="submit" name="save" value="save"> 
        </form>
    </body>
</html>
