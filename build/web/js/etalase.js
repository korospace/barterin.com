//.. remove loading section
window.addEventListener('load',function(){
    setTimeout(()=>{
        document.querySelector('section#loading').remove();
    },500)
});

//.. set height card produk
document.querySelectorAll('.card-produk').forEach(e=>{
    e.style.height = document.querySelector('.card-produk img').clientHeight+10+'px';
});

//.. set produk description
let arrDesc = [];
document.querySelectorAll('div.main.deskripsi').forEach((e)=>{
    arrDesc.push({
        id:e.dataset.id,
        desk:e.dataset.desk
    });
});
arrDesc.forEach((e)=>{
    let desk = e.desk.replace(/\r?\n|\r/g,"");
    document.querySelector(`div#${e.id}`).innerHTML = `${desk}`;
});

//.. move prevent default
function pdefault(e,event){
    event.preventDefault();
}

//.. open modals add product
function modalsAddProduct(judul,url,idbarang=null,nama=null,harga=null,kategori=null,deskripsi=null,foto=null){
    document.querySelector('#modals-container #judul').innerHTML=judul;
    document.querySelector('#modals-container').classList.add('active');
    document.querySelector('#form-add-prod').classList.add('active');
    document.querySelector('#form-add-prod').setAttribute('action',url);
    if(judul === 'edit product'){
        document.querySelector('#form-add-prod img').src=foto;
        document.querySelector('#form-add-prod input[name=idbarang]').value=idbarang;
        document.querySelector('#form-add-prod input[name=namaproduk]').value=nama;
        document.querySelector('#form-add-prod input[name=hargaproduk]').value=harga;
        document.querySelectorAll(`#form-add-prod option`).forEach(e=>{
           e.selected = false; 
        });
        document.querySelector(`#form-add-prod option#${kategori}`).selected=true;
        document.querySelector('#form-add-prod textarea[name=deskripsi-filter]').value=deskripsi;
        document.querySelector('#form-add-prod textarea[name=deskripsi]').value=deskripsi;

        oldValue = document.querySelector('#form-add-prod textarea[name=deskripsi-filter]').value;
    }
}

//.. close modals add product
function closeAddProduct(e,event){
    if(event.target.classList.contains('close')){
       document.querySelector('#modals-container').classList.remove('active');
       document.querySelector('#form-add-prod').classList.remove('active');
       document.querySelector('#form-add-prod #targetPreview').src="img/produk/produk.svg";
       document.querySelector('#form-add-prod input[type=file]').removeAttribute("name");
       document.querySelectorAll('#form-add-prod input').forEach(e=>{
           e.value = "";
       });
       document.querySelector('#form-add-prod textarea').value=null;
       document.querySelectorAll(`#form-add-prod option`).forEach(e=>{
           e.selected = false; 
       });
       oldValue = document.querySelector('#form-add-prod textarea').value;
    }
}

//.. open modals setting
function modalsSetting(){
    document.querySelector('#modals-setting').classList.add('active');
    document.querySelector('#form-profile').classList.add('active');
    
}

//.. close modals setting
function closeSetting(e,event){
    if(event.target.classList.contains('close')){
       document.querySelector('#modals-setting').classList.remove('active');
       document.querySelector('#form-profile').classList.remove('active');
       document.querySelector('#form-profile input[type=file]').removeAttribute("name");
    }
}

//.. image preview
function imgPreview(e,modal) {
    let inputFile = e;
    let exktensionFile = inputFile.files[0].type; 
    let sizeFile = inputFile.files[0].size; 
    let fotoPreview = e.previousElementSibling.firstElementChild;
    
    if(!exktensionFile.includes('image')){
        alert('Terdeteksi bukan file foto');
        inputFile.value = "";
        return false;
    }
    if(sizeFile > 1048576){
        alert('Lebih dari 1mb');
        inputFile.value = "";
        return false;
    }
    
    if(modal == 'produk'){
        e.setAttribute("name","imgproduk");
    }else{
        e.setAttribute("name","imgprofile");
    }
    
    let fileFoto = new FileReader();
    fileFoto.readAsDataURL(inputFile.files[0]);
    fileFoto.onload = function(el){
        fotoPreview.src = el.target.result;
    }
};

//.. delete produk
function deleteProduk(url){
    let isDelete = confirm('data akan dihapus permanen, anda yakin?');
    
    if(isDelete){
        window.location.href=url;
    }
}

//.. check deskripsi
let oldValue = "";
document.querySelector('#form-add-prod textarea[name=deskripsi-filter]').addEventListener('keyup',(e)=>{
    if(/style=\"|<table|<\/table|<body|<\/body|<a|<\/a|<ul|<\/ul|<li|<\/li|<div|<\/div|<main|<\/main|<section|<\/section|<footer|<\/footer|<header|<\/header|<nav|<\/nav/.test(e.target.value)){
        e.target.value=oldValue;
        alert('Maaf, anda memasukan tag hmtl yang dilarang !!');
    }
    
    let desk = e.target.value.replace(/\r?\n|\r/g,"");
    document.querySelector('#form-add-prod textarea[name=deskripsi]').value = desk;
});