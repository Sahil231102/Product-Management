package p1;
import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Enumeration<String> parameterNames = request.getParameterNames();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc123");

            // Assuming your table 'signup' has four columns: name, email, username, password
            PreparedStatement psmt = con.prepareStatement("insert into signup values(?,?,?,?)");

            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                // Assuming the order of parameters is: name, email, username, password
                // Set parameters in the PreparedStatement accordingly
                switch (paramName) {
                    case "fname":
                        psmt.setString(1, paramValue);
                        break;
                    case "lname":
                        psmt.setString(2, paramValue);
                        break;
                    case "PhoneNumber":
                        psmt.setString(3, paramValue);
                        break;
                    case "password":
                        psmt.setString(4, paramValue);
                        break;
                    default:
                        // Handle additional parameters if needed
                        break;
                }
            }

            int rowsInserted = psmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.print("Registration successful!");
            } else {
            	 System.out.print("Registration failed!");
            }
            
            response.sendRedirect("login.html");

            con.close();
        } catch (Exception e) {
            out.print(e);
        }
    }


}
