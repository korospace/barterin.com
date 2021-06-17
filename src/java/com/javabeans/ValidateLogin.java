package com.javabeans;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import model.UserModel;
import koneksi.KoneksiDB;

/**
 *
 * @author elkorospace
 */
@WebServlet(name = "ValidateLogin", urlPatterns = {"/ValidateLogin"})
public class ValidateLogin extends HttpServlet {
    
    KoneksiDB db = null;
    public ValidateLogin() {
        db = new KoneksiDB();
    }
    
    // .. cek uniq data    
    public String validasi(String select,String uniq) {
        ResultSet rs = null;
        String result="";
        try {
            String sql = "SELECT "+select +" FROM user WHERE "+select+"='"+uniq+"'";
            
            rs = db.ambilData(sql);
            
            while (rs.next()) {
                UserModel am = new UserModel();
                result = rs.getString(select);
            }
            db.diskonek(rs);
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan cari login user, pada :\n" + a);
        }
        return result;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String proses = request.getParameter("proses");
        
        if (proses.equals("login")) {
            String notelp = request.getParameter("notelp");
            String pass = request.getParameter("pass");
           
            if (notelp == null || notelp.equals("") || pass == null || pass.equals("")) {
                response.sendRedirect("login.jsp");
            } else {
                UserModel pm = new UserModel();
                List<UserModel> datalogin = new ArrayList<UserModel>();
                
                datalogin = pm.LoginUser(notelp, pass); //send data to UserModel
                
                if(datalogin.isEmpty()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('No.telepon atau Password salah!!!');");
                    out.println("</script>"); 
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.include(request, response);
                } else {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("id", datalogin.get(0).getId());
                    session.setAttribute("notelp", datalogin.get(0).getNotelp());
                    session.setAttribute("username", datalogin.get(0).getUsername());
                    session.setAttribute("hak_akses", datalogin.get(0).getHak_akses());
                    
                    if (datalogin.get(0).getHak_akses().equals("Admin")) {
                        response.sendRedirect("admin.jsp");
                    }
                    if (datalogin.get(0).getHak_akses().equals("Pelanggan")) {
                        response.sendRedirect("etalase.jsp");
                    }
                }
            }
        }
        if(proses.equals("daftar")){
            String setUsername = request.getParameter("setUsername");
            String setNotelp = request.getParameter("setNotelp");
            String setPass = request.getParameter("setPass");
            
            if (setUsername == null || setUsername.equals("") || setNotelp == null || setNotelp.equals("") || setPass == null || setPass.equals("")) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Maaf, data belum lengkap!!!');");
                out.println("</script>"); 
                RequestDispatcher rd = request.getRequestDispatcher("daftar.jsp");
                rd.include(request, response);
            } else {
                UserModel pm = new UserModel();
                String nameExist = validasi("username",setUsername);
                String telpExist = validasi("notelp",setNotelp);
                if(nameExist != ""){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('username sudah dipakai!!!');");
                    out.println("</script>"); 
                    RequestDispatcher rd = request.getRequestDispatcher("daftar.jsp");
                    rd.include(request, response);
                }else if(telpExist != ""){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('No.telepon sudah dipakai!!!');");
                    out.println("</script>"); 
                    RequestDispatcher rd = request.getRequestDispatcher("daftar.jsp");
                    rd.include(request, response);
                }else{
                    pm.DaftarLogin(setUsername, setNotelp, setPass); //send data to UserModel

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Daftar berhasil, Silahkan Login!!!');");
                    out.println("</script>"); 

                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.include(request, response); 
                }
                
            }
        }
    }
}
