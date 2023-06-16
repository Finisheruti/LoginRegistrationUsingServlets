

import jakarta.servlet.ServletConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewServ extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private Connection con;
	public void init(ServletConfig config) throws ServletException 
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String ss="jdbc:mysql://localhost:3306/mydb";
	    con=DriverManager.getConnection(ss,"root","manager");
		}
		catch(Exception e)
		{
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		pw.println("In View Servlet");
		pw.println("<br>");
		try
		{
		PreparedStatement pst=con.prepareStatement("select * from user");
		ResultSet rs=pst.executeQuery();
		pw.println("name"+"   "+"address"+"   "+"Email"+"   "+"Login Id"+"   "+"Password");
		pw.println("<br>");
		while (rs.next())
		{
			pw.println("<table>"+"<tr>");
			pw.println("<td>"+rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"</td>");
		   /* pw.println("<h3>"+rs.getString(2)+"</h3>");
		    pw.println("<h3>"+rs.getString(3)+"</h3>");
		    pw.println("<h3>"+rs.getString(4)+"</h3>");
		    pw.println("<h3>"+rs.getString(5)+"</h3>");*/
			pw.println("</tr"+"</table");
		    pw.println("<br>");
		}
		}
		catch(Exception e)
		{
			
		}
		
		
		
	}

}
