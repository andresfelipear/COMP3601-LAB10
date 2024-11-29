package A01394332.ca.bcit.comp3601.assign2.data.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Util
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Util
{
    /**
     * Reads the content of a SQL file from the classpath.
     * This method reads a SQL file line by line and returns its content as a single String.
     * The file must be located in the classpath.
     * @param fileName the name of the SQL file to be read.
     * @return the content of the SQL file as a String.
     * @throws RuntimeException if the file is not found or an error occurs during reading.
     */
    public static String readSQLFile(final String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try(InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(fileName))
        {
            if(inputStream == null)
            {
                throw new FileNotFoundException(fileName);
            }
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
            {
                String line;
                while((line = reader.readLine()) != null)
                {
                    sb.append(line).append("\n");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error reading SQL file");
        }
        return sb.toString();
    }
}
