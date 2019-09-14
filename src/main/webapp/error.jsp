<%@page  isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Exception</title>
    </head>
    <body>
        <h2>Exception occurred while processing the request</h2>
        <p>Error: ${pageContext.exception}</p>
        <p>URI: ${pageContext.errorData.requestURI}</p>
        <p>Status code:${pageContext.errorData.statusCode}</p>
        
        <p><a href="<c:url value="/" />">Index</a></p>
    </body>
</html>



