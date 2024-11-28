package A01394332.ca.bcit.comp3601.lab10.servlet;

import A01394332.ca.bcit.comp3601.lab10.data.Employee;
import A01394332.ca.bcit.comp3601.lab10.service.DefaultEmployeeManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeServlet
 *
 * @author Andres Arevalo
 * @version 1.0
 */
@WebServlet(
        urlPatterns = "",
        initParams = {
                @WebInitParam(name = "url", value = "jdbc:sqlserver://java-sql.ad.bcit.ca:1433;databaseName=jspweb"),
                @WebInitParam(name = "username", value = "javastudent"),
                @WebInitParam(name = "password", value = "compjava")
        }
)
public class EmployeeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private String url;
    private String username;
    private String password;
    private DefaultEmployeeManager employeeManager;

    /**
     * Initializes the servlet and sets up the database connection parameters.
     * This method reads the database connection parameters (URL, username,
     * and password) from the servlet's initialization parameters and
     * establishes a connection by creating an instance of {@link DefaultEmployeeManager}.
     *
     * @param config The servlet configuration object containing initialization parameters.
     * @throws ServletException if an error occurs during initialization.
     */
    @Override
    public void init(final ServletConfig config) throws ServletException
    {
        super.init(config);
        url      = getServletConfig().getInitParameter("url");
        username = getServletConfig().getInitParameter("username");
        password = getServletConfig().getInitParameter("password");

        createConnection();
    }


    /**
     * Handles HTTP GET requests to retrieve employee data.
     * This method fetches a list of employees using the `DefaultEmployeeManager`
     * and sets it as a request attribute. The request is then forwarded to
     * the `index.jsp` view for rendering. If an error occurs during data
     * retrieval, an error message is set as a request attribute.
     *
     * @param req  the `HttpServletRequest` object that contains the request
     *             the client made to the servlet.
     * @param resp the `HttpServletResponse` object that contains the response
     *             the servlet sends to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs while handling the request.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            if(employeeManager != null)
            {
                List<Employee> employees = employeeManager.getEmployees();
                req.setAttribute("employees", employees);
            }
            else
            {
                throw new ServletException("Employee Manager is null!");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            req.setAttribute("error", "Error getting the list of employees");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Establishes a connection to the database using the `DefaultEmployeeManager`.
     * This method creates a new instance of the `DefaultEmployeeManager` with the
     * database connection parameters provided during servlet initialization.
     * If an error occurs while establishing the connection, the exception
     * message is printed to the console.
     */
    private void createConnection()
    {
        System.out.println("Connecting to the database...");
        try
        {
            employeeManager =  new DefaultEmployeeManager(url, username, password);
        }
        catch(IOException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


