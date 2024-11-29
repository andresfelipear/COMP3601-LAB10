<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div>
    <h2>Add Employees</h2>
    <form method="post">
        <div>
            <label for="id">ID:</label>
            <input type="text" name="id" id="id" required>
        </div>
        <div>
            <label for="firstName">First Name:</label>
            <input type="text" name="firstName" id="firstName" required>
        </div>
        <div>
            <label for="lastName">Last Name:</label>
            <input type="text" name="lastName" id="lastName" required>
        </div>
        <div>
            <label for="dob">Date of Birth:</label>
            <input type="date" name="dob" id="dob">
        </div>
        <button type="submit">Add Employees</button>
    </form>
    <c:if test="${not empty message}">
        <p><c:out value="${message}"/></p>
    </c:if>
</div>
