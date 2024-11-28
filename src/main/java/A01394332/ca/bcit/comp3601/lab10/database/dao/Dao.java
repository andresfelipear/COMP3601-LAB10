package A01394332.ca.bcit.comp3601.lab10.database.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao
 *
 * @author user
 * @version 1.0
 */
public interface Dao<T>
{
    T get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void insert(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(int id) throws SQLException;

    void createTable() throws SQLException;
}
