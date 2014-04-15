/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var WSURL = "http://www.modern-door-542.appspot.com";

window.onload=function(){
    hiddenElement("Editar");
    hiddenElement("Agregar");
    hiddenElement("BMCancelar");
    getPlatillos();
};


function Params(strMenuOperation,strModulo)
{
  var strEXEC =  "EXECOP=" + strMenuOperation;
  var strMOD  =  "&MOD=" + strModulo;
  var strGPKEY = document.getElementById("keyPlatilloValue").value;
  var strGPNOM = document.getElementById("Name").value;
  var strGPPRE = document.getElementById("Precio").value;
  if(!$.isNumeric(strGPPRE)){alert("El Precio no tiene ningun valor numerico");return}
  strGPKEY != "" ? strGPKEY = "&GPKEY=" + strGPKEY : strGPKEY = "";
  strGPNOM != "" ? strGPNOM = "&GPNOM=" + strGPNOM : strGPNOM = "";
  strGPPRE != "" ? strGPPRE = "&GPPRE=" + strGPPRE : strGPPRE = "";

  return strEXEC + strMOD + strGPKEY + strGPNOM + strGPPRE;
}


function muestra(){
       document.getElementById("grupo").style.display="none";
       hiddenElement("");
       showElement("");
}

function Ocultarelementoseditar(){
    //oculta los elementos de la seccion de editar
       hiddenElement("edsave");
       hiddenElement("ederaser");
       hiddenElement("Name");
       hiddenElement("Precio");
       hiddenElement("Componentedeedicion");
       hiddenElement("ListaIngredientes");
}
function Mostrarelementoseditar(){
    //Muestra los elementos de la seccion de editar
       showElement("edsave");
       showElement("ederaser");
       showElement("Name");
       showElement("Precio");
       showElement("Componentedeedicion");
       showElement("ListaIngredientes");
}

function MostrarEditar(){
       showElement("Editar");
       showElement("BMadd");
       showElement("BMCancelar");
       hiddenElement("BMedit");
       hiddenElement("Agregar");
       Ocultarelementoseditar();
       limpia();
}

function MostrarAgregar(){
       showElement("Agregar");
       showElement("BMedit");
       showElement("BMCancelar");
       hiddenElement("BMadd");
       hiddenElement("Editar");
       Ocultarelementoseditar();
       limpia();
}


function limpia(){
    //limpia la seccion de editar
       document.getElementById('selectplatillos').selectedIndex=0;
       document.getElementById('Name').value="";
       document.getElementById('Precio').value=0;
    //limpia la seccion de agregar
       document.getElementById('adName').value="";
       document.getElementById('adPrecio').value=0;
}

function Cancelar(){
       showElement("BMedit");
       showElement("BMadd");
       limpia();
       hiddenElement("BMCancelar");
       hiddenElement("Editar");
       hiddenElement("Agregar");
       
}

function hiddenElement(id) {
   document.getElementById(id).style.display = "none";
}
 
function showElement(id) {
   document.getElementById(id).style.display = "block";
}

function PlatillosSeleccionado()
{
  var seleccion=document.getElementById('selectplatillos');
  var platilloKey = document.getElementById('selectplatillos').value;
  if(seleccion.selectedIndex!==0){
    //oculta los elementos de la seccion de editar
      Mostrarelementoseditar();
      document.getElementById('Name').value=seleccion.options[seleccion.selectedIndex].text;
      getPlatilloKey(platilloKey);
      getIngredientes();
      getIngredientesEnPlatillo(platilloKey)
      document.getElementById("keyPlatilloValue").value = platilloKey;
  }
  else{
    //oculta los elementos de la seccion de editar
       Ocultarelementoseditar();
      document.getElementById('Name').value="";
      document.getElementById('Precio').value=0;
  }
  
}


// Obtiene la lista de platillos completa
function getPlatillos()
{
  var xmlhttp;
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      var htmlList = "";
      var menuOptions = "<option>Seleccione un Platillo...</option>";
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      for (var i = mainJson.RETURNVALUE.length - 1; i >= 0; i--) {
        var menuJson = jQuery.parseJSON(mainJson.RETURNVALUE[i]);
        htmlList += "<tr><td>"+menuJson.mNombrePlatillo+"</td>";
        htmlList += "<td>"+menuJson.mPrecio+"</td>";
        menuOptions += "<option value='"+menuJson.mKeyValue+"'>"+menuJson.mNombrePlatillo+"</option>";
      }
      $( "#platillosListBody" ).html(htmlList);
      $( "#selectplatillos" ).html(menuOptions);
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GP",true);
  xmlhttp.send();
}

//Obtiene los datos de un platillo en especifico cuando se le da la llave del platillo que se quiere agregar
function getPlatilloKey(strPlatilloKey)
{
  var xmlhttp;
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      var menuJson = jQuery.parseJSON(mainJson.RETURNVALUE[0]);
      document.getElementById('Precio').value=menuJson.mPrecio;
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GP&GPKEY="+strPlatilloKey,true);
  xmlhttp.send();
}

// Obtiene la liesta de Platillos en existencia completa
function getIngredientes()
{
  var xmlhttp;
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      var htmlList = "<h4>Ingredientes</h4><div class='col-md-offset-1'>";
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      for (var i = mainJson.RETURNVALUE.length - 1; i >= 0; i--) {
        var ingredienteJson = jQuery.parseJSON(mainJson.RETURNVALUE[i]);
        htmlList += "<div class='checkbox'><label><input type='checkbox' value='"+ingredienteJson.mKeyValue+"'>"+ingredienteJson.mNombreIngrediente+"</label></div>";
      }
      $( "#ListaIngredientes" ).html(htmlList+"</div>");
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GI",true);
  xmlhttp.send();
}


//Pone check a aquellos platillos que estan en el menu
function getIngredientesEnPlatillo(strPlatilloKey)
{
  var xmlhttp;
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      var inputs = document.getElementsByTagName("input"); //or document.forms[0].elements;   
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      for (var i = 0; i < inputs.length; i++) {  
        if (inputs[i].type == "checkbox") {  
          for (var j = mainJson.RETURNVALUE.length - 1; j >= 0; j--) {
            var ingredienteJson = jQuery.parseJSON(mainJson.RETURNVALUE[j]);
            if(ingredienteJson.mKeyValue == inputs[i].value)
            {
              inputs[i].checked = true;
            }
          }
        }  
      }
    }    
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SIN&MOD=GP&GPKEY="+encodeURI(strPlatilloKey),true);
  xmlhttp.send();
}


function updatePlatillo()
{
  var parametros = Params("UPD","GP");
  // Ejecutar el update
  var xmlhttp;
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      activarIngredientesEnPlatillo();
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      if (mainJson.RETURNVALUE == "1"){ alert("Platillo actualizado exitosamente!"); }
      else if(mainJson.RETURNVALUE == "0"){ alert("Error al actualizar el platillo."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?" + parametros,true);
  xmlhttp.send();
}


function activarIngredientesEnPlatillo()
{
  var inputs = document.getElementsByTagName("input"); //or document.forms[0].elements;   
  for (var i = 0; i < inputs.length; i++) {  
    if (inputs[i].type == "checkbox") {  
      strOP = "";
      inputs[i].checked ? strOP = "AIN" : strOP = "BIN";
      var parametros = Params(strOP,"GP");
      // Ejecutar el update
      var xmlhttp;
      if (window.XMLHttpRequest)
      {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
      }
    else
      {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
      }
      xmlhttp.onreadystatechange=function()
      {
      if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
          var mainJson = jQuery.parseJSON(xmlhttp.responseText);
          if(main.Json.RETURNVALUE == "0"){ alert("Error al actualizar ingrediente en el platillo."); }
        }    
      }
      xmlhttp.open("GET",WSURL + "/?" + parametros + "&GIKEY=" + inputs[i].value ,true);
      xmlhttp.send();
    }  
  }
}

function insertPlatillo(){
  var parametros = "EXECOP=INS&MOD=GP";
  var strGPNOM = "&GPNOM=" + document.getElementById("adName").value;
  var strGPPRE = "&GPPRE=" + document.getElementById("adPrecio").value;
  if(!$.isNumeric(document.getElementById("adPrecio").value)){alert("El Precio no tiene ningun valor numerico");return}
  // Ejecutar la insercion
  var xmlhttp;
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      if (mainJson.RETURNVALUE == "1"){ alert("Platillo agregado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al agregar el platillo."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?"+ parametros + strGPNOM + strGPPRE ,true);
  xmlhttp.send();
}

function deletePlatillo()
{
  var parametros = Params("DEL","GP");
  // Ejecutar el update
  var xmlhttp;
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      if (mainJson.RETURNVALUE == "1"){ alert("Platillo borrado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al borrar el platillo."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?" + parametros,true);
  xmlhttp.send();
}


document.getElementById("BMedit").addEventListener("click",MostrarEditar,false);
document.getElementById("BMadd").addEventListener("click",MostrarAgregar,false);
document.getElementById("BMCancelar").addEventListener("click",Cancelar,false);