var form = document.getElementById("register");
var boton = document.getElementById("button-register");

form.addEventListener('submit', function(e){
    e.preventDefault();
    var data = new FormData(form);

    if(data.get('nu') == "" || data.get('co') == "" || data.get('ce') == "" || data.get('telef') == "" || data.get('sex') == "" || data.get('date') == ""){
        alert("Disculpe, debe rellenar los espacios con los datos correctos");
        return
    }

    var datos = {
        method: "POST",
        body: fd
    };

    fetch('https://proyecto-web-2.herokuapp.com/Register', datos)
    .then( res => res.json())
    .then( data => {
        if(data.status == 200){
            window.open('https://proyecto-web-2.herokuapp.com/public/Login.html', "_self");
            alert(data.message);
        }else{
            alert(data.message);
        }
    })
    .catch(error => console.error());

});