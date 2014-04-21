# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

class Constantes:
	mOperacionSelect="SEL"
	mOperacionInsert="INS"
	mOperacionUpdate="UPD"
	mOperacionDelete="DEL"

	#Modulos
	mModuleMesa  = "GT"
	mModuleOrden = "GO"

	#ModuloMesa
	mGTOperacionReservarMesa  = "RME"
	mGTOperacionDesocuparMesa = "DME"
	mEstadoLibre   = "L"
	mEstadoOcupada = "O"

	#ModuloOrden
	mGOEstadoServico    = "S"
	mGOEstadoCompleto   = "C"
	mGOEstadoIncompleto = "I"
	mGOEstadoCancelado  = "N"
	mGOEstadoPagado 	= "P"

	mGOOperacionServido    = "SOR"
	mGOOperacionCompleto   = "COR"
	mGOOperacionIncompleto = "IOR"
	mGOOperacionCancelado  = "NOR"
	mGOOperacionPagado	   = "POR"

	mGOOperacionAgregarPlatillo     = "APL"
	mGOOperacionBorrarPlatillo      = "BPL"
	mGOOperacionSeleccionarPlatillo = "SPL"

	mGOOperacionOrdenesDia = "SOD"
	mGOOperacionOrdenesPorEstado = "SPE"