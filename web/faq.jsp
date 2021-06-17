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
        <title>barterin.com | Buat akun</title>
    
        <%-- CSS --%>
        <link rel="stylesheet" href="css/faq.css">
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
            <div class="logo-wraper">
                <img id="img-logo" src="img/logo.svg">
                <span>barterin.com</span>
            </div>
            <div class="link-wraper">
                <a href="index.jsp">Beranda</a>
                <a href="etalase.jsp">Etalase saya</a>
            </div>
        </nav>
        <main>
            <div id="wraper-all">
                <h1 class="judul"># Tips mengisi deskripsi box</h1>
                <div class="sub-wraper">
                    <h3>1. "br" atau Break Line</h3>
                    <div class="sub-subwraper">
                        <input type="text" disabled="" value="nama: <br> Bagaskoro">
                        <h5><i>hasil:</i></h5>
                        <div class="hasil-wraper">
                            nama:<br>Bagaskoro
                        </div>
                    </div>
                    <h3>2. "b" atau Bold</h3>
                    <div class="sub-subwraper">
                        <input type="text" disabled="" value="nama: <b>Bagaskoro</b>">
                        <h5><i>hasil:</i></h5>
                        <div class="hasil-wraper">
                            nama: <b>Bagaskoro</b>
                        </div>
                    </div>
                    <h3>3. "u" atau Underline</h3>
                    <div class="sub-subwraper">
                        <input type="text" disabled="" value="nama: <u>Bagaskoro</u>">
                        <h5><i>hasil:</i></h5>
                        <div class="hasil-wraper">
                            nama: <u>Bagaskoro</u>
                        </div>
                    </div>
                    <h3>4. "i" atau Italic horizontal rule</h3>
                    <div class="sub-subwraper">
                        <input type="text" disabled="" value="nama: <i>Bagaskoro</i>">
                        <h5><i>hasil:</i></h5>
                        <div class="hasil-wraper">
                            nama: <i>Bagaskoro</i>
                        </div>
                    </div>
                    <h3>5. "hr" atau Horizontal Rule</h3>
                    <div class="sub-subwraper">
                        <input type="text" disabled="" value="nama: <hr> Bagaskoro">
                        <h5><i>hasil:</i></h5>
                        <div class="hasil-wraper">
                            nama: <hr style="width: 194px;"> Bagaskoro
                        </div>
                    </div>
                </div>
            </div>
        </main>
        
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
        
    </body>
    <script>
        //.. remove loading section
        window.addEventListener('load',function(){
            setTimeout(()=>{
                document.querySelector('section#loading').remove();
            },500)
        });
    </script>
</html>
