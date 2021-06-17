package com.javabeans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import model.TambahModel;

/**
 *
 * @author elkorospace
 */
@WebServlet(name = "ValidateDelete", urlPatterns = {"/ValidateDelete"})
public class ValidateDelete extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");
        String idbarang = request.getParameter("idbarang");
        
        TambahModel pm = new TambahModel();
        
        HttpSession session = request.getSession(true);
        
        if(session.getAttribute("notelp").toString() != null){   
            if(session.getAttribute("id").toString() != id){            
                pm.DeleteData(id,idbarang); //send data to UserModel
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Berhasil di Hapus!!!');");
                out.println("</script>"); 
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('GAGAL, jangan hapus barang orang lain !!!');");
                out.println("</script>");
            }
        }else{
            out.println("<script type=\"text/javascript\">");
            out.println("alert('GAGAL, login dulu !!!');");
            out.println("</script>");
        }
                
        RequestDispatcher rd = request.getRequestDispatcher("etalase.jsp");
        rd.include(request, response);
        
    }
}
