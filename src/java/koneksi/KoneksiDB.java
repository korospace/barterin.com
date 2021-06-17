package koneksi;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author elkorospace
 */

public class KoneksiDB {
    private Connection conn;
    private Statement st;
    public static void ambilKoneksi(){
        try {
            String db = "jdbc:mysql://localhost/db_barterin";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(db, user, pass);
        } catch (Exception x) {
            System.out.println("Terjadi kesalahan ambil koneksi : " + x);
        }
    }
    public void koneksi() {
        try {
            String db = "jdbc:mysql://localhost/db_barterin";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(db, user, pass);
            st = conn.createStatement();
        } catch (Exception x) {
            
            System.out.println("Terjadi Kesalahan Koneksi : " + x);
        }
    }
    public void diskonek(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            st.close();
            conn.close();
        }catch (Exception x) {
            System.out.println("Terjadi Kesalahan diskonek : " + x);
        }
    }
    public ResultSet ambilData(String sql) {
        ResultSet rs = null;
        try {
            koneksi();
            rs = st.executeQuery(sql);
        } catch (Exception x) {
            System.out.println("Terjadi Kesalahan ambil data : " + x);
        }
        return rs;
    }
    
    public void SimpanData(String sql) {
        
        try {
            koneksi();
            st.executeUpdate(sql);
        } catch (Exception x) {
            System.out.println("Terjadi Kesalahan input data : " + x);
        }
    }
    
    public void SimpanDataFIx(String nama, String harga, String kategori, String deskripsi, String id, InputStream foto, String notelp, String username) {
        
        try {
            koneksi();
            String sql = "INSERT INTO produk VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            statement.setInt(2, 0);
            statement.setString(3, nama);
            statement.setString(4, harga);
            statement.setString(5, kategori);
            statement.setString(6, deskripsi);
            statement.setString(7, "0");
            statement.setBlob(8, foto);
            statement.setString(9, notelp);
            statement.setString(10, username);
            
            statement.executeUpdate();
            statement.close();
            
        } catch (Exception x) {
            System.out.println("Terjadi Kesalahan input data : " + x);
        }
    }
    
    public void UpdateDataFIx(String idbarang, String namaproduk, String hargaproduk, String kategori, String deskripsi, InputStream foto) {
        
        try {
            koneksi();
            String sql = "";
            PreparedStatement pst = null;
            
            if(foto == null){
                sql = "UPDATE produk SET nama=?, harga=?, kategori=?, deskripsi=? WHERE idbarang=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, namaproduk);
                pst.setString(2, hargaproduk);
                pst.setString(3, kategori);
                pst.setString(4, deskripsi);
                pst.setString(5, idbarang);
            }else{
                sql = "UPDATE produk SET nama=?, harga=?, kategori=?, deskripsi=?, imgproduk=? WHERE idbarang=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, namaproduk);
                pst.setString(2, hargaproduk);
                pst.setString(3, kategori);
                pst.setString(4, deskripsi);
                pst.setBlob(5, foto);
                pst.setString(6, idbarang);
            }
            
            pst.executeUpdate();
            pst.close();
        } catch (Exception x) {
            System.out.println("Terjadi Kesalahan input data : " + x);
        }
    }
    public void UpdateProfileFix(String iduser, String username, String pass, String notelp, InputStream foto) {
        
        try {
            koneksi();
            String sql = "";
            PreparedStatement pst = null;
            
            if(foto == null){
                sql = "UPDATE user SET username=?, notelp=?, password=? WHERE id=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, notelp);
                pst.setString(3, pass);
                pst.setString(4, iduser);
            }else{
                sql = "UPDATE user SET username=?, notelp=?, password=?, imgprofile=? WHERE id=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, notelp);
                pst.setString(3, pass);
                pst.setBlob(4, foto);
                pst.setString(5, iduser);
            }
            
            pst.executeUpdate();
            pst.close();
        } catch (Exception x) {
            System.out.println("Terjadi Kesalahan input data : " + x);
        }
    }
    
    public void updateDilihat(String uniqid, String value) {
        
        try {
            koneksi();
            String sql = "";
            PreparedStatement pst = null;
            
            sql = "UPDATE produk SET dilihat=? WHERE idbarang=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, value);
            pst.setString(2, uniqid);
            
            pst.executeUpdate();
            pst.close();
        } catch (Exception x) {
            System.out.println("Terjadi Kesalahan input data : " + x);
        }
    }
}