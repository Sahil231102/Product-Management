package p1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminVerifySevlet")
public class AdminVerifySevlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminVerifySevlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
        	  String email = (String) req.getAttribute("email");
              String password = (String) req.getAttribute("password");
            
              

              
            
            sendemail sm = new sendemail();
            String code = sm.getRendom();

            Admin admin = new Admin(email, password, code);

            boolean test = sm.sendEmail(admin);

            if (test) {
                HttpSession session = req.getSession();
                session.setAttribute("authcode", admin);
                resp.sendRedirect("verifycode.html");
            } else {
                out.println("<script>alert('Failed to send verification email. Please try again.')</script>");
                RequestDispatcher rd = req.getRequestDispatcher("/login.html");
                rd.include(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
