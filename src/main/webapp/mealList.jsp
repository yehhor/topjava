<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="css/mealList.css" rel="stylesheet" type="text/css">
</head>
<body>

entered
<table>
    <tr>
        <th>id</th>
        <th>date</th>
        <th>description</th>
        <th>calories</th>
    </tr>
    <c:forEach items="${mealList}" var="item">
        <tr class="<c:if test="${item.exceed}"> exceeded </c:if>
               <c:if test="${!item.exceed}"> normal </c:if> ">
            <td class="id"><c:out value="${item.id}"/></td>
            <td class="date"><c:out value="${item.formatDateTime}"/></td>
            <td class="description"><c:out value="${item.description}"/></td>
            <td class="cals"><c:out value="${item.calories}"/></td>
        </tr>
    </c:forEach>
</table>
<div class="add">
    <form action="crud" method="POST">

        <span> add meal </span>
        <table>
            <tr>
                <th>Header</th>
                <th>Input</th>
            </tr>
            <tr>
                <td>Date</td>
                <td><input required type="datetime-local" name="date"/></td>
            </tr>
            <tr>
                <td>Calories</td>
                <td><input required type="text" name="calories"/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input required class="description" type="text" name="description"/></td>
            </tr>
        </table>
        <input style="width: 100px" type="submit" value="add MEAL"/>
        <input type="hidden" value="add" name="action"/>
    </form>

</div>

<div class="delete">
    <form action="crud" method="POST">
        <span> delete meal </span>
        <br>
        user id: <input required type="text" name="uid"/> <br>
        <input type="hidden" value="delete" name="action"/>
        <input type="submit" value="delete"/>
    </form>
</div>

<div class="timeFilter">
    <form action="crud" method="POST">
        <span> enter time period</span>
        <input type="hidden" value="filter" name="action">
        <table>
            <tr>
                <th>from</th>
                <th>due</th>
            </tr>
            <tr>
                <td><input type="time" name="timeFrom" ></td>
                <td><input type="time" name="timeDue" ></td>
            </tr>
            <tr>
                <td>Calories per Day</td>
                <td><input type="text" name="caloriesPerDay"></td>
            </tr>
        </table>
        <input type="submit" value="filter">
    </form>
</div>
</body>
</html>
