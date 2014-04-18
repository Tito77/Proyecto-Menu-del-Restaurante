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
import DMesa
import CMesa

#Gestion Mesa Control
class GMesaCtrl:
	def __init__(self,pRequest):
		self.mRequest = pRequest
		self.mOperation = ""
		self.mNumeroMesa = ""
		self.mEstadoRegistro = ""
		self.mTMPSTMP = ""
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
		if self.mOperation == Constantes.Constantes().mGTOperacionReservarMesa:
			self.Reservar()
		if self.mOperation == Constantes.Constantes().mGTOperacionDesocuparMesa:
			self.Desocupar()


	def Insert(self):
		self.mReturnValue = "0"
		dmesa = DMesa.DMesa()
		dmesa.mNumeroMesa = self.mRequest.get('GTNUM')
		dmesa.mEstadoRegistro = Constantes.Constantes().mEstadoLibre
		localtime = datetime.datetime.utcnow()
		localtime = localtime - datetime.timedelta(hours=6)
		dmesa.mTMPSTMP = localtime.strftime("%Y%m%d") + " " +localtime.time().strftime("%H%M%S")
		dmesa.put()
		self.mReturnValue = "1"
		

	def SelectMemcache(self):
		keyValue = str(self.mRequest.get('GTKEY'))
		if(keyValue == ""):
			self.Select()
		else:
			mesa = memcache.get(keyValue)
			if mesa is not None and mesa is not "":
				logging.debug('Requested Value From Memcache')
				self.mReturnValue = [JsonEncoder.JsonEncoder().serializeJson(pickle.loads(mesa))]
			else:
				self.Select()
				data = "";
				qry = DMesa.DMesa.query()
				for recMesa in qry:
					if keyValue != "":
						if str(recMesa.key.id()) == keyValue:
							data = pickle.dumps(CMesa.CMesa(str(recMesa.mNumeroMesa),str(recMesa.mEstadoRegistro),str(recMesa.mTMPSTMP),str(recMesa.key.id())))
				if not memcache.add(keyValue, data, 10):
					logging.error('Memcache set failed.')		


	def Select(self):
		lstMesas = []
		keyValue = str(self.mRequest.get('GTKEY'))
		qry = DMesa.DMesa.query()
		for recMesa in qry:
			if keyValue != "":
				if str(recMesa.key.id()) == keyValue:
					lstMesas.append(CMesa.CMesa(str(recMesa.mNumeroMesa),str(recMesa.mEstadoRegistro),str(recMesa.mTMPSTMP),str(recMesa.key.id())).jsonSerialize())
					data = pickle.dumps(CMesa.CMesa(str(recMesa.mNumeroMesa),str(recMesa.mEstadoRegistro),str(recMesa.mTMPSTMP),str(recMesa.key.id())))
					if not memcache.add(keyValue, data, 10):
						logging.error('Memcache set failed.')
			else :
				lstMesas.append(CMesa.CMesa(str(recMesa.mNumeroMesa),str(recMesa.mEstadoRegistro),str(recMesa.mTMPSTMP),str(recMesa.key.id())).jsonSerialize())
		self.mReturnValue = lstMesas

	def Update(self):
		# Obtener los parametros para poder actualizarlos
		self.mReturnValue = "0"
		numeroreturnValue = str(self.mRequest.get('GTNUM'))
		estadoreturnValue = str(self.mRequest.get('GTEST'))
		keyValue = str(self.mRequest.get('GTKEY'))
		qry = DMesa.DMesa.query()
		# Ejecutar el query
		if keyValue != "":
			for recMesa in qry:
				if str(recMesa.key.id()) == keyValue:					
					if numeroreturnValue != "":
						recMesa.mNumeroMesa = numeroreturnValue
					if estadoreturnValue != "":
						recMesa.mEstadoRegistro = estadoreturnValue
					recMesa.put()
					data = pickle.dumps(CMesa.CMesa(str(recMesa.mNumeroMesa),str(recMesa.mEstadoRegistro),str(recMesa.mTMPSTMP),str(recMesa.key.id())))
					memcache.set(keyValue, data, 10)
					self.mReturnValue = "1"
		

	def Delete(self):
		self.mReturnValue = "0"
		keyValue = str(self.mRequest.get('GTKEY'))
		qry = DMesa.DMesa.query()
		if keyValue != "":
			for recMesa in qry:
				if str(recMesa.key.id()) == keyValue:
					self.mReturnValue = "1"
					memcache.delete(keyValue, 10)
					recMesa.key.delete()

	def Reservar(self):
		self.mReturnValue = "0"
		keyValue = str(self.mRequest.get('GTKEY'))
		qry = DMesa.DMesa.query()
		if keyValue != "":
			for recMesa in qry:
				if str(recMesa.key.id()) == keyValue:
					recMesa.mEstadoRegistro = Constantes.Constantes().mEstadoOcupada
					localtime = datetime.datetime.utcnow()
					localtime = localtime - datetime.timedelta(hours=6)
					recMesa.mTMPSTMP = localtime.strftime("%Y%m%d") + " " +localtime.time().strftime("%H%M%S")
					recMesa.put()
					self.mReturnValue = "1"

	def Desocupar(self):
		self.mReturnValue = "0"
		keyValue = str(self.mRequest.get('GTKEY'))
		qry = DMesa.DMesa.query()
		if keyValue != "":
			for recMesa in qry:
				if str(recMesa.key.id()) == keyValue:
					recMesa.mEstadoRegistro = Constantes.Constantes().mEstadoLibre
					localtime = datetime.datetime.utcnow()
					localtime = localtime - datetime.timedelta(hours=6)
					recMesa.mTMPSTMP = localtime.strftime("%Y%m%d") + " " +localtime.time().strftime("%H%M%S")
					recMesa.put()
					self.mReturnValue = "1"
		

	def GetValue(self):
		return self.mReturnValue