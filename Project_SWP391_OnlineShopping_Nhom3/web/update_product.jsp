<%-- 
    Document   : update_customer.jsp
    Created on : Nov 19, 2020, 2:09:46 AM
    Author     : BVLT
--%>

<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
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

        <form action="${pageContext.request.contextPath}/UpdateProduct" method="POST">
            Product Name: <input type="text" name="name" value="<%= ((Product) request.getAttribute("product")).getPname()%>"><br/>
            
            Quantity: <input type="number" name="quantity" value="<%= ((Product) request.getAttribute("product")).getQuantity()%>"><br/>
            Price: <input type="number" name="money" value="<%= (int) ((Product)request.getAttribute("product")).getPrice()%>">><br/>
            Image: <input type="text" name="Image" value="<%= ((Product) request.getAttribute("product")).getImage()%>"><br/>
            Description: <input type="text" name="description" value="<%= ((Product) request.getAttribute("product")).getDescription()%>"><br/>
            Status: <input type="checkbox" name="status" <%if (((Product) request.getAttribute("product")).isStatus()== true) {%> checked="checked" <%}%> > <br/>
            CateID: <select name ="cateID">                
                <%for (int i = 0; i < cal.size(); i++) {%>          
                <option value="<%=cal.get(i).getCateId()%>"><%=cal.get(i).getCateId()%>-<%=cal.get(i).getCateName() %></option> 
                <%}%>
            </select>
            <br/>
            <input type="hidden" name="ppid" value="<%=request.getParameter("pid")%>">
            <input type="submit" name="save" value="save"> 
        </form>
    </body>
</html>
