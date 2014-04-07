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

        <title>Men&uacute;s</title>

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
                  <li class="active"><a href="../jsp/menus.jsp"><span class="glyphicon glyphicon-cutlery"></span> Men&uacute;s</a></li>
                  <li><a href="../jsp/platillos.jsp"><span class="glyphicon glyphicon-list-alt"></span> Platillos</a></li>
                  <li><a href="../jsp/ingredientes.jsp"><span class="glyphicon glyphicon-list"></span> Ingredientes</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </div>

        <div class="container">

            <div class="page-header">
                <h1>Men&uacute;s</h1>
            </div>
            <div class="row featurette">
            <div class="col-md-9">
                <div id="ListadeMenus">
                    <h3>Lista de Men&uacute;s</h3>
                    <table class="table table-hover table-striped table-condensed" id="ingredientes">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Descripci&oacute;n</th>
                                <th scope="col">Fecha de Inicio</th>
                                <th scope="col">Fecha de Finalizaci&oacute;n</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Almuerzos</td>
                                <td>Comidas para la hora del almuerzo</td>
                                <td>07-04-2014</td>
                                <td>12-04-2014</td>
                            </tr>
                            <tr>
                                <td>Asados</td>
                                <td>Comidas asadas</td>
                                <td>12-04-2014</td>
                                <td>14-04-2014</td>
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
                            <select  class="form-control" id="selectmenu" onChange="MenusSeleccionado()">
                                <option>Seleccione un Men&uacute;...</option>
                                <option>Almuerzos</option>
                                <option>Asados</option>
                            </select>
                    
                        </div>
                    </form>
                    <br>
                    <form id="Componentedeedicion" class="form-inline" role="form">
                        <div class="form-group">
                          <label class="sr-only" for="Name">Nombre</label>
                          <input type="text" class="form-control" id="Name" placeholder="Nombre del Men&uacute;">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="Descripcion">Descripci&oacute;n</label>
                            <input type="text" class="form-control" id="Descripcion" placeholder="Descripci&oacute;n">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="Fechainicio">Fecha de Inicio</label>
                            <input type="date" class="form-control" id="Fechainicio" placeholder="Fecha de Inicio" value="">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="Fechafinal">Fecha de Finalizaci&oacute;n</label>
                            <input type="date" class="form-control"  id="Fechafinal" placeholder="Fecha de Finalizaci&oacute;n">
                        </div>
                    </form>
                    <br>
                    <form role="form">
                        <div id="ListaPlatillos">
                            <h4>Platillos</h4>
                            <div class="col-md-offset-1"> 
                                    <div class="checkbox"><label><input type="checkbox" value="">Arroz con pollo</label></div>
                                    <div class="checkbox"><label><input type="checkbox" value="">Arroz con carne</label></div>
                                    <div class="checkbox"><label><input type="checkbox" value="">Pollo asado</label></div>
                                    <div class="checkbox"><label><input type="checkbox" value="">Carne asada</label></div>
                            </div>
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
                    <br>
                    <br>
                    <br>
                </div>
                <!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++/-->
                <div id="Agregar">
                    <h3>Agregar</h3>
                    <form class="form-inline" role="form">
                        <div class="form-group">
                          <label class="sr-only" for="adName">Nombre</label>
                          <input type="text" class="form-control" id="adName" placeholder="Nombre del Men&uacute;">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="adDescripcion">Descripci&oacute;n</label>
                            <input type="text" class="form-control" id="adDescripcion" placeholder="Descripci&oacute;n">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="adFechainicio">Fecha de Inicio</label>
                            <input type="date" class="form-control" id="adFechainicio" placeholder="Fecha de Inicio">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="adFechafinal">Fecha de Finalizaci&oacute;n</label>
                            <input type="date" class="form-control"  id="adFechafinal" placeholder="Fecha de Finalizaci&oacute;n">
                        </div>
                    </form>
                    <br>
                    <form role="form">
                        <div id="adListaPlatillos">
                            <h4>Platillos</h4>
                            <div class="col-md-offset-1"> 
                                    <div class="checkbox"><label><input type="checkbox" value="">Arroz con pollo</label></div>
                                    <div class="checkbox"><label><input type="checkbox" value="">Arroz con carne</label></div>
                                    <div class="checkbox"><label><input type="checkbox" value="">Pollo asado</label></div>
                                    <div class="checkbox"><label><input type="checkbox" value="">Carne asada</label></div>
                            </div>
                        
                            <div class="col-owner-offset-9">
                                <div class="btn-group btn-group-lg" >
                                    <button type="submit" data-loading-text="Guardando..." class="btn bg-primary" id="adsave"><span class="glyphicon glyphicon-save"></span> Guardar</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br>
                    <br>
                    <br>
                </div>
                
            </div>
            </div>
            <hr class="featurette-divider">
            <!-- FOOTER -->
            <footer>
              <p>&copy; 2014 La Cuchara Alegre. &middot; <a href="">Privacy</a> &middot; <a href="">Terms</a></p>
            </footer>
            
        </div><!-- /.container -->

            <!-- FOOTER -->
            
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../js/jquery.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/script-formulario-menus.js"></script>
  
    </body>
</html>
