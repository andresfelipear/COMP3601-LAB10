package A01394332.ca.bcit.comp3601.lab10.database.dao;

import A01394332.ca.bcit.comp3601.lab10.database.Database;

import java.sql.SQLException;

/**
 * Dao
 *
 * @author user
 * @version 1.0
 */
public abstract class Dao
{
    protected final Database db;
    protected final String   tableName;

    protected Dao(final Database db,
               final String tableName)
    {
        this.db = db;
        this.tableName = tableName;
    }

    public abstract void create() throws SQLException;




}