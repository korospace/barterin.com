package com.javabeans;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import model.TambahModel;
import model.UserModel;

/**
 *
 * @author elkorospace
 */
@WebServlet(name = "TambahData", urlPatterns = {"/TambahData"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class TambahData extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String proses = request.getParameter("proses");
        
        if (proses.equals("tambah")) {
            
            HttpSession session = request.getSession(true);
            String id = session.getAttribute("id").toString();
            String notelp = session.getAttribute("notelp").toString();
            String username = session.getAttribute("username").toString();
            
            String nama  = request.getParameter("namaproduk");
            String harga = request.getParameter("hargaproduk");
            String kategori  = request.getParameter("kategori");
            String deskripsi = request.getParameter("deskripsi");
            
            InputStream inputfoto = null;
            
            Part filePart = request.getPart("imgproduk");
            if (filePart != null) {
                inputfoto = filePart.getInputStream();
            }
            System.out.println(inputfoto);
            
            if (nama == null || nama.equals("") || harga == null || harga.equals("") || kategori == null || kategori.equals("") || deskripsi == null || deskripsi.equals("") || inputfoto == null || inputfoto.equals("")) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Data belum Lengkap!!!');");
                out.println("</script>"); 
                RequestDispatcher rd = request.getRequestDispatcher("etalase.jsp");
                rd.include(request, response);
            } else {
                TambahModel pm = new TambahModel();
                
                pm.TambahData(nama, harga, kategori, deskripsi, id, inputfoto, notelp, username); //send data to TambahModel
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Produk berhasil Ditambah!!!');");
                out.println("</script>"); 
                
                RequestDispatcher rd = request.getRequestDispatcher("etalase.jsp");
                rd.include(request, response);
                
            }
        }
    }
}
