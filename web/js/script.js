//.. remove loading section
window.addEventListener('load',function(){
    setTimeout(()=>{
        document.querySelector('section#loading').remove();
    },500)
});

//.. kategori active
if(document.querySelector('span#kategoriactive')){ 
    let kategoriActive = document.querySelector('span#kategoriactive').dataset.kategoriactive;
    if(kategoriActive !== 'no'){
        document.querySelector(`a#${kategoriActive}`).classList.add('active');
    }
}

//.. slider
if(document.querySelector('.glide')){
    var glide = new Glide('.glide', {
        type: 'carousel',
        focusAt: 'center',
        autoplay: 5000,
        animationTimingFunc: 'ease-in-out',
        animationDuration: 1000,
        gap: 0,
        perView: 1
    });

    glide.mount();
}

//.. btn etalase
if(document.querySelector('#etalase-saya')){
    document.querySelector('#etalase-saya').addEventListener('click',function(){
       if(document.querySelector('div#bg-etalase')){
            document.querySelector('div#bg-etalase').classList.add('active');   
       }
       document.querySelector('#etalase-saya').classList.add('active');
    });
}
if(document.querySelector('div#bg-etalase')){
    document.querySelector('div#bg-etalase').addEventListener('click',function(){
       document.querySelector('div#bg-etalase').classList.remove('active');
       document.querySelector('#etalase-saya').classList.remove('active');
    });
}

//.. remove prevent default
function pdefault(event){
    event.preventDefault();
}

//.. open modals add product
let noTelp = "";
let penjuall= "";
let barang = ""
function modalsAddProduct(penjual,nama,harga,kategori,deskripsi, notelp, foto, dilihat, uniqid){
    noTelp = notelp;
    penjuall= penjual;
    barang = nama;
    document.body.style.overflow="hidden";
    document.querySelector('#modals-container').classList.add('active');
    document.querySelector('#form-add-prod').classList.add('active');
    
    document.querySelector('#form-add-prod img#target').src=foto;
    document.querySelector('#form-add-prod input[name=penjual]').value=penjual;
    document.querySelector('#form-add-prod input[name=namaproduk]').value=nama;
    document.querySelector('#form-add-prod input[name=hargaproduk]').value=harga;
    document.querySelector('#form-add-prod input[name=kategori]').value=kategori;
    document.querySelector('#form-add-prod div#textarea').innerHTML=`${deskripsi}`;
    
    updateDilihat(dilihat,uniqid);
}

//.. hubungi penjual
function callPenjual(){
    window.open(`https://wa.me/${noTelp}?text=Pak%20${penjuall}%20pemilik%20${barang}`, '_blank');
}

//.. close modals add product
function closeAddProduct(e,event){
    if(event.target.classList.contains('close')){
       document.body.style.overflow="auto";
       document.querySelector('#modals-container').classList.remove('active');
       document.querySelector('#form-add-prod').classList.remove('active');
       document.querySelectorAll('#form-add-prod input').forEach(e=>{
           e.value = "";
       });
       document.querySelector('#form-add-prod #textarea').innerHTML="";
    }
}

function updateDilihat(dilihat,uniqid){
    let value = parseInt(dilihat)+1;
    
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `ValidateUpdate?proses=updateDilihat&uniqid=${uniqid}&value=${value}`, true);
    xhr.send();
}
