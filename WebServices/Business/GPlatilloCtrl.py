# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Dataaccess'))
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'CommonEntities'))
from google.appengine.ext import ndb
import Constantes
import DPlatillo
import CPlatillo

#Gestion Platillo Control
class GPlatilloCtrl:
	def __init__(self,pRequest):
		self.mRequest = pRequest
		self.mOperation = ""
		self.mNombrePlatillo = ""
		self.mPrecio = ""
		self.mkeyValue = ""
		self.mReturnValue = []

	def Execute(self):
		self.mOperation = str(self.mRequest.get("EXECOP"))
		if self.mOperation == Constantes.Constantes().mOperacionSelect:
			self.Select()
		if self.mOperation == Constantes.Constantes().mOperacionInsert:
			self.Insert()
		if self.mOperation == Constantes.Constantes().mOperacionUpdate:
			self.Update()
		if self.mOperation == Constantes.Constantes().mOperacionDelete:
			self.Delete()


	def Insert(self):
		self.mReturnValue = "0"
		dplatillo = DPlatillo.DPlatillo()
		dplatillo.mNombrePlatillo = self.mRequest.get('GPNOM')
		dplatillo.mPrecio = self.mRequest.get('GPPRE')
		dplatillo.put()
		self.mReturnValue = "1"
		
		

	def Select(self):
		lstPlatillos = []
		keyValue = str(self.mRequest.get('GPKEY'))
		qry = DPlatillo.DPlatillo.query()
		for recPlatillo in qry:
			if keyValue != "":
				if str(recPlatillo.key.id()) == keyValue:
					lstPlatillos.append(CPlatillo.CPlatillo(str(recPlatillo.mNombrePlatillo),str(recPlatillo.mPrecio),str(recPlatillo.key.id())).jsonSerialize())
			else :
				lstPlatillos.append(CPlatillo.CPlatillo(str(recPlatillo.mNombrePlatillo),str(recPlatillo.mPrecio),str(recPlatillo.key.id())).jsonSerialize())
		self.mReturnValue = lstPlatillos

	def Update(self):
		# Obtener los parametros para poder actualizarlos
		self.mReturnValue = "0"
		nombrereturnValue = str(self.mRequest.get('GPNOM'))
		caloriareturnValue = str(self.mRequest.get('GPPRE'))
		keyValue = str(self.mRequest.get('GPKEY'))
		qry = DPlatillo.DPlatillo.query()
		# Ejecutar el query
		if keyValue != "":
			for recPlatillo in qry:
				if str(recPlatillo.key.id()) == keyValue:					
					if nombrereturnValue != "":
						recPlatillo.mNombrePlatillo = nombrereturnValue
					if caloriareturnValue != "":
						recPlatillo.mPrecio = caloriareturnValue
					recPlatillo.put()
					self.mReturnValue = "1"
		

	def Delete(self):
		self.mReturnValue = "0"
		keyValue = str(self.mRequest.get('GPKEY'))
		qry = DPlatillo.DPlatillo.query()
		if keyValue != "":
			for recPlatillo in qry:
				if str(recPlatillo.key.id()) == keyValue:
					self.mReturnValue = "1"
					recPlatillo.key.delete()
		

	def GetValue(self):
		return self.mReturnValue