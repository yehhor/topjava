<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal Editing</title>
    <link href="css/mealList.css" rel="stylesheet" type="text/css">
</head>
<body>
<form style="margin-top: 100px;" action="crud" method="post">
    <table>
        <tr>
            <td>Date</td>
            <td><input type="datetime-local" name="date"></td>
        </tr>
        <tr>
            <td>calories</td>
            <td><input type="text" name="calories"></td>
        </tr>
        <tr>
            <td>description</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td><input type="submit" value="update" name="action"></td>
            <td><input type="submit" value="cancel" name="action"></td>
            <td><input type="hidden" value="${param.id}" name="mealId"></td>
        </tr>
    </table>
</form>
</body>
</html>
