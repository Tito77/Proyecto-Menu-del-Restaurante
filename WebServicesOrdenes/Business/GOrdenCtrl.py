# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
import pickle
import logging
import json
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Dataaccess'))
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'CommonEntities'))
import datetime
from google.appengine.ext import ndb
from google.appengine.api import memcache
import JsonEncoder
import Constantes
import DOrden
import DPlatilloXOrden
import COrden
import CPlatilloXOrden

#Gestion Orden Control
class GOrdenCtrl:
	def __init__(self,pRequest):
		self.mRequest = pRequest
		self.mOperation = ""
		self.mTotal = ""
		self.mEstadoServido = ""
		self.mTMPSTMP = ""
		self.mKeyMesa = ""
		self.mkeyValue = ""
		self.mReturnValue = []

	def Execute(self):
		self.mOperation = str(self.mRequest.get("EXECOP"))
		if self.mOperation == Constantes.Constantes().mOperacionSelect:
			self.SelectMemcache()
		if self.mOperation == Constantes.Constantes().mOperacionInsert:
			self.Insert()
		if self.mOperation == Constantes.Constantes().mOperacionUpdate:
			self.Update()
		if self.mOperation == Constantes.Constantes().mOperacionDelete:
			self.Delete()
		if self.mOperation == Constantes.Constantes().mGOOperacionServido:
			self.CambiarEstado(Constantes.Constantes().mGOEstadoServico)
		if self.mOperation == Constantes.Constantes().mGOOperacionCompleto:
			self.CambiarEstado(Constantes.Constantes().mGOEstadoCompleto)
		if self.mOperation == Constantes.Constantes().mGOOperacionIncompleto:
			self.CambiarEstado(Constantes.Constantes().mGOEstadoIncompleto)
		if self.mOperation == Constantes.Constantes().mGOOperacionCancelado:
			self.CambiarEstado(Constantes.Constantes().mGOEstadoCancelado)
		if self.mOperation == Constantes.Constantes().mGOOperacionPagado:
			self.CambiarEstado(Constantes.Constantes.mGOEstadoPagado)
		if self.mOperation == Constantes.Constantes().mGOOperacionAgregarPlatillo:
			self.AgregarPlatillo()
		if self.mOperation == Constantes.Constantes().mGOOperacionBorrarPlatillo:
			self.BorrarPlatillo()
		if self.mOperation == Constantes.Constantes().mGOOperacionSeleccionarPlatillo:
			self.SeleccionarPlatillos()
		if self.mOperation == Constantes.Constantes().mGOOperacionOrdenesDia:
			self.SeleccionarOrdenesDelDia()
		if self.mOperation == Constantes.Constantes().mGOOperacionOrdenesPorEstado:
			self.SeleccionarOrdenesPorEstado()


	def Insert(self):
		self.mReturnValue = "0"
		dorden = DOrden.DOrden()
		dorden.mTotal = self.mRequest.get('GOTOT')
		dorden.mKeyMesa = str(self.mRequest.get('GTKEY'))
		localtime = datetime.datetime.utcnow()
		localtime = localtime - datetime.timedelta(hours=6)
		dorden.mTMPSTMP = localtime.strftime("%Y%m%d") + " " +localtime.time().strftime("%H%M%S")
		dorden.mEstadoServido = Constantes.Constantes().mGOEstadoIncompleto
		dorden.put()
		if dorden.key.id() != None and dorden.key.id() != "":
			self.mReturnValue = dorden.key.id()
		else:
			self.mReturnValue = "0"
		
	def SelectMemcache(self):
		keyValue = str(self.mRequest.get('GOKEY'))
		if(keyValue == ""):
			self.Select()
		else:
			orden = memcache.get(keyValue)
			if orden is not None and orden is not "":
				logging.debug('Requested Value From Memcache')
				self.mReturnValue = [JsonEncoder.JsonEncoder().serializeJson(pickle.loads(orden))]
			else:
				self.Select()
				data = "";
				qry = DOrden.DOrden.query()
				for recOrden in qry:
					if keyValue != "":
						if str(recOrden.key.id()) == keyValue:
							data = pickle.dumps(COrden.COrden(str(recOrden.mTotal),str(recOrden.mEstadoServido),str(recOrden.mTMPSTMP),str(recOrden.mKeyMesa),str(recOrden.key.id())))
				if not memcache.add(keyValue, data, 10):
					logging.error('Memcache set failed.')


	def Select(self):
		lstOrdens = []
		keyValue = str(self.mRequest.get('GOKEY'))
		qry = DOrden.DOrden.query()
		for recOrden in qry:
			if keyValue != "":
				if str(recOrden.key.id()) == keyValue:
					lstOrdens.append(COrden.COrden(str(recOrden.mTotal),str(recOrden.mEstadoServido),str(recOrden.mTMPSTMP),str(recOrden.mKeyMesa),str(recOrden.key.id())).jsonSerialize())
					data = pickle.dumps(COrden.COrden(str(recOrden.mTotal),str(recOrden.mEstadoServido),str(recOrden.mTMPSTMP),str(recOrden.mKeyMesa),str(recOrden.key.id())))
					if not memcache.add(keyValue, data, 10):
						logging.error('Memcache set failed.')
			else :
				lstOrdens.append(COrden.COrden(str(recOrden.mTotal),str(recOrden.mEstadoServido),str(recOrden.mTMPSTMP),str(recOrden.mKeyMesa),str(recOrden.key.id())).jsonSerialize())
		self.mReturnValue = lstOrdens

	def Update(self):
		# Obtener los parametros para poder actualizarlos
		self.mReturnValue = "0"
		total = str(self.mRequest.get('GOTOT'))
		keyMesa = str(self.mRequest.get('GTKEY'))
		keyValue = str(self.mRequest.get('GOKEY'))
		qry = DOrden.DOrden.query()
		# Ejecutar el query
		if keyValue != "":
			for recOrden in qry:
				if str(recOrden.key.id()) == keyValue:					
					if total != "":
						recOrden.mTotal = total
					if keyMesa != "":
						recOrden.mKeyMesa = keyMesa
					recOrden.put()
					data = pickle.dumps(COrden.COrden(str(recOrden.mTotal),str(recOrden.mEstadoServido),str(recOrden.mTMPSTMP),str(recOrden.mKeyMesa),str(recOrden.key.id())))
					memcache.set(keyValue, data, 10)
					self.mReturnValue = "1"
		

	def Delete(self):
		self.mReturnValue = "0"
		keyValue = str(self.mRequest.get('GOKEY'))
		qry = DOrden.DOrden.query()
		if keyValue != "":
			for recOrden in qry:
				if str(recOrden.key.id()) == keyValue:
					self.mReturnValue = "1"
					memcache.delete(keyValue, 10)
					recOrden.key.delete()
	

	def CambiarEstado(self,strEstadoServido=""):
		self.mReturnValue = "0"
		keyValue = str(self.mRequest.get('GOKEY'))
		qry = DOrden.DOrden.query()
		if keyValue != "":
			for recOrden in qry:
				if str(recOrden.key.id()) == keyValue:
					recOrden.mEstadoServido = strEstadoServido
					recOrden.put()
					self.mReturnValue = "1"

#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	def AgregarPlatillo(self):
		self.mReturnValue = "0"
		keyOrdenValue = str(self.mRequest.get('GOKEY'))
		keyPlatilloValue = str(self.mRequest.get('GPKEY'))
		cantidad = str(self.mRequest.get('GPCAN'))
		notaEspecial = str(self.mRequest.get('GONES'))
		notaPromocion = str(self.mRequest.get('GONPR'))
		dplaxorden = DPlatilloXOrden.DPlatilloXOrden()
		dplaxorden.mKeyOrden = keyOrdenValue
		dplaxorden.mKeyPlatillo = keyPlatilloValue
		dplaxorden.mCantidad = cantidad
		dplaxorden.mNotaEspecial = notaEspecial
		dplaxorden.mNotaPromocion = notaPromocion
		dplaxorden.put()
		if dplaxorden.key.id() != None and dplaxorden.key.id() != "":
			self.mReturnValue = "1"
		else:
			self.mReturnValue = "0"

	def BorrarPlatillo(self):
		self.mReturnValue = "0"
		keyOrdenValue = str(self.mRequest.get('GOKEY'))
		keyPlatilloValue = str(self.mRequest.get('GPKEY'))
		qry = DPlatilloXOrden.DPlatilloXOrden.query()
		if keyOrdenValue != "" and keyPlatilloValue != "":
			for recOrden in qry:
				if str(recOrden.mKeyOrden) == keyOrdenValue and str(recOrden.mKeyPlatillo) == keyPlatilloValue:
					self.mReturnValue = "1"
					recOrden.key.delete()

	def SeleccionarPlatillos(self):
		self.mReturnValue = "0"
		lstPlatillos = []
		keyOrdenValue = str(self.mRequest.get('GOKEY'))
		qryOrden = DPlatilloXOrden.DPlatilloXOrden.query()
		for recOrden in qryOrden:
			if str(recOrden.mKeyOrden) == keyOrdenValue:					
				lstPlatillos.append(CPlatilloXOrden.CPlatilloXOrden(str(recOrden.mKeyOrden),str(recOrden.mKeyPlatillo),str(recOrden.mCantidad),str(recOrden.mNotaPromocion),str(recOrden.mNotaEspecial)).jsonSerialize())
		self.mReturnValue = lstPlatillos


	def SeleccionarOrdenesDelDia(self):
		lstOrdens = []
		keyValue = str(self.mRequest.get('GOKEY'))
		qry = DOrden.DOrden.query()
		for recOrden in qry:
			tmpstmp = datetime.datetime.strptime(recOrden.mTMPSTMP, "%Y%m%d %H%M%S")
			localtime = datetime.datetime.utcnow()
			localtime = localtime - datetime.timedelta(hours=6)
			logging.debug(tmpstmp)
			if tmpstmp.date().strftime("%Y%m%d") == localtime.strftime("%Y%m%d"):
				lstOrdens.append(COrden.COrden(str(recOrden.mTotal),str(recOrden.mEstadoServido),str(recOrden.mTMPSTMP),str(recOrden.mKeyMesa),str(recOrden.key.id())).jsonSerialize())
		self.mReturnValue = lstOrdens

	def SeleccionarOrdenesPorEstado(self):
		lstOrdens = []
		keyValue = str(self.mRequest.get('GOKEY'))
		estadoValue = str(self.mRequest.get('GOEST'))
		qry = DOrden.DOrden.query()
		for recOrden in qry:
			tmpstmp = datetime.datetime.strptime(recOrden.mTMPSTMP, "%Y%m%d %H%M%S")
			localtime = datetime.datetime.utcnow()
			localtime = localtime - datetime.timedelta(hours=6)
			logging.debug(tmpstmp)
			if tmpstmp.date().strftime("%Y%m%d") == localtime.strftime("%Y%m%d") and str(recOrden.mEstadoServido) == estadoValue:
				lstOrdens.append(COrden.COrden(str(recOrden.mTotal),str(recOrden.mEstadoServido),str(recOrden.mTMPSTMP),str(recOrden.mKeyMesa),str(recOrden.key.id())).jsonSerialize())
		self.mReturnValue = lstOrdens


	def GetValue(self):
		return self.mReturnValue