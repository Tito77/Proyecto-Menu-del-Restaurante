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
  if(seleccion.selectedIndex!==0){
    //oculta los elementos de la seccion de editar
      Mostrarelementoseditar();
      document.getElementById('Name').value=seleccion.options[seleccion.selectedIndex].text;
  }
  else{
    //oculta los elementos de la seccion de editar
       Ocultarelementoseditar();
      document.getElementById('Name').value="";
      document.getElementById('Precio').value=0;
  }
  
}




document.getElementById("BMedit").addEventListener("click",MostrarEditar,false);
document.getElementById("BMadd").addEventListener("click",MostrarAgregar,false);
document.getElementById("BMCancelar").addEventListener("click",Cancelar,false);