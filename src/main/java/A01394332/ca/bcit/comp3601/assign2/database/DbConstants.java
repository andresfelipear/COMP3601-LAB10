package A01394332.ca.bcit.comp3601.assign2.database;

/**
 * DbConstants
 *
 * @author user
 * @version 1.0
 */
public interface DbConstants
{
    String DB_PROPERTIES_FILENAME            = "db.properties";
    String DB_DRIVER_KEY                     = "db.driver";
    String TABLE_ROOT                        = "A01394332_";
    String CUSTOMER_TABLE_NAME               = TABLE_ROOT + "Customer";
    String EMPLOYEE_TABLE_NAME               = TABLE_ROOT + "Employees";
    String EMPLOYEE_CREATE_TABLE_SCRIPT_NAME = "Employees_CreateTable.sql";
    String EMPLOYEE_INSERT_ALL_SCRIPT_NAME   = "Employees_Insert.sql";
}
