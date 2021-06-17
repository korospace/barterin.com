package model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import koneksi.KoneksiDB;


public class UpdateModel {
    
    KoneksiDB db = null;

    public UpdateModel() {
        db = new KoneksiDB();
    }
    
    public void UpdateData(String idbarang, String namaproduk, String hargaproduk, String kategori, String deskripsi, InputStream foto) {
        try {
            db.UpdateDataFIx(idbarang, namaproduk, hargaproduk, kategori, deskripsi, foto);
            
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
    }
    
    public void UpdateProfile(String iduser,String username, String pass, String notelp, InputStream foto) {
        try {
            
            db.UpdateProfileFix(iduser, username, pass, notelp, foto);
            
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
    }
    
    public void updateDilihat(String uniqid, String value) {
        try {
            
            db.updateDilihat(uniqid,value);
            
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
    }
}
