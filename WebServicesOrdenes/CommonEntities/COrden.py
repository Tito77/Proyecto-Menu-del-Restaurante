# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
import JsonEncoder

class COrden:
	def __init__(self, pTotal="", pEstadoServido="", pTMPSTMP="", pKeyMesa="", pKeyValue=""):
		self.mTotal = ""
		self.mEstadoServido = ""
		self.mTMPSTMP = ""
		self.mKeyMesa = ""
		self.mKeyValue = ""
		if type(pTotal) is str:
			self.mTotal = pTotal
		if type(pEstadoServido) is str:
			self.mEstadoServido = pEstadoServido
		if type(pTMPSTMP) is str:
			self.mTMPSTMP = pTMPSTMP
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue
		if type(pKeyMesa) is str:
			self.mKeyMesa = pKeyMesa

			#Setters y Getters
	def setKeyValue(self,pKeyValue):
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

	def setTotal(self, pTotal=""):
		if type(pTotal) is str:
			self.mTotal = pTotal

	def setEstadoServido(self, pEstadoServido = ""):
		if type(pEstadoServido) is str:
			self.mEstadoServido = pEstadoServido

	def setTMPSTMP(self, pTMPSTMP=""):
		if type(pTMPSTMP) is str:
			self.mTMPSTMP = pTMPSTMP

	def setKeyMesa(self,pKeyMesa):
		if type(pKeyMesa) is str:
			self.mKeyMesa = pKeyMesa


	def getTotal(self):
		return self.mTotal

	def getEstadoServido(self):
		return self.mEstadoServido

	def getTMPSTMP(self):
		return self.mTMPSTMP

	def getKeyMesa(self):
		return self.mKeyMesa

	def jsonSerialize(self):
		return JsonEncoder.JsonEncoder().serializeJson(self)