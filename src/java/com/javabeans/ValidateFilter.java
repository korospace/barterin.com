package com.javabeans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import model.UpdateModel;

/**
 *
 * @author elkorospace
 */
@WebServlet(name = "ValidateFilter", urlPatterns = {"/ValidateFilter"})
public class ValidateFilter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String keyword = request.getParameter("keyword");

        HttpSession session = request.getSession(true);

        session.setAttribute("keyword", keyword);
        session.removeAttribute("kategori");
        session.removeAttribute("halaman");

        RequestDispatcher rd = request.getRequestDispatcher("");
        rd.include(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        String proses = request.getParameter("proses");
        if (!proses.equals("pagination")) {

            session.setAttribute("kategori", proses);
            session.removeAttribute("keyword");
            session.removeAttribute("halaman");
        }else{
            session.setAttribute("halaman", request.getParameter("page"));
        }

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.include(request, response);
    }
    
}
