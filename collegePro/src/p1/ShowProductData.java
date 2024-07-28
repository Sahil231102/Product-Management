package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowProductData")
public class ShowProductData extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int c=1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("select * from myproduct");
			  out.println("<!DOCTYPE html>");
	          out.println("<html>");
	          out.println("<head>");
	          out.println("<title>E-commerce</title>");
	          out.println("<link href=\"https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@400;700&display=swap\" rel=\"stylesheet\">");

	          
	          out.println("</head>");
	          out.println("<body>");
	          out.println("<style>");
	          out.println("body { background-color: Coral; font-family: 'Josefin Sans'; }");
	          out.println("h1 { color: white; text-align: center; }");
	          out.println(".styled-table { background: linear-gradient(black,#020024 ) padding-box,linear-gradient(145deg, transparent 35%, #e81cff, #40c9ff) border-box;border: 5px solid transparent;padding: 32px 24px;font-size: 19px;font-family: inherit;color: white;display: flex;position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);flex-direction: column;gap: 20px;box-sizing: border-box;border-radius: 16px;background-size: 200% 100%;animation: gradient 5s ease infinite; }");
	         		         
	          out.println(".styled-table th, .styled-table td { padding: 30px; border:1px solid black; text-align: left; }");
	          out.println(".styled-table button { background-color: linear-gradient(white, #212121); /* Green */ border: none; color: white; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; transition-duration: 0.4s; cursor: pointer; border-radius: 8px;text-decoration:none }");
	          out.println("</style>");
	          out.println("<h1>Product Details</h1>");
	          out.println("<table  class=\"styled-table\">"); // Apply CSS class to table
	          
	         
	      out.print("<tr><th>No</th><th>Product Id</th><th>Product Number</th><th>Product Name</th><th>Product Quantity</th></tr>");
	      while(rs.next())
	      {
	        out.print("<tr>");
	        out.print("<td>"+c+"</td>");
	        out.print("<td>"+rs.getString(1)+"</td>");
	        out.print("<td>"+rs.getString(2)+"</td>");
	        out.print("<td>"+rs.getString(3)+"</td>");
	        out.print("<td>"+rs.getString(4)+"</td>");
	        out.print("</tr>");
	        c++;
	      }
	      //out.print("<tr><td colspan=5 style='align-item:center'><a href='homepages.html'><input type='submit'value='Go to Home'></input></a></td></tr>");
	      out.print("</table>");
	      out.print("</body>");
	      out.print("</html>");
	      
	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
