package com.javabeans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import model.UpdateModel;

/**
 *
 * @author elkorospace
 */
@WebServlet(name = "ValidateUpdate", urlPatterns = {"/ValidateUpdate"})
@MultipartConfig(maxFileSize = 16177215)
public class ValidateUpdate extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String proses = request.getParameter("proses");
        
        if (proses.equals("updateDilihat")){
            String uniqid = request.getParameter("uniqid");
            String value = request.getParameter("value");
            
            UpdateModel pm = new UpdateModel();
            
            pm.updateDilihat(uniqid,value);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String proses = request.getParameter("proses");
        
        if (proses.equals("update")) {
            
            String idbarang = request.getParameter("idbarang");
            String namaproduk = request.getParameter("namaproduk");
            String hargaproduk = request.getParameter("hargaproduk");
            String kategori = request.getParameter("kategori");
            String deskripsi = request.getParameter("deskripsi");
            InputStream inputfoto = null;
            
            Part filePart = request.getPart("imgproduk");
            if (filePart != null) {
                inputfoto = filePart.getInputStream();
            }
           
            if (namaproduk == null || namaproduk.equals("") || hargaproduk == null || hargaproduk.equals("") || kategori == null || kategori.equals("") || deskripsi == null || deskripsi.equals("")) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Data belum Lengkap!!!');");
                out.println("</script>"); 
                RequestDispatcher rd = request.getRequestDispatcher("etalase.jsp");
                rd.include(request, response);
            } else {
                UpdateModel pm = new UpdateModel();
                
                pm.UpdateData(idbarang, namaproduk, hargaproduk, kategori, deskripsi, inputfoto); //send data to TambahModel

                HttpSession session = request.getSession(true);
            
                if(session.getAttribute("hak_akses").equals("Admin")){
                    RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
                    rd.include(request, response); 
                }
                else if (session.getAttribute("hak_akses").equals("Pelanggan")) {
                    RequestDispatcher rd = request.getRequestDispatcher("etalase.jsp");
                    rd.include(request, response);
                }
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Data Sudah DiUpdate!!!');");
                out.println("</script>"); 
            }
        }
        if (proses.equals("updateProfile")){
            String iduser = request.getParameter("iduser");
            String username = request.getParameter("username");
            String pass = request.getParameter("password");
            String notelp = request.getParameter("notelp");
            
            InputStream inputfoto = null;
            
            Part filePart = request.getPart("imgprofile");
            if (filePart != null) {
                inputfoto = filePart.getInputStream();
            }
            
            if (username == null || username.equals("") || pass == null || pass.equals("") || notelp == null || notelp.equals("")) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Data belum Lengkap!!!');");
                out.println("</script>"); 
                RequestDispatcher rd = request.getRequestDispatcher("etalase.jsp");
                rd.include(request, response);
            } else {
                UpdateModel zz = new UpdateModel();
                
                zz.UpdateProfile(iduser,username, pass, notelp, inputfoto); //send data to TambahModel

                HttpSession session = request.getSession(true);
            
                if(session.getAttribute("hak_akses").equals("Admin")){
                    RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
                    rd.include(request, response); 
                }
                else if (session.getAttribute("hak_akses").equals("Pelanggan")) {
                    RequestDispatcher rd = request.getRequestDispatcher("etalase.jsp");
                    rd.include(request, response);
                }
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Profile Sudah DiUpdate!!!');");
                out.println("</script>"); 
            }
        }
    }
}
