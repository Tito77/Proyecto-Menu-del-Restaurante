/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload=function(){
    hiddenElement("Editar");
    hiddenElement("Agregar");
    hiddenElement("BMCancelar");
    obtienefecha("adFechainicio",1);
    obtienefecha("adFechafinal",0);
};

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
   showElement("adListaPlatillos");
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
  if(seleccion.selectedIndex!==0){
    //oculta los elementos de la seccion de editar
      Mostrarelementoseditar();
      document.getElementById('Name').value=seleccion.options[seleccion.selectedIndex].text;
  }
  else{
    //oculta los elementos de la seccion de editar
       Ocultarelementoseditar();
        limpia();
  }
  
}




document.getElementById("BMedit").addEventListener("click",MostrarEditar,false);
document.getElementById("BMadd").addEventListener("click",MostrarAgregar,false);
document.getElementById("BMCancelar").addEventListener("click",Cancelar,false);