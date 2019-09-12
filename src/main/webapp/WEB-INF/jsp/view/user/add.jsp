<%-- @elvariable id="users" type="ru.solon4ak.jm_crudapp.model.User" --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add user</title>
    </head>
    <body>
        <h2>New user</h2>
        <form action="add" method="post">
            <table border="0" width="300" cellpadding="5">
                <tbody>
                    <tr>
                        <td>First name</td>
                        <td><input type="text" name="firstName" /></td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td><input type="text" name="lastName" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" /></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="address" /></td>
                    </tr>
                    <tr>
                        <td>Phone number</td>
                        <td><input type="text" name="phoneNumber" /></td>
                    </tr>
                    <tr>
                        <td>Age</td>
                        <td><input type="text" name="age" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Submit" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
        <br />
        <br />
        <a href="<c:url value="list" />">List users</a>
    </body>
</html>
