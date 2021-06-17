package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import koneksi.KoneksiDB;

/**
 *
 * @author elkorospace
 */

public class UserModel {
    String id,notelp,username,password,hak_akses;
    KoneksiDB db = null;

    public UserModel() {
        db = new KoneksiDB();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNotelp() {
        return notelp;
    }
    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHak_akses() {
        return hak_akses;
    }
    public void setHak_akses(String hak_akses) {
        this.hak_akses = hak_akses;
    }
    public List LoginUser(String notelp, String pass) {
        List data = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM user WHERE notelp='"+notelp+"' AND password='"+pass+"'";
            
            rs = db.ambilData(sql);
            
            while (rs.next()) {
                UserModel am = new UserModel();
                am.setId(rs.getString("id"));
                am.setUsername(rs.getString("username"));
                am.setNotelp(rs.getString("notelp"));
                am.setPassword(rs.getString("password"));
                am.setHak_akses(rs.getString("hak_akses"));
                data.add(am);
            }
            db.diskonek(rs);
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan cari login user, pada :\n" + a);
        }
        return data;
    }
    public void DaftarLogin(String setUsername, String setNotelp,String setPass) {
        try {
            String sql = "INSERT INTO user VALUES (null, '"+setUsername+"', '"+setNotelp+"','"+setPass+"','2',null)";
            
            db.SimpanData(sql);
            
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan:\n" + a);
        }
    }
}