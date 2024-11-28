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
 * @author user
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

    @Override
    public void init(final ServletConfig config) throws ServletException
    {
        super.init(config);
        url      = getServletConfig().getInitParameter("url");
        username = getServletConfig().getInitParameter("username");
        password = getServletConfig().getInitParameter("password");

        createConnection();
    }


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


