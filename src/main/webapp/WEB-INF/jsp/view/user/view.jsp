<%-- @elvariable id="user" type="ru.solon4ak.jm_crudapp.model.User" --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View user</title>
    </head>
    <body>
        <h2>View user</h2>
        <a href="<c:url value="edit">
               <c:param name="id" value="${user.id}" />
           </c:url>">Edit</a> / 
        <a href="<c:url value="list" />">List all</a>
        <br />
        <br />
        <table border="0" width="300" cellpadding="5">
            <tbody>
                <tr>
                    <td>First name</td>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td>${user.lastName}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>${user.address}</td>
                </tr>
                <tr>
                    <td>Phone number</td>
                    <td>${user.phoneNumber}</td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td>${user.age}</td>
                </tr>
            </tbody>
        </table>  
        <br />
        <form action="delete" method="post">
            <input type="hidden" name="id" value="${user.id}" />
            <input type="submit" value="delete" />
        </form>
    </body>
</html>
