

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	public void init(ServletConfig config) throws ServletException 
	{
		try
		  {
			System.out.println("in init");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String ss="jdbc:mysql://localhost:3306/mydb";
		    con=DriverManager.getConnection(ss,"root","manager");
		    
		
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		pw.println("In Login Servlet");
		String id=request.getParameter("LoginName");
		String pwd=request.getParameter("password");
		
		pw.println(id+" "+pwd);
		try
		{
		PreparedStatement pst=con.prepareStatement("select * from user where loginId=? and password=?");
		pst.setString(1,id);
		pst.setString(2,pwd);
		ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
			pw.println("found");
			RequestDispatcher rd=request.getRequestDispatcher("Success.html");
			rd.forward(request, response);
		}
		else
		{
			pw.println("not found");
			RequestDispatcher rd=request.getRequestDispatcher("Fail.html");
			rd.forward(request, response);
		}
			
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
