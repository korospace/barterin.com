<%-- 
    Document   : login
    Created on : Apr 25, 2021, 9:59:33 AM
    Author     : elkorospace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="img/logo.svg" type="image/x-icon">
        <title>barterin.com | login</title>
    
        <%-- CSS --%>
        <link rel="stylesheet" href="css/login.css">
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
    <% if(session.getAttribute("notelp") == null){ %>
        <section id="alert-responsive">
            <h1>Mobile version</h1>
            <h2>Not available</h2>
        </section>
        <section id="loading">
            <img id="logo" src="img/logo.svg">
        </section>
        <nav>
            <div class="logo-wraper">
                <img id="img-logo" src="img/logo.svg">
                <span>barterin.com</span>
            </div>
            <div class="link-wraper">
                <a href="index.jsp">Beranda</a>
                <a href="faq.jsp">FAQ</a>
            </div>
        </nav>
        
        <main>
            <section id="section-deskripsi">
                <h1>Di barterin.com</h1>
                <h2>Barang bekas jadi berkelas</h2>
                <img id="ilustrasi" src="img/second-ilustrasi.png">
            </section>
            <section id="section-form">
                <form method ="post" action="ValidateLogin?proses=login">
                    <p>Masuk ke Etalase saya</p>
                    <input type="text" name="notelp" placeholder="no.hp : +62012345678" autofocus autocomplete="off">
                    <input type="password" name="pass" placeholder="kata sandi">
                    <button type="submit" name="vSubmit" value="LOG IN">masuk</button>
                    <a href="daftar.jsp">belum punya akun?</a>
                </form>
            </section>
        </main>
        <script>
            //.. remove loading section
            window.addEventListener('load',function(){
                setTimeout(()=>{
                    document.querySelector('section#loading').remove();
                },500)
            });
        </script>
    <% }else{ %>
        <script>   
            alert('Anda sudah login');
            window.location.href="etalase.jsp";
        </script>
    <% } %>
    </body>
</html>
