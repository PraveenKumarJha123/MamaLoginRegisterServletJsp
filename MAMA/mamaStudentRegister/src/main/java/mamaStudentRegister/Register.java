package mamaStudentRegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Redirect to the form page
//        RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
//        rd.forward(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String myname = req.getParameter("name1");
        String myemail = req.getParameter("email1");
        String mypass = req.getParameter("pass1");
        String mygender = req.getParameter("gender1");
        String mycity = req.getParameter("city1");
         System.out.println(myname);
         System.out.println(myemail);
         System.out.println(mycity);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mamaStudent", "root", "admin");
            PreparedStatement ps = con.prepareStatement("insert INTO register (name, email, password, gender, city) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, myname);
            ps.setString(2, myemail);
            ps.setString(3, mypass);
            ps.setString(4, mygender);
            ps.setString(5, mycity);
            int executeUpdate = ps.executeUpdate();

            if (executeUpdate > 0) {
                resp.setContentType("text/html");
                out.print("<h3 style='color:green'>User registered successfully</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                rd.include(req, resp);
            } else {
                resp.setContentType("text/html");
                out.print("<h3 style='color:red'>User not registered due to some error</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                rd.include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            out.print("<h3 style='color:red'>Exception occurred: " + e.getMessage() + "</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
            rd.include(req, resp);
        }
    }
}
