package p1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminVerifyCode")
public class AdminVerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter())
		{
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("authcode");
			String code = request.getParameter("authcode");
			
			if(code.equals(admin.getCode()))
			{
				response.sendRedirect("homepages.html");
				out.print("Verification Successfully");
			}
			else
			{
				out.print("Incorrect Verication code");
			}
		}
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
