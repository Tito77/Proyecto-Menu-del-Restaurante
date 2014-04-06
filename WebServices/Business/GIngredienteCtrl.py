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
import DIngrediente
import CIngrediente

#Gestion Ingrediente Control
class GIngredienteCtrl:
	def __init__(self,pRequest):
		self.mRequest = pRequest
		self.mOperation = ""
		self.mNombreIngrediente = ""
		self.mCalorias = ""
		self.mkeyValue = ""
		self.mReturnValue = ""

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
		dingrediente = DIngrediente.DIngrediente()
		dingrediente.mNombreIngrediente = self.mRequest.get('GINOM')
		dingrediente.mCalorias = self.mRequest.get('GICAL')
		dingrediente.put()
		self.mReturnValue = "1"
		
		

	def Select(self):
		lstIngredientes = []
		keyValue = str(self.mRequest.get('GIKEY'))
		qry = DIngrediente.DIngrediente.query()
		for recIngrediente in qry:
			if keyValue != "":
				if str(recIngrediente.key.id()) == keyValue:
					lstIngredientes.append(CIngrediente.CIngrediente(str(recIngrediente.mNombreIngrediente),str(recIngrediente.mCalorias),str(recIngrediente.key.id())).jsonSerialize())
			else :
				lstIngredientes.append(CIngrediente.CIngrediente(str(recIngrediente.mNombreIngrediente),str(recIngrediente.mCalorias),str(recIngrediente.key.id())).jsonSerialize())
		self.mReturnValue = lstIngredientes

	def Update(self):
		# Obtener los parametros para poder actualizarlos
		self.mReturnValue = "0"
		nombrereturnValue = str(self.mRequest.get('GINOM'))
		caloriareturnValue = str(self.mRequest.get('GICAL'))
		keyValue = str(self.mRequest.get('GIKEY'))
		qry = DIngrediente.DIngrediente.query()
		# Ejecutar el query
		if keyValue != "":
			for recIngrediente in qry:
				if str(recIngrediente.key.id()) == keyValue:					
					if nombrereturnValue != "":
						recIngrediente.mNombreIngrediente = nombrereturnValue
					if caloriareturnValue != "":
						recIngrediente.mCalorias = caloriareturnValue
					recIngrediente.put()
					self.mReturnValue = "1"
		

	def Delete(self):
		self.mReturnValue = "0"
		keyValue = str(self.mRequest.get('GIKEY'))
		qry = DIngrediente.DIngrediente.query()
		if keyValue != "":
			for recIngrediente in qry:
				if str(recIngrediente.key.id()) == keyValue:
					self.mReturnValue = "1"
					recIngrediente.key.delete()
		

	def GetValue(self):
		return self.mReturnValue