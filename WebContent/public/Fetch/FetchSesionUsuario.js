 var formulario = document.getElementById("form-archivo");		
		 var subirArchivo= event => {
			  const archivos = event.target.files;
			  var email = document.getElementById("email").value;
			  var datos = new FormData(formulario);

			 datos.append('archivo', archivos[0]);
			 datos.append('email',email);

			  var init = {
					method: "POST",
					body: datos 
				};
		
			  fetch('https://murmuring-wildwood-16373.herokuapp.com/Archivos', init)
			  .then(response => response.json())
			  .then(data => {
				  if(data.status == 200){
					  obtenerData(email);				  
				  }else{
					  alert("Error Al Subir El Archivo");
				  }			
			  })
			  .catch(error => console.error('AAAAA  Error:', error));
			}
		
			document.querySelector('#subir_archivo').addEventListener('change', event => {
			    subirArchivo(event);
			});
			
			function init(){
				  let url = location.search;
				  var xvalues = url.split("=");
				  sesionActual = xvalues[1];
				  document.getElementById("email").value=sesionActual;
				  document.getElementById("sesion").value=sesionActual;
				  obtenerData(document.getElementById("email").value);					
			}			
			
			function obtenerData(email){
				var formData = new FormData();
				formData.append('email',email);

				 var init = {
					method: "POST",
					body: formData
				 }

				 fetch('https://murmuring-wildwood-16373.herokuapp.com/ObtencionArchivos', init)
					 .then(response => response.json())
					 .then(data => {
						 if(data.status == 200){
							 crearGridData(data.archivos);
						 }else{
							 alert("Error Al Subir El Archivo");
						 }			
					 })
					  .catch(error => console.error('AAAAA  Error:', error));
			}
			
			
			 function crearGridData(data){

				var contenido='';
				var	cabecera='';

				//------Limpiar la tabla....
					dvtf=document.getElementById("TF");
					let bdivhtf=document.getElementById("hTF"); bdivhtf.remove();
					let bdivbtf=document.getElementById("bTF"); bdivbtf.remove();
					const ndivhtf = document.createElement('thead'); 
					dvtf.appendChild(ndivhtf);
					ndivhtf.id="hTF";
					const ndivbtf = document.createElement('tbody'); 
					dvtf.appendChild(ndivbtf);
					ndivbtf.id="bTF";
				//----------------------------

					//----Creando cabecera de la tabla
					divhtf=document.getElementById("hTF"); //divhtf.remove();
			    	const fila = document.createElement('tr');
					const col1= document.createElement('th');		
					const col2= document.createElement('th');		
					const col3= document.createElement('th');		
					const col4= document.createElement('th');		
	
					fila.appendChild(col1);
					fila.appendChild(col2);
					fila.appendChild(col3);
					fila.appendChild(col4);

					divhtf.appendChild(fila); 
					fila.style = "background-color: #528FD5; color: #FFFFFF; font-family: 'Oswald', sans-serif; font-size: 14px;";
					col1.style="width: 10%;"; col1.textContent="No.";
					col2.style="width: 70%;"; col2.textContent="Nombre del Archivo";
					col3.style="width: 10%;"; col3.textContent="Ver";
					col4.style="width: 10%;"; col4.textContent="Borrar";		

					//----Creando Cuerpo o Body de la tabla
					divbtf=document.getElementById("bTF"); //divbtf.remove();
					let cont=0;
					for (let dato of data) {
						cont++;
						divhtf=document.getElementById("hTF");
						const fila = document.createElement('tr');
						const col1= document.createElement('th');		
						const col2= document.createElement('th');		
						const col3= document.createElement('th');		
						const col4= document.createElement('th');		

						const href1= document.createElement('a');	
						const href2= document.createElement('a');	

						var imgVer = new Image(); imgVer.src = 'imagenes/ver.png'; imgVer.width="24";
						href1.appendChild(imgVer);
						href1.href="javascript: verMedia('"+dato+"')";
					    col3.appendChild(href1);
					    
						var imgBor = new Image(); imgBor.src = 'imagenes/borrar.png'; imgBor.width="22";
						href2.appendChild(imgBor);
						href2.href="javascript: borMedia('"+dato+"')";
					    col4.appendChild(href2);
						
						fila.appendChild(col1);
						fila.appendChild(col2);
						fila.appendChild(col3);
						fila.appendChild(col4);
						divbtf.appendChild(fila); 
						fila.style = "background-color: #528FD5; color: #FFFFFF; font-family: 'Oswald', sans-serif; font-size: 14px;";
						col1.style="width: 35%;"; col1.textContent=cont;
						col2.style="width: 35%;"; col2.textContent=dato;
					}

					return;							
				}
			 
	function verMedia(file){

		var archivos = document.getElementById("div-archivos");
      
		var xvalues = file.split(".");
		var formato = xvalues[xvalues.length - 1];
		
		if(formato == 'mp4'|| formato == 'MP4' || formato == 'ogg' || formato == 'OGG'){		
			
			var videos = document.createElement("video"); 
			videos.src = "ContenedorArchivos/"+file; 
			videos.id = "videos";
			videos.width = "450";
			videos.style = "position: relative ; left: 250px;";		
			archivos.appendChild(videos);
			
			 var boton1 = document.createElement("INPUT"); 
		     var boton2 = document.createElement("INPUT"); 
		     var boton3 = document.createElement("INPUT"); 
		     var boton4 = document.createElement("INPUT"); 

		     boton1.setAttribute("type","button");
			    boton1.setAttribute("value","Reproducir");
			    boton1.setAttribute("onclick" , "document.getElementById('videos').play()");
			    boton1.style = " padding: 5px 19px;   font-family: 'Oswald', sans-serif; color: #fff; background: #528FD5; position: relative ; left: -201px; top: 20px;";
			    archivos.appendChild(boton1);

			    boton2.setAttribute("type","button");
			    boton2.setAttribute("value","Pausar");
			    boton2.setAttribute("onclick" , "document.getElementById('videos').pause()");
			    boton2.style = " padding: 5px 19px;   font-family: 'Oswald', sans-serif; color: #fff; background: #528FD5; position: relative ; left: -202px; top: 20px";
			    archivos.appendChild(boton2);
			    
			    boton3.setAttribute("type","button");
			    boton3.setAttribute("value","Subir Volumen");
			    boton3.setAttribute("onclick" , "document.getElementById('videos').volume+=0.1");
			    boton3.style = " padding: 5px 22px;   font-family: 'Oswald', sans-serif; color: #fff; background: #528FD5; position: relative ; left: -202px; top: 20px";
			    archivos.appendChild(boton3);
			    
			    boton4.setAttribute("type","button");
			    boton4.setAttribute("value","Bajar Volumen");
			    boton4.setAttribute("onclick" , "document.getElementById('videos').volume-=0.1");
			    boton4.style = " padding: 5px 22px;   font-family: 'Oswald', sans-serif; color: #fff; background: #528FD5; position: relative ; left: -202px; top:20px";
			    archivos.appendChild(boton4);
		}
			
		if(formato == 'png' || formato == 'PNG' || formato == 'jpg' || formato == 'JPG'){
			var imgVer = new Image(); 
			imgVer.src = "ContenedorArchivos/"+file; 
	        imgVer.width="450";
	        imgVer.style = "position: relative ; left: 250px; ";

	        archivos.appendChild(imgVer);
	        
		}
			
	}
	
	function borrarArchivos(archivos){
		var email = document.getElementById("email").value;
		document.getElementById("div-archivos").remove();
		window.location.href="session.html?email="+email;
	}

	
	function borMedia(filename){
		var email = document.getElementById("email").value;
		var file = filename;	
	
		var formData = new FormData();
		formData.append('file',file);
		formData.append('email',email);

		 var init = {
			method: "POST",
			body: formData
		 }

		 fetch('https://murmuring-wildwood-16373.herokuapp.com/BorradorArchivos', init)
			 .then(response => response.json())
			 .then(data => {
				 if(data.status == 200){
					 alert(data.mensaje);
					 setTimeout(()=>{window.location.href="session.html?email="+email;},10)
				 }else{
					 alert("Error Al Subir El Archivo");
				 }			
			 })
			  .catch(error => console.error('AAAAA  Error:', error));
	}
	
	 function openPerfil(){
		 var email = document.getElementById("email").value;
	  	  window.open("https://murmuring-wildwood-16373.herokuapp.com/vistas/perfil.html?email=" + email, "_self");  		
	 }
	 
	
	function cerrarSesion(){
		var formData = new FormData();
		formData.append('mensaje','cerrar Sesion');

		 var init = {
			method: "POST",
			body: formData
		 }

		 fetch('https://murmuring-wildwood-16373.herokuapp.com/SessionUser', init)
			 .then(response => response.json())
			 .then(data => {
				 if(data.status == 200){
					 setTimeout(()=>{window.location.href="login.html";},100)
					 alert(data.mensaje);
				 }else{
					 alert(data.mensaje);
				 }			
			 })
			  .catch(error => console.error('AAAAA  Error:', error));
		
	}