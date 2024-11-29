<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div>
    <h2>Employees List</h2>
    <div id="table">
        <table>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>DOB</th>
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td><c:out value="${employee.id}"/> </td>
                    <td><c:out value="${employee.firstName}"/> </td>
                    <td><c:out value="${employee.lastName}"/> </td>
                    <td><c:out value="${employee.dateOfBirth}"/> </td>
                </tr>
            </c:forEach>

            <c:if test="${empty employees}">
                <p>No employees found.</p>
            </c:if>

            <c:if test="${not empty error}">
                <p><c:out value="${error}"/></p>
            </c:if>
        </table>
    </div>
</div>
