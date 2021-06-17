<%-- 
    Document   : etalase
    Created on : Jun 14, 2021, 10:20:35 AM
    Author     : elkorospace
--%>
<%@page import="java.sql.Blob"%>
<%@page import="model.TambahModel"%>
<%@ page import = "java.util.*" %>
<%@ page import = "javax.swing.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/logo.svg" type="image/x-icon">
    <title>barterin.com | etalase</title>

    <%-- CSS --%>
    <link rel="stylesheet" href="css/etalase.css">
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
<% if(session.getAttribute("notelp") != null){ %>
<section id="alert-responsive">
    <h1>Mobile version</h1>
    <h2>Not available</h2>
</section>
<section id="loading">
    <img id="logo" src="img/logo.svg">
</section>
<div id="container">
    <nav>
        <div class="logo-wraper">
            <img id="img-logo" src="img/logo.svg">
            <span>barterin.com</span>
        </div>
        <div class="link-wraper">
            <a href="index.jsp">Beranda</a>
            <a href="" onclick="pdefault(this,event);modalsSetting();">Setting</a>
            <a href="LogoutServlet">Logout</a>
        </div>
    </nav>

    <section id="tombol-wraper">
        <div class="btn-out1" onclick="modalsAddProduct('add product','TambahData?proses=tambah');">
            <div>
                <div>
                    add product
                </div>
            </div>
        </div>
    </section>

    <section id="produk-wraper">
        <%
            
            Blob image = null;
            byte[ ] imgData = null ;

            String id = session.getAttribute("id").toString();

            TambahModel tm = new TambahModel();
            List<TambahModel> dataretrieve = new ArrayList<TambahModel>();

            dataretrieve = tm.RetrievePelanggan(id);
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
        <div class="card-produk">
            <img src="<%= (imagedata) %>">
            <div id="deskripsi1-wraper">
                <h3>nama:</h3>
                <p  style="display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;overflow: hidden;text-overflow: ellipsis;"><%= dataretrieve.get(i).getNamaproduk()%></p>
                <h3>harga:</h3>
                <p><%= dataretrieve.get(i).getHargaproduk() %></p>
                <h3>dilihat:</h3>
                <p><%= dataretrieve.get(i).getDilihat() %> kali</p>
            </div>
            <div id="deskripsi2-wraper">
                <h3>deskripsi:</h3>
                <div class="main deskripsi" id="main<%= dataretrieve.get(i).getId() %><%= dataretrieve.get(i).getIdBarang() %>" data-id="main<%= dataretrieve.get(i).getId() %><%= dataretrieve.get(i).getIdBarang() %>" data-desk="<%= dataretrieve.get(i).getDeskripsi() %>"></div>
            </div>
            <div id="action-wraper">
                <a href=""  onclick="pdefault(this,event);modalsAddProduct('edit product','ValidateUpdate?proses=update','<%= dataretrieve.get(i).getIdBarang() %>','<%= dataretrieve.get(i).getNamaproduk()%>','<%= dataretrieve.get(i).getHargaproduk() %>','<%= dataretrieve.get(i).getKategori() %>','<%= dataretrieve.get(i).getDeskripsi() %>','<%=(imagedata) %>');">edit</a>
                <a href="" onclick="pdefault(this,event);deleteProduk('ValidateDelete?id=<%= dataretrieve.get(i).getId() %>&idbarang=<%= dataretrieve.get(i).getIdBarang() %>');">delete</a>
            </div>
        </div>
        <%
            i++;
           }
        %>
    </section>
</div>

<footer>
    <div id="logo-login">
        <div id="footer-logo">
            <img src="img/logo-white.svg">
            barterin.com
        </div>
    </div>
    <h1>Copyright © 2010-2021 PT. BARTERIN WITH RAKYAT All rights reserved.</h1>
</footer>

<div id="modals-container" class="close" onclick="closeAddProduct(this,event);">
    <form id="form-add-prod" method ="post" action="" enctype="multipart/form-data">
        <h1 id="judul">add product</h1>
        <input type="hidden" name="idbarang">
        <div id="wraper">
            <div id="img-wraper">
                <img src="img/produk/produk.svg" width="100%" height="100%" id="targetPreview">
                <p>400 x 400</p>
            </div>
            <input type="file" onchange="imgPreview(this,'produk')">
            <input type="text" name="namaproduk" placeholder="nama produk">
            <input type="text" name="hargaproduk" placeholder="hagra produk, ex:200.000.000">
            <select name="kategori">
                <option id="null" value="null">-- kategori --</option>
                <option id="elektronik" value="elektronik">elektronik</option>
                <option id="pakaian" value="pakaian">pakaian</option>
                <option id="perabotan" value="perabotan">perabotan</option>
                <option id="kendaraan" value="kendaraan">kendaraan</option>
                <option id="mainan" value="mainan">mainan</option>
            </select>
            <textarea name="deskripsi-filter" rows="10" placeholder="deskripsi produk"></textarea>
            <textarea name="deskripsi" style="display:none;"></textarea>
            <a id="hrefTips" href="faq.jsp">Tips mengisi deskripsi</a>
        </div>
        <button>kirim</button>
    </form>
</div>

<%
    //... user            
    TambahModel xm = new TambahModel();
    List<TambahModel> retrieve = new ArrayList<TambahModel>();
    String notelp = session.getAttribute("notelp").toString();
    retrieve = xm.getUserX(notelp);
    String iduser = session.getAttribute("id").toString();
    
    Blob imgava = null;
    byte[ ] imgavadata = null; 
    String ava = "img/user.svg";
    imgava = retrieve.get(0).getImgprofile();
    if(imgava != null){
        imgavadata = imgava.getBytes(1,(int)imgava.length());
        String encodedImage=Base64.getEncoder().encodeToString(imgavadata);
        if(!encodedImage.equals("")){
            ava="data:image/jpg;base64,"+encodedImage; 
        }else{
            ava="img/user.svg"; 
        }
    }
%>
<div id="modals-setting" class="close" onclick="closeSetting(this,event);">
    <form id="form-profile" method ="post" action="ValidateUpdate?proses=updateProfile" enctype="multipart/form-data">
        <h1 id="judul">edit profile</h1>
        <input type="hidden" name="iduser" value="<%=(iduser) %>">
        <div id="wraper">
            <div id="ava-wraper">
                <img src="<%=(ava) %>" width="100%" height="100%" id="targetPreview">
                <p>400 x 400</p>
            </div>
            <input type="file" onchange="imgPreview(this,'setting')">
            <h3>username :</h3>
            <input type="text" name="username" placeholder="username" value="<%=retrieve.get(0).getUsername() %>">
            <h3>password :</h3>
            <input type="text" name="password" placeholder="password" value="<%=retrieve.get(0).getPass() %>">
            <h3>no.telp :</h3>
            <input type="text" name="notelp" placeholder="No.telp, ex: 62851445567"  value="<%=retrieve.get(0).getNoTelp() %>">
        </div>
        <button>kirim</button>
    </form>
</div>
        
<script src="js/etalase.js"></script>
        
<% }else{ %>
    <script>   
        window.location.href="login.jsp";
    </script>
<% } %>

</body>
</html>