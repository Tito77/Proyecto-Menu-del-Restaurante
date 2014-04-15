/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 var WSURL = "http://www.modern-door-542.appspot.com";

window.onload=function(){
    hiddenElement("Editar");
    hiddenElement("Agregar");
    hiddenElement("BMCancelar");
    getIngredientes();
};


function Params(strMenuOperation,strModulo)
{
  var strEXEC =  "EXECOP=" + strMenuOperation;
  var strMOD  =  "&MOD=" + strModulo;
  var strGIKEY = document.getElementById("keyIngredienteValue").value;
  var strGINOM = document.getElementById("Name").value;
  var strGICAL = document.getElementById("Caloria").value;
  if(!$.isNumeric(strGICAL)){alert("Las calorias no tienen ningun valor numerico.");return}
  strGIKEY != "" ? strGIKEY = "&GIKEY=" + strGIKEY : strGIKEY = "";
  strGINOM != "" ? strGINOM = "&GINOM=" + strGINOM : strGINOM = "";
  strGICAL != "" ? strGICAL = "&GICAL=" + strGICAL : strGICAL = "";

  return strEXEC + strMOD + strGIKEY + strGINOM + strGICAL;
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
       hiddenElement("Caloria");
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

function Cancelar(){
       showElement("BMedit");
       showElement("BMadd");
       limpia();
       //oculta los elementos de la seccion de editar
       Ocultarelementoseditar();
       hiddenElement("BMCancelar");
       hiddenElement("Editar");
       hiddenElement("Agregar");
       
}
function limpia(){
    //limpia la seccion de editar
       document.getElementById('selectingredientes').selectedIndex=0;
       document.getElementById('Name').value="";
       document.getElementById('Caloria').value=0;
    //limpia la seccion de agregar
       document.getElementById('adName').value="";
       document.getElementById('adCaloria').value=0;
}

function hiddenElement(id) {
   document.getElementById(id).style.display = "none";
}
 
function showElement(id) {
   document.getElementById(id).style.display = "block";
}

function IngredienteSeleccionado()
{
  var seleccion=document.getElementById('selectingredientes');
  var ingredienteKey = document.getElementById('selectingredientes').value;
  if(seleccion.selectedIndex!==0){
    //oculta los elementos de la seccion de editar
       showElement("edsave");
       showElement("ederaser");
       showElement("Name");
       showElement("Caloria");
       document.getElementById('Name').value=seleccion.options[seleccion.selectedIndex].text;
       getIngredienteKey(ingredienteKey);
       document.getElementById("keyIngredienteValue").value = ingredienteKey;
  }
  else{
    //oculta los elementos de la seccion de editar
       Ocultarelementoseditar();
      document.getElementById('Name').value="";
      document.getElementById('Caloria').value=0;
  }
  
}

// Obtiene la lista de ingredientes completa
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
      var htmlList = "";
      var menuOptions = "<option>Seleccione un Ingrediente...</option>";
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      for (var i = mainJson.RETURNVALUE.length - 1; i >= 0; i--) {
        var ingredienteJson = jQuery.parseJSON(mainJson.RETURNVALUE[i]);
        htmlList += "<tr><td>"+ingredienteJson.mNombreIngrediente+"</td>";
        htmlList += "<td>"+ingredienteJson.mCalorias+"</td>";
        menuOptions += "<option value='"+ingredienteJson.mKeyValue+"'>"+ingredienteJson.mNombreIngrediente+"</option>";
      }
      $( "#ingredientesListBody" ).html(htmlList);
      $( "#selectingredientes" ).html(menuOptions);
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GI",true);
  xmlhttp.send();
}

//Obtiene los datos de un ingrediente en especifico cuando se le da la llave del ingrediente que se quiere agregar
function getIngredienteKey(strIngredienteKey)
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
      var ingredienteJson = jQuery.parseJSON(mainJson.RETURNVALUE[0]);
      document.getElementById('Caloria').value=ingredienteJson.mCalorias;
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GI&GIKEY="+strIngredienteKey,true);
  xmlhttp.send();
}

function updateIngrediente()
{
  var parametros = Params("UPD","GI");
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
      if (mainJson.RETURNVALUE == "1"){ alert("Ingrediente actualizado exitosamente!"); }
      else if(mainJson.RETURNVALUE == "0"){ alert("Error al actualizar el ingrediente."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?" + parametros,true);
  xmlhttp.send();
}

function insertIngrediente(){
  var parametros = "EXECOP=INS&MOD=GI";
  var strGINOM = "&GINOM=" + document.getElementById("adName").value;
  var strGICAL = "&GICAL=" + document.getElementById("adCaloria").value;
  if(!$.isNumeric(document.getElementById("adCaloria").value)){alert("El Precio no tiene ningun valor numerico");return}
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
      if (mainJson.RETURNVALUE == "1"){ alert("Ingrediente agregado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al agregar el ingrediente."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?"+ parametros + strGINOM + strGICAL ,true);
  xmlhttp.send();
}


function deleteIngrediente()
{
  var parametros = Params("DEL","GI");
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
      if (mainJson.RETURNVALUE == "1"){ alert("Ingrediente borrado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al borrar el ingrediente."); }
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