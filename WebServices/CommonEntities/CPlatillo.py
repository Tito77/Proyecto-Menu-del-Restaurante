# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
import JsonEncoder

class CPlatillo:
	def __init__(self, pNombrePlatillo="", pPrecio="", pKeyValue=""):
		self.mNombrePlatillo = ""
		self.mPrecio = ""
		self.mKeyValue = ""
		if type(pNombrePlatillo) is str:
			self.mNombrePlatillo = pNombrePlatillo
		if type(pPrecio) is str:
			self.mPrecio = pPrecio
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

			#Setters y Getters
	def setKeyValue(self,pKeyValue):
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

	def setNombrePlatillo(self, pNombrePlatillo=""):
		if type(pNombrePlatillo) is str:
			self.mNombrePlatillo = pNombrePlatillo

	def setPrecio(self, pPrecio = 0):
		if type(pPrecio) is int:
			self.mPrecio = pPrecio

	def getNombrePlatillo(self):
		return self.mNombrePlatillo

	def getPrecio(self):
		return self.mPrecio

	def jsonSerialize(self):
		return JsonEncoder.JsonEncoder().serializeJson(self)