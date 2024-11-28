package A01394332.ca.bcit.comp3601.lab10.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Database
{
    public final String dbUrl;
    public final String dbUser;
    public final String dbPassword;

    private static Connection   connection;
    private final Properties    properties;

    public Database(final Properties properties,
                    final String url,
                    final String user,
                    final String password)
    {
        this.properties = properties;
        dbUrl = url;
        dbUser = user;
        dbPassword = password;
    }

    public Connection getConnection() throws SQLException
    {
        if(connection != null)
        {
            return connection;
        }
        try
        {
            connect();
        }
        catch(ClassNotFoundException e)
        {
            throw new SQLException(e.getMessage());
        }

        return connection;
    }

    private void connect() throws SQLException, ClassNotFoundException
    {
        Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
        System.out.println("Driver Loaded");

        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        System.out.println("Database Connection Established");
    }

    public void shutdown()
    {
        if(connection != null)
        {
            try
            {
                connection.close();
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
