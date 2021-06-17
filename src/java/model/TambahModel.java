/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elkorospace
 */

package model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import koneksi.KoneksiDB;

public class TambahModel {
    String Id,idbarang,namaproduk,hargaproduk,kategori,deskripsi,notelp,penjual,dilihat;
    String username,password,noTelp;
    Blob foto,imgprofile;
    KoneksiDB db = null;

    public TambahModel() {
        db = new KoneksiDB();
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    
    public String getIdBarang() {
        return idbarang;
    }

    public void setIdBarang(String Id) {
        this.idbarang = Id;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }
    
    public String getHargaproduk() {
        return hargaproduk;
    }

    public void setHargaproduk(String hargaproduk) {
        this.hargaproduk = hargaproduk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    public String getDilihat() {
        return dilihat;
    }

    public void setDilihat(String dilihat) {
        this.dilihat = dilihat;
    }
    
    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }
    
    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    public String getTotData(String type,String value) {
        return this.countAllBarang(type,value);
    }

    public Blob getImgprofile() {
        return imgprofile;
    }
    
    public void setImgprofile(Blob img) {
        this.imgprofile = img;
    }
    
    public void TambahData(String nama, String hagra, String kategori, String deskripsi, String id, InputStream foto, String notelp, String username) {
        try {
            
            db.SimpanDataFIx(nama, hagra, kategori, deskripsi, id, foto, notelp, username);
            
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
    }
    
    public List Retrieve(String type,String value,Double indexAwal,Double jmlDataPerhalaman) {
        List data = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "";
            DecimalFormat df = new DecimalFormat("###");
            
            if(type.equals("keyword")){
                sql = "SELECT * FROM produk WHERE nama LIKE '%"+value+"%' ORDER BY idbarang DESC LIMIT "+df.format(indexAwal).toString()+","+df.format(jmlDataPerhalaman).toString()+"";
            }else{    
                if(value.equals("semua")){
                    sql = "SELECT * FROM produk ORDER BY idbarang DESC LIMIT "+df.format(indexAwal).toString()+","+df.format(jmlDataPerhalaman).toString()+"";
                }else{
                    sql = "SELECT * FROM produk WHERE kategori='"+value+"' ORDER BY idbarang DESC LIMIT "+df.format(indexAwal).toString()+","+df.format(jmlDataPerhalaman).toString()+"";
                }
            }
            
            rs = db.ambilData(sql);
            
            while (rs.next()) {
                TambahModel am = new TambahModel();
                am.setId(rs.getString("id"));
                am.setIdBarang(rs.getString("idbarang"));
                am.setNamaproduk(rs.getString("nama"));
                am.setHargaproduk(rs.getString("harga"));
                am.setKategori(rs.getString("kategori"));
                am.setDeskripsi(rs.getString("deskripsi"));
                am.setFoto((rs.getBlob("imgproduk")));
                am.setNotelp(rs.getString("notelp"));
                am.setPenjual(rs.getString("penjual"));
                am.setDilihat(rs.getString("dilihat"));
                data.add(am);
            }
            db.diskonek(rs);
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
        return data;
    }
    
    public List RetrievePelanggan(String id) {
        List data = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM produk WHERE id='"+id+"' ORDER BY idbarang DESC";
            
            rs = db.ambilData(sql);
            
            while (rs.next()) {
                TambahModel am = new TambahModel();
                am.setId(rs.getString("id"));
                am.setIdBarang(rs.getString("idbarang"));
                am.setNamaproduk(rs.getString("nama"));
                am.setHargaproduk(rs.getString("harga"));
                am.setKategori(rs.getString("kategori"));
                am.setDeskripsi(rs.getString("deskripsi"));
                am.setDilihat(rs.getString("dilihat"));
                am.setFoto((rs.getBlob("imgproduk")));
                data.add(am);
            }
            db.diskonek(rs);
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
        return data;
    }
    
    public List getUserX(String notelp) {
        List data = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM user WHERE notelp='"+notelp+"'";
            
            rs = db.ambilData(sql);
            
            while (rs.next()) {
                TambahModel am = new TambahModel();
                am.setUsername(rs.getString("username"));
                am.setNoTelp(rs.getString("notelp"));
                am.setPass(rs.getString("password"));
                am.setImgprofile((rs.getBlob("imgprofile")));
                data.add(am);
            }
            db.diskonek(rs);
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
        return data;
    }
    
    public String countAllBarang(String type, String value) {
        ResultSet rs = null;
        String data = "";
        try {
            
            String sql = "";
            if(type.equals("keyword")){
                sql = "SELECT count(*) as totData FROM produk WHERE nama LIKE '%"+value+"%' ORDER BY idbarang DESC";
            }else{    
                if(value.equals("semua")){
                    sql = "SELECT count(*) as totData FROM produk ORDER BY idbarang DESC";
                }else{
                    sql = "SELECT count(*) as totData FROM produk WHERE kategori='"+value+"' ORDER BY idbarang DESC";
                }
            }
            rs = db.ambilData(sql);
            
            while (rs.next()) {
                data = rs.getString("totData");
            }
            db.diskonek(rs);
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
        return data;
    }
    
    public void DeleteData(String id,String idbarang) {
        try {
            String sql = "DELETE FROM produk WHERE id='"+id+"' AND idbarang='"+idbarang+"'";
            
            db.SimpanData(sql);
            
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
    }
    
}

