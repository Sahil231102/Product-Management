package p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveServlet
 */
@WebServlet("/RemoveServlet")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> deleteValues = request.getParameterNames();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			
			PreparedStatement psmt = con.prepareStatement("delete from myproduct where PRODUCTNUMBER=?");
			
			while (deleteValues.hasMoreElements()) {
				String deleteValue = (String) deleteValues.nextElement();
				String pdelete = request.getParameter(deleteValue);
				
				switch (deleteValue) {
				case "ProductNumber":
					int pnumber = Integer.parseInt(pdelete);
					psmt.setInt(1, pnumber);
					break;

				default:
					break;
				}
				
			}
			
			int rowsUpadatesd = psmt.executeUpdate();
			
			
			if(rowsUpadatesd>0)
			{
				System.out.print("Delete Successfully!!");
			}
			else
			{
				System.out.print("Not Delete Successfully!!");
			}
			
			response.sendRedirect("homepages.html");
			con.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
