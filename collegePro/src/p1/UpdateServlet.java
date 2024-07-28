package p1;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Enumeration<String> pvalue = request.getParameterNames();
		System.out.print("update Enter ");
		try {
			System.out.print("try inside");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection  con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			PreparedStatement psmt = con.prepareStatement("update product set PNAME=?,PPRICE=?, PQUANTITY=? where PNUMBER=?");
			
			while (pvalue.hasMoreElements()) {
				System.out.print("While Inside");
				String updatevalue = pvalue.nextElement();
				String pupdatevalue = request.getParameter(updatevalue);
				
				switch (updatevalue)
				{
				case "uProductNumber":
				int pnumber= Integer.parseInt(pupdatevalue);
					psmt.setInt(4, pnumber);
					break;
				case "uProductName":
					psmt.setString(1, pupdatevalue);
					break;
				case "uprice":
					int pprice= Integer.parseInt(pupdatevalue);
					psmt.setInt(2, pprice);
					break;
				case "uquantity":
					int pq =Integer.parseInt(pupdatevalue);
					psmt.setInt(3, pq);
					break;
				default:
					break;
				}
				
			}
			int rowsUpadared = psmt.executeUpdate();
			if(rowsUpadared>0)
			{
                System.out.print("Update Product successful!");
			}
			else
			{
           	     System.out.print("Update Product Not failed!");
			}
			response.sendRedirect("homepages.html");
			con.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
