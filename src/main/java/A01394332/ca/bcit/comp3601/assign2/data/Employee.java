package A01394332.ca.bcit.comp3601.assign2.data;

import java.time.LocalDate;

/**
 * Employee
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Employee
{
    private static final int ID_VALID_LENGTH;
    private static final LocalDate DEFAULT_DATE_OF_BIRTH;

    private final String        id;
    private final String        firstName;
    private final String        lastName;
    private final LocalDate     dateOfBirth;

    static
    {
        ID_VALID_LENGTH       = 9;
        DEFAULT_DATE_OF_BIRTH = null;
    }

    /**
     * Constructs an Employee with the specified ID, first name, last name, and date of birth.
     * Ensures that the provided ID, first name, and last name are valid.
     *
     * @param id          the unique employee ID, must be exactly 9 characters long.
     * @param firstName   the employee's first name, cannot be null or blank.
     * @param lastName    the employee's last name, cannot be null or blank.
     * @param dateOfBirth the employee's date of birth; can be null if not provided.
     * @throws IllegalArgumentException if the ID, first name, or last name is invalid.
     */
    public Employee(final String id,
                    final String firstName,
                    final String lastName,
                    final LocalDate dateOfBirth)
    {
        validateId(id);
        validateString(firstName);
        validateString(lastName);
        this.id          = id;
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Constructs an Employee with the specified ID, first name, and last name.
     * The date of birth is set to the default value (null).
     *
     * @param id        the unique employee ID, must be exactly 9 characters long.
     * @param firstName the employee's first name, cannot be null or blank.
     * @param lastName  the employee's last name, cannot be null or blank.
     * @throws IllegalArgumentException if the ID, first name, or last name is invalid.
     */
    public Employee(final String id,
                    final String firstName,
                    final String lastName)
    {
        this(id, firstName, lastName, DEFAULT_DATE_OF_BIRTH);
    }

    /**
     * Validates that the given string is neither null nor blank.
     *
     * @param str the string to validate.
     * @throws IllegalArgumentException if the string is null or blank.
     */
    private static void validateString(final String str)
    {
        if(str == null || str.isBlank())
        {
            throw new IllegalArgumentException("Result Code: 901 Description:invalid employee data!");
        }
    }

    /**
     * Validates that the given employee ID is non-null, non-blank, and exactly 9 characters long.
     *
     * @param id the employee ID to validate.
     * @throws IllegalArgumentException if the ID is null, blank, or not 9 characters long.
     */
    private static void validateId(final String id)
    {
        String regex = "^A0[0-9]{7}$";
        if(id == null || !id.matches(regex))
        {
            throw new IllegalArgumentException("Result Code: 901 Description:invalid employee data!");
        }
    }

    /**
     * Retrieves the employee's unique ID.
     *
     * @return the employee's ID as a string.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Retrieves the employee's first name.
     *
     * @return the employee's first name as a string.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Retrieves the employee's last name.
     *
     * @return the employee's last name as a string.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Retrieves the employee's date of birth.
     *
     * @return the employee's date of birth as a {@link LocalDate}, or null if not provided.
     */
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    @Override
    public String toString()
    {
        return "Employee [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", dateOfBirth=" + dateOfBirth + "]";
    }
}
