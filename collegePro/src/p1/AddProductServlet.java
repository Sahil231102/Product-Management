package p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> value = request.getParameterNames();
		
		try {
			System.out.print("Enter");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			PreparedStatement psmt = con.prepareStatement("insert into myproduct values(?,?,?,?)");
			System.out.print("Enter1");
			while (value.hasMoreElements()) {
				String name = value.nextElement();
				String namevalue = request.getParameter(name);
				System.out.print("Enter2");
				switch (name) {
				case "ProductNumber":
				int pnumber= Integer.parseInt(namevalue);
					psmt.setInt(1, pnumber);
					break;
				case "ProductName":
					psmt.setString(2, namevalue);
					break;
				case "Pprice":
					int pprice= Integer.parseInt(namevalue);
					psmt.setInt(3, pprice);
					break;
				case "Pquantity":
					int pq =Integer.parseInt(namevalue);
					psmt.setInt(4, pq);
					break;
				default:
					break;
				}
				
			}
			
			int rowsInserted = psmt.executeUpdate();
			if(rowsInserted>0)
			{
                System.out.print("Add Product successful!");
			}
			else
			{
           	     System.out.print("Add Product Not failed!");

			}
			response.sendRedirect("homepages.html");
			con.commit();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
