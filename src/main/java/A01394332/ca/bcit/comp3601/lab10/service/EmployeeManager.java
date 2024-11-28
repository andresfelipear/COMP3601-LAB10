package A01394332.ca.bcit.comp3601.lab10.service;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;

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
}
