package A01394332.ca.bcit.comp3601.lab10.database.dao;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;
import A01394332.ca.bcit.comp3601.lab10.data.util.Util;
import A01394332.ca.bcit.comp3601.lab10.database.Database;
import A01394332.ca.bcit.comp3601.lab10.database.DbConstants;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * EmployeeDao
 *
 * @author Andres Arevalo
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

    /**
     * Constructs an {@code EmployeeDao} with the specified {@link Database} connection.
     *
     * @param db the {@link Database} instance used to interact with the employee database table.
     */
    public EmployeeDao(final Database db)
    {
        this.db = db;
    }

    /**
     * Retrieves an {@link Employee} by its unique ID.
     *
     * @param id the unique identifier of the employee to retrieve.
     * @return the {@link Employee} object if found; {@code null} otherwise.
     */
    @Override
    public Employee get(final String id) throws SQLException
    {
        ResultSet rs = null;
        try(Connection connection = db.getConnection();
        Statement statement = connection.createStatement())
        {
            String query = String.format("SELECT * FROM %s WHERE ID='%s'", TABLE_NAME, id);
            rs = statement.executeQuery(query);
        }
        catch(SQLException e)
        {
            System.out.println("There was an error retrieving the employee with id: " + id);
            throw e;
        }

        ArrayList<Employee> employees = getEmployeesFromResultSet(rs);
        if(employees.isEmpty())
        {
            return null;
        }
        return employees.get(0);
    }

    /**
     * Retrieves all {@link Employee} records from the database.
     *
     * @return a {@link List} of all {@link Employee} objects in the database.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Employee> getAll() throws SQLException
    {
        ArrayList<Employee> employees;
        ResultSet           resultSet;

        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement())
        {
            String query = String.format("SELECT * FROM %s",
                                         TABLE_NAME);
            resultSet = statement.executeQuery(query);
            employees = getEmployeesFromResultSet(resultSet);
        }
        catch(SQLException e)
        {
            System.out.println("error getting employees");
            throw e;
        }

        return employees;
    }

    /**
     * Lists the names of all tables in the current database.
     *
     * <p>
     * This method queries the database's information schema to retrieve all table names.
     * </p>
     *
     * @throws SQLException if a database access error occurs.
     */
    public void listAllTablesNames() throws SQLException
    {
        ResultSet resultSet;
        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement())
        {
            String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Parses a {@link ResultSet} to create a list of {@link Employee} objects.
     *
     * @param resultSet the {@link ResultSet} containing employee data.
     * @return an {@link ArrayList} of {@link Employee} objects.
     * @throws SQLException if a database access error occurs while reading the {@link ResultSet}.
     */
    private ArrayList<Employee> getEmployeesFromResultSet(final ResultSet resultSet) throws SQLException
    {
        final ArrayList<Employee> employees = new ArrayList<>();

        while(resultSet.next())
        {
            final Employee employee;
            final String   id        = resultSet.getString("ID");
            final String   firstName = resultSet.getString("firstName");
            final String   lastName  = resultSet.getString("lastName");
            final String   dobString = resultSet.getString("dob");

            if(dobString != null)
            {
                final LocalDate dob = LocalDate.parse(resultSet.getString("dob"));
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

    /**
     * Inserts a new {@link Employee} into the database.
     *
     * @param employee the {@link Employee} object to insert.
     * @throws SQLException if a database access error occurs or the insertion fails.
     */
    @Override
    public void insert(final Employee employee) throws SQLException
    {
        try(Connection connection = db.getConnection();
        Statement statement = connection.createStatement())
        {
            String sql = String.format("INSERT INTO %s VALUES('%s', '%s', '%s', '%s)",
                                       TABLE_NAME,
                                       employee.getId(),
                                       employee.getFirstName(),
                                       employee.getLastName(),
                                       employee.getDateOfBirth());
        }
    }

    /**
     * Updates an existing {@link Employee} record in the database.
     *
     * @param employee the {@link Employee} object with updated values.
     * @throws SQLException if a database access error occurs or the update fails.
     */
    @Override
    public void update(final Employee employee) throws SQLException
    {

    }

    /**
     * Deletes an {@link Employee} record from the database by its ID.
     *
     * @param id the unique identifier of the employee to delete.
     * @throws SQLException if a database access error occurs or the deletion fails.
     */
    @Override
    public void delete(final int id) throws SQLException
    {
        try(Connection connection = db.getConnection();
        Statement statement = connection.createStatement())
        {
            String query = String.format("DELETE FROM %s WHERE id = '%s'", TABLE_NAME, id);
            statement.execute(query);
        }
        catch(SQLException e)
        {
            System.out.println("error deleting employee with id: " + id);
            throw e;
        }
        System.out.println("employee deleted.");
    }

    /**
     * Drops the employee table from the database.
     *
     * @throws SQLException if a database access error occurs or the operation fails.
     */
    @Override
    public void dropTable() throws SQLException
    {
        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement())
        {
            String query = String.format("DROP TABLE %s", TABLE_NAME);
            statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("error dropping table");
            throw e;
        }

        System.out.println("Table " + TABLE_NAME + "dropped correctly");
    }

    /**
     * Creates the employee table in the database using a pre-defined SQL script.
     * The SQL script is read from the file specified by {@link DbConstants#EMPLOYEE_CREATE_TABLE_SCRIPT_NAME}.
     *
     * @throws SQLException if a database access error occurs or the operation fails.
     */
    @Override
    public void createTable() throws SQLException
    {
        System.out.println("creating table");
        String ucIdentifier = "uc_" + DbConstants.TABLE_ROOT + "EmployeeID";
        String creatTableSQLQuery = Util.readSQLFile(DbConstants.EMPLOYEE_CREATE_TABLE_SCRIPT_NAME);
        creatTableSQLQuery = creatTableSQLQuery.replace("Employees",
                                                        TABLE_NAME);
        creatTableSQLQuery = creatTableSQLQuery.replace("uc_EmployeeID", ucIdentifier);
        executeSQLScript(creatTableSQLQuery);
    }

    /**
     * Inserts all employee records into the database using a pre-defined SQL script.
     * The SQL script is read from the file specified by {@link DbConstants#EMPLOYEE_INSERT_ALL_SCRIPT_NAME}.
     *
     * @throws SQLException if a database access error occurs or the operation fails.
     */
    public void insertAll() throws SQLException
    {
        System.out.println("inserting employees");
        String insertAllSQLQuery = Util.readSQLFile(DbConstants.EMPLOYEE_INSERT_ALL_SCRIPT_NAME);
        insertAllSQLQuery = insertAllSQLQuery.replaceAll("Employees", TABLE_NAME);
        executeSQLScript(insertAllSQLQuery);
    }

    /**
     * Executes an SQL script containing multiple statements, separated by "GO".
     *
     * @param script the SQL script to execute.
     * @throws SQLException if a database access error occurs or the script execution fails.
     */
    private void executeSQLScript(final String script) throws SQLException
    {
        String[] sqlBatches = script.split("GO");

        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement())
        {
            for(String sql : sqlBatches)
            {
                sql = sql.trim();
                if(!sql.isEmpty())
                {
                    statement.execute(sql);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("error performing sql operation in the employees table: " + e.getMessage());
            throw new SQLException(e);
        }
    }
}
