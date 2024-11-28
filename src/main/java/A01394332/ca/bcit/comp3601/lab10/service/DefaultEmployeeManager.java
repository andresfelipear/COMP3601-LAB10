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
 * @author user
 * @version 1.0
 */
public class DefaultEmployeeManager implements EmployeeManager
{
    private Properties dbProps;

    private final EmployeeDao employeeDao;

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

    @Override
    public List<Employee> getEmployees() throws SQLException
    {
        return employeeDao.getAll();
    }
}
