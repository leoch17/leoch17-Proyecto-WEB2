var btnAbrirPopup = document.getElementById('btn-abrir-popup'),
		overlay = document.getElementById('overlay'),
		popup = document.getElementById('popup'),
		btnCerrarPopup = document.getElementById('btn-cerrar-popup');
		var tiempo;
		
		tiempo =  btnAbrirPopup.addEventListener('click', function(){
		setTimeout(() => {
		overlay.classList.add('active');
		popup.classList.add('active');
		    },1000);
		});
		
		tiempo = btnCerrarPopup.addEventListener('click', function(e){
		setTimeout(() =>{
		overlay.classList.remove('active');
		popup.classList.remove('active');
		},1000);
		});
		
		function stop() {
		    clearTimeout(tiempo);
		}

function init(){
			  let url = location.search;
			  var xvalues = url.split("=");
			  sesionActual = xvalues[1];
			  document.getElementById("ce").value=sesionActual;
			  document.getElementById("perfil").value=sesionActual;	
			  mostrarInfo();
			  		  			 
		}	
		
		function mostrarInfo(){
			var email = document.getElementById("ce").value;
			var formData = new FormData();
			formData.append('ce',ce);

			 var init = {
				method: "DELETE",
				body: formData
			 }

			 fetch('https://murmuring-wildwood-16373.herokuapp.com/SessionUser', init)
				 .then(response => response.json())
				 .then(data => {
					 if(data.status == 200){
						 insertarInfo(data.name,ce);
					 }else{
						 alert("Error En Obtencion de Data");
					 }			
				 })
				  .catch(error => console.error('AAAAA  Error:', error));
		}
		
		
		function insertarInfo(nu,ce){
			 var info = document.getElementById("info");
			 
			  var parrafo1 = document.createElement("h2"); 
			  var texto1 = document.createTextNode(nu);
			  parrafo1.appendChild(texto1);
			  parrafo1.style ="position: relative; left: 400px; top: -105px;  color: rgb(230, 235, 247); text-decoration: none;";
			  info.appendChild(parrafo1);
				
			  var parrafo2 = document.createElement("h2"); 
			  var texto2 = document.createTextNode("Nombre: "+nu);
			  parrafo2.appendChild(texto2);
			  parrafo2.style ="position: relative; left: -245px; top: 38px;";
			  info.appendChild(parrafo2);
				
			  var parrafo3 = document.createElement("h3"); 
			  var texto3 = document.createTextNode("Correo: "+ce);
		      parrafo3.appendChild(texto3);
			  parrafo3.style ="position: relative; left: -245px; top: -38px;";
			  info.appendChild(parrafo3);
			  			  
		}
		
		function borrarInfo(){
			 var info = document.getElementById("info");info.remove();
			 modificarInfo();		
		}
		
		
		function modificarInfo(){
			 var nombre = document.getElementById("name").value;
			 var infor = document.getElementById("infoActual");
				
			  var parrafo1 = document.createElement("h4"); 
			  var texto1 = document.createTextNode("Nombre: "+name);
			  parrafo1.appendChild(texto1);
			  parrafo1.style ="position: relative; left: -200px;";
			  infor.appendChild(parrafo1);							
			  
			  window.location.href="perfil.html?ce="+document.getElementById("correo").value;
		}
		
		function cerrarVentana(){
			window.location.href="perfil.html?ce="+document.getElementById("ce").value;
		}
		
		var subirArchivo= event => {
			  const archivos = event.target.files;
			  var datos = new FormData();

			 datos.append('imagenes', archivos[0]);
			 datos.append('mensaje','Foto Subida');

			  var init = {
					method: "PUT",
					body: datos 
				};
		
			  fetch('https://murmuring-wildwood-16373.herokuapp.com/SessionUser', init)
			  .then(response => response.json())
			  .then(data => {
				  if(data.status == 200){
					  img(data.filename);				  
				  }else{
					  alert("Error Al Subir La Foto");
				  }			
			  })
			  .catch(error => console.error('AAAAA  Error:', error));
			}
		
			document.querySelector('#file-upload').addEventListener('change', event => {
			    subirArchivo(event);
			});
			
			function img(imagen){
				var imguser = document.getElementById("imguser"); imguser.remove();
				var img = document.getElementById("imagen");
				var imgVer = new Image(); 
				imgVer.src = "ImgUser/"+imagen; 
		        imgVer.style = "width: 200px; height: 250px; border-radius: 50%; position: relative; left: 20px;";

		        img.appendChild(imgVer);
			}
			
			function cerrarPerfil(){
				window.location.href="session.html?ce="+document.getElementById("ce").value;
			}
			
			function borrarPerfil(){
				var info = document.getElementById("body"); 
				window.location.href="session.html?ce="+document.getElementById("ce").value; info.remove();
			}