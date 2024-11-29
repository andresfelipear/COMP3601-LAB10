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
</div>
