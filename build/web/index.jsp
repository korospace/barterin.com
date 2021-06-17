<%-- 
    Document   : index
    Created on : Apr 19, 2021, 11:29:05 AM
    Author     : elkorospace
--%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="model.FilterModel"%>
<%@page import="model.TambahModel"%>
<%@ page import = "java.util.*" %>
<%@ page import = "javax.swing.*" %>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%>
<%@ page import = "java.sql.Statement"%>
<%@ page import = "java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="img/logo.svg" type="image/x-icon">
        <title>Cari barang bekas dan tukar tambah</title>
    
        <%-- CSS --%>
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/glidecss/glide.core.min.css">
        <style>
            #alert-responsive{
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                z-index: 300;
                background-color: white;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            #alert-responsive h1,#alert-responsive h2{
                font-size: 30px;
                margin-bottom: 10px;
                background: linear-gradient(to right, #32CED5, #8FC982, #E7C634);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
            }
            #alert-responsive h2{
                font-size: 40px;
            }
            @media (max-width: 940px){ 
                #alert-responsive{
                    display: flex;
                }
            }
        </style>
    </head>
    <body>
        <section id="alert-responsive">
            <h1>Mobile version</h1>
            <h2>Not available</h2>
        </section>
        <section id="loading">
            <img id="logo" src="img/logo.svg">
        </section>
        <nav>
            <img id="logo" src="img/logo.svg">
            <div id="keyword-kategori">
                <form id="input-wraper" method="post" action="ValidateFilter">
                    <input id="keyword" type="text" name="keyword" placeholder="Iphone 12 bekas no lecet" autofocus autocomplete="off">
                    <button>
                        <img src="img/search.svg" width="100%">
                    </button>
                </form>
                <div id="kategori-wraper">
                    <a id="semua" href="ValidateFilter?proses=semua">Semua kategori</a>
                    <a id="elektronik" href="ValidateFilter?proses=elektronik">Elektronik</a>
                    <a id="pakaian" href="ValidateFilter?proses=pakaian">Pakaian</a>
                    <a id="kendaraan" href="ValidateFilter?proses=kendaraan">Kendaraan</a>
                    <a id="perabotan" href="ValidateFilter?proses=perabotan">Perabotan</a>
                    <a id="mainan" href="ValidateFilter?proses=mainan">Mainan</a>
                </div>
            </div>
            <div id="etalase-saya" 
                <% if(session.getAttribute("notelp") != null){ %>
                    onclick="return window.location.href='etalase.jsp';" 
                <% } %>
                >
                <span>etalase saya</span>
                <% 
                    if(session.getAttribute("notelp") != null){ 
                    //... user            
                    TambahModel xm = new TambahModel();
                    List<TambahModel> retrieve = new ArrayList<TambahModel>();
                    String notelp = session.getAttribute("notelp").toString();
                    retrieve = xm.getUserX(notelp);

                    Blob imgava = null;
                    byte[ ] imgavadata = null; 
                    String ava = "img/user.svg";
                    imgava = retrieve.get(0).getImgprofile();
                    if(imgava != null){
                        imgavadata = imgava.getBytes(1,(int)imgava.length());
                        String encodedImage=Base64.getEncoder().encodeToString(imgavadata);
                        ava="data:image/jpg;base64,"+encodedImage;
                    }
                %>
                <div id="foto-profile">
                    <img src="<%= (ava) %>" width="100%" style="opacity: 0.7;">
                </div>
                <% } else{ %>
                <div id="foto-profile" style="padding:5px;">
                    <img src="img/user.svg" width="100%" style="opacity: 0.4;">
                </div>
                <% }%>
                <% if(session.getAttribute("notelp") == null){ %>
                    <div id="login-wraper">
                        <a id="login" href="login.jsp">
                            masuk <img src="img/login.svg">
                        </a>
                        <a id="create-account" href="daftar.jsp">buat akun</a>
                    </div>
                <% } %>
            </div>
            <% if(session.getAttribute("notelp") == null){ %>
            <div id="bg-etalase">

            </div>
            <% } %>
        </nav>
        
        <header class="glide">
            <div class="glide__track" data-glide-el="track">
                <div class="glide__slides">
                    <div class="glide__slide">
                        <img src="img/banner/banner1.webp" width="92%">
                    </div>
                    <div class="glide__slide">
                        <img src="img/banner/banner1.webp" width="92%">
                    </div>
                </div>
            </div>
        </header>
        
        <main>
            <%
                Blob image = null;
                byte[ ] imgData = null ;
                String type="kategori",value="semua",kategoriActive="no";

                TambahModel tm = new TambahModel();
                List<TambahModel> dataretrieve = new ArrayList<TambahModel>();
                
                if(session.getAttribute("kategori") != null){
                    type = "kategori";
                    value = session.getAttribute("kategori").toString();
                    kategoriActive=value;
                }
                if(session.getAttribute("keyword") != null){
                    type = "keyword";
                    value = session.getAttribute("keyword").toString();
                }
                
                double dataPerHalaman = 12;
                double totalDataBarang = Double.valueOf(tm.getTotData(type,value));
                double jumlahPagination = Math.ceil(totalDataBarang/dataPerHalaman);
                String halamanAktif = "1";
                
                if(session.getAttribute("halaman") != null){
                    halamanAktif = session.getAttribute("halaman").toString();
                }
                double indexAwal = (dataPerHalaman*Double.valueOf(halamanAktif))-dataPerHalaman;

                dataretrieve = tm.Retrieve(type,value,indexAwal,dataPerHalaman);
                int i=0;
                while(i < dataretrieve.size()) { 


                    image = dataretrieve.get(i).getFoto();
                    imgData = image.getBytes(1,(int)image.length());

                    String imagedata="";
                    String encodedImage=Base64.getEncoder().encodeToString(imgData);
                    if(!encodedImage.equals("")){
                        imagedata="data:image/jpg;base64,"+encodedImage; 
                    }else{
                        imagedata="img/produk/produk.svg"; 
                    }
            %>  
               <span id="kategoriactive"  data-kategoriactive="<%= (kategoriActive) %>" style="display: none;"></span>
               <a href="" class="card" onclick="pdefault(event);">
                   <div id="img-wraper" style="position: relative;">
                       <img id="img-bg" src="img/produk/produk.svg" style="opacity: 0;position: relative; z-index: 1;">
                       <img id="img-utama" src="<%=(imagedata) %>" style="position: absolute; z-index: 10;width:100%;height: 100%;">
                   </div>
                   <span onclick="modalsAddProduct('<%= dataretrieve.get(i).getPenjual() %>','<%= dataretrieve.get(i).getNamaproduk() %>','<%= dataretrieve.get(i).getHargaproduk() %>','<%= dataretrieve.get(i).getKategori() %>','<%= dataretrieve.get(i).getDeskripsi() %>','<%= dataretrieve.get(i).getNotelp() %>','<%=(imagedata) %>','<%= dataretrieve.get(i).getDilihat() %>','<%= dataretrieve.get(i).getIdBarang() %>');">
                       <div>
                           <div>
                               detail barang
                           </div>
                       </div>
                   </span>
                   <div id="harga">Rp. <%= dataretrieve.get(i).getHargaproduk() %></div>
               </a>
            <% i++; } %>
        </main>
        
        <section id="pagination">
            <%
                int x=0;
                int y=1;
                while(x < jumlahPagination) { 
            %> 
            <a href="ValidateFilter?proses=pagination&page=<%=(y) %>" class="wraper"><span><%=(y) %></span></a>
            <% y++; x++; } %>
        </section>
        
        <footer>
            <div id="logo-login">
                <div id="footer-logo">
                    <img src="img/logo-white.svg">
                    barterin.com
                </div>
                <a class="footer-login" href="login.jsp">gabung</a>
            </div>
            <h1>Copyright © 2010-2021 PT. BARTERIN WITH RAKYAT All rights reserved.</h1>
        </footer>

        <div id="modals-container" class="close" onclick="closeAddProduct(this,event);">
            <form id="form-add-prod" method ="post" action="" enctype="multipart/form-data">
                <h1 id="judul">detil product</h1>
                <div id="wraper">
                    <div id="img-wraper" style="width: 100%;position: relative;overflow: hidden;margin-bottom: 10px;">
                        <img src="img/produk/produk.svg" style="opacity: 0;position: relative;z-index: 10;">
                        <img id="target" src="" style="position: absolute;z-index: 20;width: 100%;height: 100%;top: 0;">
                    </div>
                    <div style="padding: 0 24px;">
                        <h3>penjual:</h3>
                        <input type="text" name="penjual" disabled>
                        <h3>nama produk:</h3>  
                        <input type="text" name="namaproduk" disabled>
                        <h3>harga:</h3>
                        <input type="text" name="hargaproduk" disabled>
                        <h3>kategori:</h3>
                        <input type="text" name="kategori" disabled>
                        <h3>deskripsi:</h3>
                        <div id="textarea"></div>
                    </div>
                </div>
                <button onclick="pdefault(event);callPenjual();">hubungi penjual</button>
            </form>
        </div>
        
        <script src="js/glide.min.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>
