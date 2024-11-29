package A01394332.ca.bcit.comp3601.assign2.database.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public interface Dao<T>
{
    /**
     * Retrieves an object of type {@code T} by its ID.
     *
     * @param id the unique identifier of the object to retrieve.
     * @return the object corresponding to the given ID, or {@code null} if no such object exists.
     * @throws SQLException if a database access error occurs.
     */
    T get(String id) throws SQLException;

    /**
     * Retrieves all objects of type {@code T} from the database.
     *
     * @return a {@link List} of all objects in the database.
     * @throws SQLException if a database access error occurs.
     */
    List<T> getAll() throws SQLException;

    /**
     * Inserts a new object of type {@code T} into the database.
     *
     * @param t the object to insert into the database.
     * @throws SQLException if a database access error occurs or the insertion fails.
     */
    void insert(T t) throws SQLException;

    /**
     * Updates an existing object of type {@code T} in the database.
     *
     * @param t the object with updated values to save in the database.
     * @throws SQLException if a database access error occurs or the update fails.
     */
    void update(T t) throws SQLException;

    /**
     * Deletes an object of type {@code T} from the database by its ID.
     *
     * @param id the unique identifier of the object to delete.
     * @throws SQLException if a database access error occurs or the deletion fails.
     */
    void delete(String id) throws SQLException;

    /**
     * Drops the table associated with objects of type {@code T} from the database.
     *
     * @throws SQLException if a database access error occurs or the operation fails.
     */
    void dropTable() throws SQLException;

    /**
     * Creates the table associated with objects of type {@code T} in the database.
     *
     * @throws SQLException if a database access error occurs or the operation fails.
     */
    void createTable() throws SQLException;
}
