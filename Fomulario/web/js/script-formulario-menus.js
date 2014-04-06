/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload=function(){
    hiddenElement("Editar");
    hiddenElement("Agregar");
    hiddenElement("BMCancelar");
};


function muestra(){
       document.getElementById("grupo").style.display="none";
       hiddenElement("");
       showElement("");
}




function MostrarEditar(){
       showElement("Editar");
       showElement("BMadd");
       showElement("BMCancelar");
       hiddenElement("BMedit");
       hiddenElement("Agregar");
       limpia();
}

function MostrarAgregar(){
       showElement("Agregar");
       showElement("BMedit");
       showElement("BMCancelar");
       hiddenElement("BMadd");
       hiddenElement("Editar");
}

function Cancelar(){
       showElement("BMedit");
       showElement("BMadd");
       limpia();
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
  if(seleccion.selectedIndex!=0){
      document.getElementById('Name').value=seleccion.options[seleccion.selectedIndex].text;
  }
  else{
      document.getElementById('Name').value="";
      document.getElementById('Caloria').value=0;
  }
  
}




document.getElementById("BMedit").addEventListener("click",MostrarEditar,false);
document.getElementById("BMadd").addEventListener("click",MostrarAgregar,false);
document.getElementById("BMCancelar").addEventListener("click",Cancelar,false);