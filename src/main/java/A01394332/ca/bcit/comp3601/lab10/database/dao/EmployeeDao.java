package A01394332.ca.bcit.comp3601.lab10.database.dao;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;
import A01394332.ca.bcit.comp3601.lab10.data.util.Util;
import A01394332.ca.bcit.comp3601.lab10.database.Database;
import A01394332.ca.bcit.comp3601.lab10.database.DbConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    @Override
    public void createTable() throws SQLException
    {
        System.out.println("creating table");
        String ucIdentifier = "uc_" + DbConstants.TABLE_ROOT + "EmployeeID";
        String creatTableSQLQuery = readSQLFile(DbConstants.EMPLOYEE_CREATE_TABLE_SCRIPT_NAME);
        creatTableSQLQuery = creatTableSQLQuery.replace("Employees",
                                                        TABLE_NAME);
        creatTableSQLQuery = creatTableSQLQuery.replace("uc_EmployeeID", ucIdentifier);
        executeSQLScript(creatTableSQLQuery);
    }

    public void insertAll() throws SQLException
    {
        System.out.println("inserting employees");
        String insertAllSQLQuery = readSQLFile(DbConstants.EMPLOYEE_INSERT_ALL_SCRIPT_NAME);
        insertAllSQLQuery = insertAllSQLQuery.replaceAll("Employees", TABLE_NAME);
        executeSQLScript(insertAllSQLQuery);
    }

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

    private String readSQLFile(final String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName))
        {
            if(inputStream == null)
            {
                throw new FileNotFoundException(fileName);
            }
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
            {
                String line;
                while((line = reader.readLine()) != null)
                {
                    sb.append(line).append("\n");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error reading SQL file");
        }
        return sb.toString();
    }
}
