package A01394332.ca.bcit.comp3601.lab10.service;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeManager
 *
 * @author user
 * @version 1.0
 */
public interface EmployeeManager
{
    List<Employee> getEmployees() throws SQLException;
}
