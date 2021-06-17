package com.javabeans;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        HttpSession sesi = request.getSession();
        sesi.invalidate();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Berhasil Logout!!!');");
        out.println("</script>"); 
        response.sendRedirect("login.jsp");
    }
}
