 /* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var WSURL = "http://www.modern-door-542.appspot.com";

window.onload=function(){
    hiddenElement("rqstTest");
    hiddenElement("Editar");
    hiddenElement("Agregar");
    hiddenElement("BMCancelar");
    obtienefecha("adFechainicio",1);
    obtienefecha("adFechafinal",0);
    getMenus();
};

function Params(strMenuOperation,strModulo)
{
  var strEXEC =  "EXECOP=" + strMenuOperation;
  var strMOD  =  "&MOD=" + strModulo;
  var strGMKEY = document.getElementById("keyMenuValue").value;
  var strGMNOM = document.getElementById("Name").value;
  var strGMDSC = document.getElementById("Descripcion").value.replace("-","").replace("-","");
  var strGMFIA = document.getElementById("Fechainicio").value.replace("-","").replace("-","");
  var strGMFFA = document.getElementById("Fechafinal").value.replace("-","").replace("-","");
  strGMKEY != "" ? strGMKEY = "&GMKEY=" + strGMKEY : strGMKEY = "";
  strGMNOM != "" ? strGMNOM = "&GMNOM=" + strGMNOM : strGMNOM = "";
  strGMDSC != "" ? strGMDSC = "&GMDSC=" + strGMDSC : strGMDSC = "";
  strGMFIA != "" ? strGMFIA = "&GMFIA=" + strGMFIA : strGMFIA = "";
  strGMFFA != "" ? strGMFFA = "&GMFFA=" + strGMFFA : strGMFFA = "";

  return strEXEC + strMOD + strGMKEY + strGMNOM + strGMDSC + strGMFIA + strGMFFA;
}

function obtienefecha(id,val) {
    var now = new Date();
    var month = (now.getMonth() + 1);               
    var day = now.getDate();
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    var today = now.getFullYear() + '-' + month + '-' + day;
    document.getElementById(id).value=today;
    if(val===0)
        document.getElementById(id).min=today;
        
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
       hiddenElement("Descripcion");
       hiddenElement("Fechainicio");
       hiddenElement("Fechafinal");
       hiddenElement("Componentedeedicion");
       hiddenElement("ListaPlatillos");
}
function Mostrarelementoseditar(){
    //Muestra los elementos de la seccion de editar
       showElement("edsave");
       showElement("ederaser");
       showElement("Name");
       showElement("Descripcion");
       showElement("Fechainicio");
       showElement("Fechafinal");
       showElement("Componentedeedicion");
       showElement("ListaPlatillos");
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
       Mostrarelementosagregar();
       limpia();
}


function Ocultarelementosagregar(){
   hiddenElement('adName');
   hiddenElement('adDescripcion');
   hiddenElement('adFechainicio');
   hiddenElement('adFechafinal');
   hiddenElement("adListaPlatillos");
}

function Mostrarelementosagregar(){
   showElement('adName');
   showElement('adDescripcion');
   showElement('adFechainicio');
   showElement('adFechafinal');
}


function limpia(){
    //limpia la seccion de editar
       document.getElementById('selectmenu').selectedIndex=0;
       document.getElementById('Name').value="";
       document.getElementById('Descripcion').value="";
       document.getElementById('Fechainicio').value="";
       document.getElementById('Fechafinal').value="";
    //limpia la seccion de agregar
       document.getElementById('adName').value="";
       document.getElementById('adDescripcion').value="";
       obtienefecha("adFechainicio",1);
       obtienefecha("adFechafinal",0);
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

function MenusSeleccionado()
{
  var seleccion=document.getElementById('selectmenu');
  var menuKey = document.getElementById('selectmenu').value;
  if(seleccion.selectedIndex!==0){
    //oculta los elementos de la seccion de editar
      Mostrarelementoseditar();
      document.getElementById('Name').value=seleccion.options[seleccion.selectedIndex].text;
      getMenuKey(menuKey);
      getPlatillos();
      getPlatillosEnMenu(menuKey);
      document.getElementById("keyMenuValue").value = menuKey;
  }
  else{
    //oculta los elementos de la seccion de editar
       Ocultarelementoseditar();
        limpia();
  }
  
}

// Obtiene la lista de menus completa
function getMenus()
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
      var menuOptions = "<option>Seleccione un Men&uacute;...</option>";
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      for (var i = mainJson.RETURNVALUE.length - 1; i >= 0; i--) {
        var menuJson = jQuery.parseJSON(mainJson.RETURNVALUE[i]);
        htmlList += "<tr><td>"+menuJson.mNombreMenu+"</td>";
        htmlList += "<td>"+menuJson.mDescripcion+"</td>";
        htmlList += "<td>"+getDay(menuJson.mFechaInicioAplicacion)+"/"+getMonth(menuJson.mFechaInicioAplicacion)+"/"+getYears(menuJson.mFechaInicioAplicacion)+"</td>";
        htmlList += "<td>"+getDay(menuJson.mFechaFinalAplicacion)+"/"+getMonth(menuJson.mFechaFinalAplicacion)+"/"+getYears(menuJson.mFechaFinalAplicacion)+"</td>";
        if(menuJson.mEstadoAplicacion == "A"){
          htmlList += "<td>Activo</td></tr>";
        } else if (menuJson.mEstadoAplicacion == "I"){
          htmlList += "<td>Inactivo</td></tr>"
        } else {
          htmlList += "<td>Estado No Conodido</td></tr>"
        }
        menuOptions += "<option value='"+menuJson.mKeyValue+"'>"+menuJson.mNombreMenu+"</option>";
      }
      $( "#menuListBody" ).html(htmlList);
      $( "#selectmenu" ).html(menuOptions);
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GM",true);
  xmlhttp.send();
}



//Obtiene los datos de un menu en especifico cuando se le da la llave del menu que se quiere agregar
function getMenuKey(strMenuKey)
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
      document.getElementById('Descripcion').value=menuJson.mDescripcion;
      if(menuJson.mEstadoAplicacion == "A"){
          document.getElementById("estadoMenuActivo").checked = true;
        } else if (menuJson.mEstadoAplicacion == "I"){
          document.getElementById("estadoMenuActivo").checked = false;
        } else {
          alert("Estado de Activacion desconocido.");
          document.getElementById("estadoMenuActivo").checked = false;
        }
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GM&GMKEY="+strMenuKey,true);
  xmlhttp.send();
}

// Obtiene la liesta de Platillos en existencia completa
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
      var htmlList = "<h4>Platillos</h4><div class='col-md-offset-1'>";
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      for (var i = mainJson.RETURNVALUE.length - 1; i >= 0; i--) {
        var platilloJson = jQuery.parseJSON(mainJson.RETURNVALUE[i]);
        htmlList += "<div class='checkbox'><label><input type='checkbox' value='"+platilloJson.mKeyValue+"'>"+platilloJson.mNombrePlatillo+"</label></div>";
      }
      $( "#ListaPlatillos" ).html(htmlList+"</div>");
    }
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SEL&MOD=GP",true);
  xmlhttp.send();
}


//Pone check a aquellos platillos que estan en el menu
function getPlatillosEnMenu(strMenuKey)
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
        if (inputs[i].type == "checkbox" && inputs[i].id != "estadoMenuActivo") {  
          for (var j = mainJson.RETURNVALUE.length - 1; j >= 0; j--) {
            var platilloJson = jQuery.parseJSON(mainJson.RETURNVALUE[j]);
            if(platilloJson.mKeyValue == inputs[i].value)
            {
              inputs[i].checked = true;
            }
          }
        }  
      }
    }    
  }
  xmlhttp.open("GET",WSURL + "/?EXECOP=SPL&MOD=GM&GMKEY="+encodeURI(strMenuKey),true);
  xmlhttp.send();
  /*xmlhttp.open("PUT",WSURL + "",true);
  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
  xmlhttp.send("EXECOP=SPL&MOD=GM&GMKEY="+encodeURI(strMenuKey));

  $.ajax({
    type: 'PUT',
    url: 'http://www.modern-door-542.appspot.com',
    data: "EXECOP="+encodeURI("SPL")+"&MOD="+encodeURI("GM")+"&GMKEY="+encodeURI(strMenuKey),
    async: false,
    cache: true,
    jsonpCallback: 'jsonCallback',
    contentType: "application/json",
    dataType: 'jsonp',
    crossDomain: true,
    success: function(data)
    {
      alert("Success!");
    },
    error: function(e)
    {
       alert(e.message);
    }
  });*/
}

function updateMenu()
{
  var parametros = Params("UPD","GM");
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
      activateMenu();
      activarPlatillosEnMenu();
      var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      if (mainJson.RETURNVALUE == "1"){ alert("Menu Actualizado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al actualizar el menu."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?" + parametros,true);
  xmlhttp.send();
}

function activateMenu()
{
  var parametros = "";
  if(document.getElementById("estadoMenuActivo").checked){
    parametros = Params("ACT","GM");
  } else {
    parametros = Params("DAC","GM");
  }
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
      //Verificar en caso de cualquier error
      /*var mainJson = jQuery.parseJSON(xmlhttp.responseText);
      if (mainJson.RETURNVALUE == "1"){ alert("Menu Actualizado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al actualizar el menu."); }
      else { alert("Operacion desconocida por el servidor."); }*/
    }    
  }
  xmlhttp.open("GET",WSURL + "/?" + parametros,true);
  xmlhttp.send();
}

function activarPlatillosEnMenu()
{
  var inputs = document.getElementsByTagName("input"); //or document.forms[0].elements;   
  for (var i = 0; i < inputs.length; i++) {  
    if (inputs[i].type == "checkbox" && inputs[i].id != "estadoMenuActivo") {  
      strOP = "";
      inputs[i].checked ? strOP = "APL" : strOP = "BPL";
      var parametros = Params(strOP,"GM");
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
          if(main.Json.RETURNVALUE == "0"){ alert("Error al actualizar platillo el menu."); }
        }    
      }
      xmlhttp.open("GET",WSURL + "/?" + parametros + "&GPKEY=" + inputs[i].value ,true);
      xmlhttp.send();
    }  
  }
}

function insertMenu(){
  var parametros = "EXECOP=INS&MOD=GM";
  var strGMNOM = "&GMNOM=" + document.getElementById("adName").value;
  var strGMDSC = "&GMDSC=" + document.getElementById("adDescripcion").value;
  var strGMFIA = "&GMFIA=" + document.getElementById("adFechainicio").value.replace("-","").replace("-","");
  var strGMFFA = "&GMFFA=" + document.getElementById("adFechafinal").value.replace("-","").replace("-","");
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
      if (mainJson.RETURNVALUE == "1"){ alert("Menu agregado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al agregar el menu."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?"+ parametros + strGMNOM + strGMDSC + strGMFIA + strGMFFA,true);
  xmlhttp.send();
}

function deleteMenu()
{
  var parametros = Params("DEL","GM");
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
      if (mainJson.RETURNVALUE == "1"){ alert("Menu borrado exitosamente!"); }
      else if(main.Json.RETURNVALUE == "0"){ alert("Error al borrar el menu."); }
      else { alert("Operacion desconocida por el servidor."); }
      window.location.reload()
    }    
  }
  xmlhttp.open("GET",WSURL + "/?" + parametros,true);
  xmlhttp.send();
}

function getYears(strFecha)
{
  return strFecha.substring(0,4);
}

function getMonth(strFecha)
{
  return strFecha.substring(4,6);
}

function getDay(strFecha)
{
  return strFecha.substring(6,8);
}




document.getElementById("BMedit").addEventListener("click",MostrarEditar,false);
document.getElementById("BMadd").addEventListener("click",MostrarAgregar,false);
document.getElementById("BMCancelar").addEventListener("click",Cancelar,false);