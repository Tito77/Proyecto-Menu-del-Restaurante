<%-- 
    Document   : index
    Created on : 05/04/2014, 04:09:13 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0050)http://getbootstrap.com/examples/starter-template/ -->
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="../img/Food_Icon_32.png">

    <title>Ingredientes</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/formulario-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>window["_GOOG_TRANS_EXT_VER"] = "1";</script></head>

    <body>

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
                <a class="navbar-brand" href="../index.jsp"><span class="glyphicon glyphicon-home"></span> Inicio</a>
            </div>
            <div class="collapse navbar-collapse">
              <ul class="nav navbar-nav">
                  <li><a href="../jsp/menus.jsp"><span class="glyphicon glyphicon-cutlery"></span> Men&uacute;s</a></li>
                  <li><a href="../jsp/platillos.jsp"><span class="glyphicon glyphicon-list-alt"></span> Platillos</a></li>
                  <li class="active"><a href="../jsp/ingredientes.jsp"><span class="glyphicon glyphicon-list"></span> Ingredientes</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </div>

        <div class="container">

            <div class="page-header">
              <h1>Ingredientes</h1>
            </div>
            <div class="row featurette">
            <div class="col-md-9">
                <div id="Listadeingredientes">
                    <h3>Lista de Ingredientes</h3>
                    <table class="table table-hover table-striped table-condensed" id="ingredientes">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Calor&iacute;as</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Chile</td>
                                <td>40</td>
                            </tr>
                            <tr>
                                <td>Carne</td>
                                <td>1050</td>
                            </tr>
                            <tr>
                                <td>Pollo</td>
                                <td>900</td>
                            </tr>
                            <tr>
                                <td>Arroz</td>
                                <td>300</td>
                            </tr>
                            <tr>
                                <td>Cebolla</td>
                                <td>10</td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="col-owner-offset-9">
                        <div class="btn-group btn-group-lg">
                            <button type="button" data-loading-text="Cargando..." class="btn bg-primary" id="BMedit"><span class="glyphicon glyphicon-edit"></span> Editar</button>
                        </div>
                        <div class="btn-group btn-group-lg">
                            <button type="button" data-loading-text="Cargando..." class="btn bg-primary" id="BMadd"><span class="glyphicon glyphicon-plus"></span> Agregar</button>
                        </div>
                        <div class="btn-group btn-group-lg">
                            <button type="button" data-loading-text="Cargando..." class="btn bg-primary" id="BMCancelar"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                        </div>
                    </div>
                </div>
                <!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++/-->
                <div id="Editar">
                    <h3>Editar</h3>
                    <form role="form">
                        <div class="form-group">
                            <select  class="form-control" id="selectingredientes" onChange="IngredienteSeleccionado()">
                                <option>Seleccione un Ingrediente...</option>
                                <option>Chile</option>
                                <option>Carne</option>
                                <option>Pollo</option>
                                <option>Arroz</option>
                                <option>Cebolla</option>
                            </select>
                    
                        </div>
                    </form>
                    <br>
                    <form class="form-inline" role="form">
                        <div class="form-group">
                          <label class="sr-only" for="Name">Nombre</label>
                          <input type="text" class="form-control" id="Name" placeholder="Nombre del Ingrediente">
                        </div>
                        <div class="form-group">
                          <label class="sr-only" for="Caloria">Calorias</label>
                          <input type="number" min="0" value="0" class="form-control" id="Caloria" placeholder="Calorias">
                        </div>
                        <div class="col-owner-offset-9">
                            <div class="btn-group btn-group-lg" >
                                <button type="submit" data-loading-text="Guardando..." class="btn bg-primary" id="edsave"><span class="glyphicon glyphicon-save"></span> Guardar</button>
                            </div>
                            <div class="btn-group btn-group-lg" >
                                <button type="submit" data-loading-text="Borrando..." class="btn bg-primary" id="ederaser"><span class="glyphicon glyphicon-trash"></span> Borrar</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++/-->
                <div id="Agregar">
                    <h3>Agregar</h3>
                    <form class="form-inline" role="form">
                        <div class="form-group">
                          <label class="sr-only" for="adName">Nombre</label>
                          <input type="text" class="form-control" id="adName" placeholder="Nombre del Ingrediente">
                        </div>
                        <div class="form-group">
                          <label class="sr-only" for="adCaloria">Calorias</label>
                          <input type="number" min="0" value="0" class="form-control" id="adCaloria" placeholder="Calorias">
                        </div>
                        <div class="col-owner-offset-9">
                            <div class="btn-group btn-group-lg" >
                                <button type="submit" data-loading-text="Guardando..." class="btn bg-primary" id="adsave"><span class="glyphicon glyphicon-save"></span> Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
                
            </div>
            </div>
            <hr class="featurette-divider">
            <!-- FOOTER -->
            <footer>
              <p>&copy; 2014 La Cuchara Alegre. &middot; <a href="">Privacy</a> &middot; <a href="">Terms</a></p>
            </footer>
        </div><!-- /.container -->
            
            

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../js/jquery.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/script-formulario-ingredientes.js"></script>
  
    </body>
</html>
