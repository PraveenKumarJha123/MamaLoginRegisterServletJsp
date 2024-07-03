package mamaStudentRegister;

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

@WebServlet("/StudentLogin")
public class StLogin  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		 String email12 = req.getParameter("Email");
		String pass12 = req.getParameter("pass1");
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mamaStudent", "root", "admin");
	            PreparedStatement ps= con.prepareStatement("select * from register where email=? and password=?");
	            ps.setString(1, email12);
	            ps.setString(2, pass12);
	            ResultSet rs= ps.executeQuery();
	            
	            if(rs.next()) {
	            	//dynamic name so we use HttpSession
	            	HttpSession session = req.getSession() ;
	            	session.setAttribute("session_name", rs.getString("name"));// name is column name
	            	
	            	out.print("<h3 style='color:green'>login sucessfully</h3>");
	            	RequestDispatcher rd= req.getRequestDispatcher("welcome.jsp");
	            	rd.include(req, resp);
	            }else {
	         
	                 out.print("<h3 style='color:red'>Email and Password didnot match</h3>");
	                 RequestDispatcher rd = req.getRequestDispatcher("/registerLogin.jsp");
	                 rd.include(req, resp);
	            }
			
		}catch(Exception e) {
			e.printStackTrace();
			
            out.print("<h3 style='color:red'>"+e.getMessage()+"</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/registerLogin.jsp");
            rd.include(req, resp);
       
		}
		
	}

}
