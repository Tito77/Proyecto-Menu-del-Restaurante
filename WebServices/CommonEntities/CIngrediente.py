# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
import JsonEncoder

class CIngrediente:
	def __init__(self, pNombreIngrediente="", pCalorias="", pKeyValue=""):
		self.mNombreIngrediente = ""
		self.mCalorias = ""
		self.mKeyValue = ""
		if type(pNombreIngrediente) is str:
			self.mNombreIngrediente = pNombreIngrediente
		if type(pCalorias) is str:
			self.mCalorias = pCalorias
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

			#Setters y Getters
	def setKeyValue(self,pKeyValue):
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

	def setNombreIngrediente(self, pNombreIngrediente=""):
		if type(pNombreIngrediente) is str:
			self.mNombreIngrediente = pNombreIngrediente

	def setCalorias(self, pCalorias = 0):
		if type(pCalorias) is int:
			self.mCalorias = pCalorias

	def getNombreIngrediente(self):
		return self.mNombreIngrediente

	def getCalorias(self):
		return self.mCalorias

	def jsonSerialize(self):
		return JsonEncoder.JsonEncoder().serializeJson(self)