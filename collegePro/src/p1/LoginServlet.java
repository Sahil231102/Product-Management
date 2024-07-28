package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc123");

            PreparedStatement psmt = con.prepareStatement("select * from adminsignup where email=? and password=?");
            psmt.setString(1, email);
            psmt.setString(2, password);

            ResultSet rs = psmt.executeQuery();

         
            if (rs.next()) {
            	  request.setAttribute("email", email);
                  request.setAttribute("password", password);
//                sendemail sm = new sendemail();	
//                String code = sm.getRendom();
//
//                Admin admin = new Admin(email, password, code);
//
//                boolean test = sm.sendEmail(admin);
//
//                if (email == null || email.isEmpty()) {
//                    System.out.println("Admin verification Email address is null or empty.");
//                }
//
//                if (test) {
//                    HttpSession session = request.getSession();
//                    session.setAttribute("authcode", admin);
//                    response.sendRedirect("verify.html"); // Corrected redirect handling
//                } else {
//                    out.println("<script>alert('Failed to send verification email. Please try again.')</script>");
//                    RequestDispatcher rd = request.getRequestDispatcher("/login.html");
//                    rd.include(request, response);
//                }
            	
            	RequestDispatcher rd = request.getRequestDispatcher("AdminVerifySevlet");
            	rd.forward(request, response);
            } else {
                out.println("<script>alert('Incorrect email or password. Please enter valid credentials.')</script>");
                RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                rd.include(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
