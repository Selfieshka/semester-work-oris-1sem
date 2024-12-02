<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Профиль</title>
</head>
<body>
    <h1>${sessionScope.get("email")}</h1>
</body>
</html>
