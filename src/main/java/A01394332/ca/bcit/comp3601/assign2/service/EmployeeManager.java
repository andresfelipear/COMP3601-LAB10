package A01394332.ca.bcit.comp3601.assign2.service;

import A01394332.ca.bcit.comp3601.assign2.data.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeManager
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public interface EmployeeManager
{
    /**
     * Retrieves a list of all employees.
     * Implementations of this method should fetch all employee records from
     * the underlying data source, such as a database, and return them as a
     * {@link List} of {@link Employee} objects.
     *
     * @return a {@link List} of {@link Employee} objects representing all employees.
     * @throws SQLException if a database access error occurs.
     */
    List<Employee> getEmployees() throws SQLException;

    /**
     * Adds a new employee to the system.
     * <p>
     * Implementations of this method should insert the provided {@link Employee}
     * object into the underlying data source.
     * </p>
     *
     * @param employee the {@link Employee} object to add.
     * @throws SQLException if a database access error occurs.
     */
    void addEmployee(Employee employee) throws SQLException;

    /**
     * Deletes an employee by their unique ID.
     * <p>
     * Implementations of this method should remove the employee record associated
     * with the provided ID from the underlying data source.
     * </p>
     *
     * @param employeeId the unique ID of the employee to delete.
     * @throws SQLException if a database access error occurs.
     */
    void deleteEmployee(String employeeId) throws SQLException;

    /**
     * Retrieves a specific employee by their unique ID.
     * <p>
     * Implementations of this method should fetch the employee record associated
     * with the provided ID from the underlying data source.
     * </p>
     *
     * @param employeeId the unique ID of the employee to retrieve.
     * @return the {@link Employee} object representing the employee with the specified ID,
     *         or {@code null} if no such employee exists.
     * @throws SQLException if a database access error occurs.
     */
    Employee getEmployee(String employeeId) throws SQLException;
}
