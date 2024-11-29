<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div>
    <h2>Find An Employee By ID</h2>
    <form method="post">
        <div>
            <label for="idToFind">ID:</label>
            <input type="text" name="idToFind" id="idToFind" required>
        </div>
        <button type="submit">Search</button>
    </form>
    <c:if test="${not empty employee}">
        <p>Found <c:out value="${employee.firstName}"/> <c:out value="${employee.lastName}"/></p>
    </c:if>
    <c:if test="${not empty foundStatus}">
        <p><c:out value="${foundStatus}"/></p>
    </c:if>
</div>
