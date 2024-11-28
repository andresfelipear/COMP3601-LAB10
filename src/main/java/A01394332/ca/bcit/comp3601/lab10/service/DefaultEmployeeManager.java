package A01394332.ca.bcit.comp3601.lab10.service;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;
import A01394332.ca.bcit.comp3601.lab10.database.Database;
import A01394332.ca.bcit.comp3601.lab10.database.DbConstants;
import A01394332.ca.bcit.comp3601.lab10.database.dao.EmployeeDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
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

    private final Database      db;
    private final EmployeeDao   employeeDao;

    public DefaultEmployeeManager(final String dbUrl, final String dbUser, final String dbPassword) throws IOException, SQLException
    {
        readAndLoadPropertiesFile();
        db = new Database(dbProps, dbUrl, dbUser, dbPassword);
        employeeDao = new EmployeeDao(db);
    }

    private void readAndLoadPropertiesFile() throws IOException
    {
        File dbPropertiesFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
        if(!dbPropertiesFile.exists())
        {
            System.out.println("Properties file does not exist");
            throw new RuntimeException("Properties file does not exist");
        }

        dbProps = new Properties();
        dbProps.load(new FileInputStream(dbPropertiesFile));
    }

    @Override
    public List<Employee> getEmployees() throws SQLException
    {
        return employeeDao.getAll();
    }
}
