package A01394332.ca.bcit.comp3601.lab10.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public Employee(final String id,
                    final String firstName,
                    final String lastName)
    {
        this(id, firstName, lastName, DEFAULT_DATE_OF_BIRTH);
    }

    private static void validateString(final String str)
    {
        if(str == null || str.isBlank())
        {
            throw new IllegalArgumentException("String is null or empty");
        }
    }

    private static void validateId(final String id)
    {
        if(id == null || id.isBlank() || id.length() != ID_VALID_LENGTH)
        {
            throw new IllegalArgumentException("Invalid Employee ID");
        }
    }
}
