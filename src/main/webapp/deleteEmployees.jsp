<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div>
    <h2>Remove an Employee</h2>
    <form method="post">
        <div>
            <label for="idToDelete">ID:</label>
            <input type="text" name="idToDelete" id="idToDelete" required>
        </div>
        <button type="submit">Delete</button>
    </form>
    <c:if test="${not empty deleteStatus}">
        <p><c:out value="${deleteStatus}"/></p>
    </c:if>
</div>
