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
		self.mOpetation = ""
		self.mNombreIngrediente = ""
		self.mCalorias = ""
		self.mKeyValue = ""

	def setOperation(self,pOperation):
		if pOperation is str:
			self.mOpetation = pOperation

	def Execute(self):
		constantes = Constantes.Constantes()
		if self.mOpetation == constantes.mOperacionInsert:
			self.Insert()
		if self.mOpetation == constantes.mOperacionSelect:
			self.Select()
		if self.mOpetation == constantes.mOperacionUpdate:
			self.Update()
		if self.mOpetation == constantes.mOperacionDelete:
			self.Delete()


	def Insert(self):
		dingrediente = DIngrediente.DIngrediente()
		dingrediente.mNombreIngrediente = self.mRequest.get('GINOM')
		dingrediente.mCalorias = self.mRequest.get('GICAL')
		dingrediente.put()

	def Select(self):
		lstIngredientes = []
		qry = DIngrediente.DIngrediente.query().order(DIngrediente.DIngrediente.mNombreIngrediente)
		for recIngrediente in qry:
			lstIngredientes.append(CIngrediente.CIngrediente(recIngrediente.mNombreIngrediente,recIngrediente.mCalorias,str(recIngrediente.key.id())).jsonSerialize())
			#lstIngredientes.append(" Key: " + str(recIngrediente.key.id()) + " Ingrediente:  " + recIngrediente.mNombreIngrediente + " Calorias:" + recIngrediente.mCalorias)
		return lstIngredientes

	def Update(self):
		qry = DIngrediente.DIngrediente.query().order(DIngrediente.DIngrediente.mNombreIngrediente)
		for recIngrediente in qry:
			if str(recIngrediente.key.id()) == self.mKeyValue:
				recIngrediente.mNombreIngrediente = self.mNombreIngrediente
				recIngrediente.mCalorias = self.mCalorias
				recIngrediente.put()