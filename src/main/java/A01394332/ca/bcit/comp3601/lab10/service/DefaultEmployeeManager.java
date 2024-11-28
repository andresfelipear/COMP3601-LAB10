package A01394332.ca.bcit.comp3601.lab10.service;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;
import A01394332.ca.bcit.comp3601.lab10.database.Database;
import A01394332.ca.bcit.comp3601.lab10.database.DbConstants;
import A01394332.ca.bcit.comp3601.lab10.database.dao.EmployeeDao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * DefaultEmployeeManager
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class DefaultEmployeeManager implements EmployeeManager
{
    private Properties dbProps;

    private final EmployeeDao employeeDao;

    /**
     * Constructs a {@code DefaultEmployeeManager} with the provided database credentials.
     * This constructor initializes a {@link Database} object, checks if the employee table exists,
     * and creates and populates it if necessary. It also loads database properties from a file.
     *
     * @param dbUrl      the URL of the database.
     * @param dbUser     the username to connect to the database.
     * @param dbPassword the password to connect to the database.
     * @throws IOException  if an error occurs while reading the properties file.
     * @throws SQLException if a database access error occurs during initialization.
     */
    public DefaultEmployeeManager(final String dbUrl,
                                  final String dbUser,
                                  final String dbPassword) throws IOException, SQLException
    {
        readAndLoadPropertiesFile();
        Database db = new Database(dbProps, dbUrl, dbUser, dbPassword);

        db.getConnection();
        employeeDao = new EmployeeDao(db);

        if(!Database.tableExists(DbConstants.EMPLOYEE_TABLE_NAME))
        {
            employeeDao.createTable();
            employeeDao.insertAll();
        }
    }

    /**
     * Reads and loads the database properties file.
     * This method attempts to load the database configuration from a properties file
     * specified by {@link DbConstants#DB_PROPERTIES_FILENAME}. If the file is not found,
     * it throws a {@code RuntimeException}.
     *
     * @throws IOException if an error occurs while reading the properties file.
     * @throws RuntimeException if the properties file does not exist.
     */
    private void readAndLoadPropertiesFile() throws IOException
    {
        dbProps = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DbConstants.DB_PROPERTIES_FILENAME))
        {
            if(inputStream == null)
            {
                System.out.println("Properties file does not exist. " + DbConstants.DB_PROPERTIES_FILENAME);
                throw new RuntimeException("Properties file does not exist");
            }
            dbProps.load(inputStream);
        }
    }

    /**
     * Retrieves all employees from the database.
     * This method delegates to the {@link EmployeeDao#getAll()} method to fetch
     * a list of employees from the database.
     *
     * @return a {@link List} of {@link Employee} objects representing all employees in the database.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Employee> getEmployees() throws SQLException
    {
        return employeeDao.getAll();
    }
}
