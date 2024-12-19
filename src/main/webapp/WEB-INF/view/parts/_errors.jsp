<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty requestScope.errors}">
    <div class="errors-box">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message()}</span>
            <br>
        </c:forEach>
    </div>
</c:if>
