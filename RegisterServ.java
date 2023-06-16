

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



public class RegisterServ extends HttpServlet {
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("in dopost");
		response.setContentType("text/html");
		String nname=request.getParameter("name");
		String nadd=request.getParameter("add");
		String nemail=request.getParameter("email");
		String nLoginId=request.getParameter("id");
		String npassword=request.getParameter("pwd");
		PrintWriter pw=response.getWriter();
		try
		{
		PreparedStatement pst=con.prepareStatement("insert into user values(?,?,?,?,?)");
		pst.setString(1, nname);
		pst.setString(2, nadd);
		pst.setString(3, nemail);
		pst.setString(4, nLoginId);
		pst.setString(5, npassword);
		int k=pst.executeUpdate();
		if(k>0)
			pw.println("updated");
		else
			pw.println("not updated");
			
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
