package model;

import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import koneksi.KoneksiDB;

public class FilterModel {
    String Id,kategori,nm_brg,tahun,kondisi,spec,kontak;
    Blob foto;
    
    KoneksiDB db = null;

    public FilterModel() {
        db = new KoneksiDB();
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNm_brg() {
        return nm_brg;
    }

    public void setNm_brg(String nm_brg) {
        this.nm_brg = nm_brg;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }
    
    
    
    public List RetrieveFilter(String kategori) {
        List data = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "";
            if(!kategori.equals("")){
                sql = "SELECT Id, kategori, nm_brg, tahun, kondisi, spec, kontak, foto FROM barang WHERE kategori = '"+kategori+"'";
            }else{
                sql = "SELECT * FROM produk";
            }
            
            rs = db.ambilData(sql);
            
            while (rs.next()) {
                FilterModel am = new FilterModel();
                am.setId(rs.getString("Id"));
                am.setKategori(rs.getString("kategori"));
                am.setNm_brg(rs.getString("nm_brg"));
                am.setTahun(rs.getString("tahun"));
                am.setKondisi(rs.getString("kondisi"));
                am.setSpec(rs.getString("spec"));
                am.setKontak(rs.getString("kontak"));
                am.setFoto((rs.getBlob("foto")));
                data.add(am);
            }
            db.diskonek(rs);
        } catch (Exception a) {
            System.out.println("Terjadi kesalahan pada :\n" + a);
        }
        return data;
    }
}
