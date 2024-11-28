package A01394332.ca.bcit.comp3601.lab10.database.dao;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;
import A01394332.ca.bcit.comp3601.lab10.database.Database;
import A01394332.ca.bcit.comp3601.lab10.database.DbConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDao
 *
 * @author user
 * @version 1.0
 */
public class EmployeeDao implements Dao<Employee>
{
    public static final String TABLE_NAME;
    private final Database db;

    static
    {
        TABLE_NAME = DbConstants.EMPLOYEE_TABLE_NAME;
    }

    public EmployeeDao(final Database db)
    {
        this.db = db;
    }

    @Override
    public Employee get(final int id)
    {
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException
    {
        ArrayList<Employee> employees;
        Connection          connection;
        Statement           statement;
        ResultSet           resultSet;

        try
        {
            connection = db.getConnection();
            statement = connection.createStatement();
            String query = String.format("SELECT * FROM %s", TABLE_NAME);
            resultSet = statement.executeQuery(query);
            employees = getEmployeesFromResultSet(resultSet);
        }
        finally
        {
            db.shutdown();
        }

        return employees;
    }

    private ArrayList<Employee> getEmployeesFromResultSet(final ResultSet resultSet) throws SQLException
    {
        final ArrayList<Employee> employees = new ArrayList<>();

        while(resultSet.next())
        {
            final Employee employee;
            final String id          = resultSet.getString("ID");
            final String firstName   = resultSet.getString("firstName");
            final String lastName    = resultSet.getString("lastName");
            final String dobString   = resultSet.getString("dob");

            if(dobString != null)
            {
                final LocalDate dob      = LocalDate.parse(resultSet.getString("dob"));
                employee = new Employee(id, firstName, lastName, dob);
            }
            else
            {
                employee = new Employee(id, firstName, lastName);
            }

            employees.add(employee);
        }

        return employees;
    }

    @Override
    public void insert(final Employee employee) throws SQLException
    {

    }

    @Override
    public void update(final Employee employee) throws SQLException
    {

    }

    @Override
    public void delete(final int id) throws SQLException
    {

    }

    @Override
    public void createTable() throws SQLException
    {

    }

}
