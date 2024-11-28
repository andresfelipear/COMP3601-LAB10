package A01394332.ca.bcit.comp3601.lab10.database;

import java.sql.*;
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

    private static Connection connection;
    private final Properties properties;

    /**
     * Initializes a new {@code Database} object with the provided configuration.
     *
     * @param properties a {@link Properties} object containing database-related properties,
     *                   such as the driver class name.
     * @param url        the URL of the database.
     * @param user       the username to connect to the database.
     * @param password   the password to connect to the database.
     */
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

    /**
     * Returns an active {@link Connection} to the database.
     * If an existing connection is available and open, it is returned.
     * Otherwise, a new connection is established.
     *
     * @return a {@link Connection} object to interact with the database.
     * @throws SQLException if a database access error occurs.
     */
    public Connection getConnection() throws SQLException
    {
        if(connection != null && !connection.isClosed())
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

    /**
     * Establishes a new connection to the database using the configured properties.
     * The method loads the database driver and establishes a secure connection
     * with encryption and certificate trust enabled.
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private void connect() throws SQLException, ClassNotFoundException
    {
        Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
        System.out.println("Driver Loaded");

        String modifiedUrl = dbUrl + ";encrypt=true;trustServerCertificate=true";

        connection = DriverManager.getConnection(modifiedUrl,
                                                 dbUser,
                                                 dbPassword);
        System.out.println("Database Connection Established");
    }

    /**
     * Closes the active database connection.
     * This method ensures that the database connection is properly closed
     * to free up resources.
     */
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

    /**
     * Checks if a given table exists in the database.
     *
     * @param tableName The name of the table to check.
     * @return true if the table exists in the database; false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public static boolean tableExists(String tableName) throws SQLException
    {
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        String           rsTableName;
        try(ResultSet resultSet = databaseMetaData.getTables(connection.getCatalog(),
                                                             "%",
                                                             "%",
                                                             null))
        {
            while(resultSet.next())
            {
                rsTableName = resultSet.getString("TABLE_NAME");
                if(rsTableName.equalsIgnoreCase(tableName))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
