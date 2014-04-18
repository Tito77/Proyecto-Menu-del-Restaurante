# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
import JsonEncoder

class CMesa:
	def __init__(self, pNumeroMesa="", pEstadoRegistro="", pTMPSTMP="", pKeyValue=""):
		self.mNumeroMesa = ""
		self.mEstadoRegistro = ""
		self.mTMPSTMP = ""
		self.mKeyValue = ""
		if type(pNumeroMesa) is str:
			self.mNumeroMesa = pNumeroMesa
		if type(pEstadoRegistro) is str:
			self.mEstadoRegistro = pEstadoRegistro
		if type(pTMPSTMP) is str:
			self.mTMPSTMP = pTMPSTMP
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

			#Setters y Getters
	def setKeyValue(self,pKeyValue):
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

	def setNumeroMesa(self, pNumeroMesa=""):
		if type(pNumeroMesa) is str:
			self.mNumeroMesa = pNumeroMesa

	def setEstadoRegistro(self, pEstadoRegistro = ""):
		if type(pEstadoRegistro) is str:
			self.mEstadoRegistro = pEstadoRegistro

	def setTMPSTMP(self, pTMPSTMP=""):
		if type(pTMPSTMP) is str:
			self.mTMPSTMP = pTMPSTMP


	def getNumeroMesa(self):
		return self.mNumeroMesa

	def getEstadoRegistro(self):
		return self.mEstadoRegistro

	def getTMPSTMP(self):
		return self.mTMPSTMP

	def jsonSerialize(self):
		return JsonEncoder.JsonEncoder().serializeJson(self)