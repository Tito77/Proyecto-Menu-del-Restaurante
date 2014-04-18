# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
import JsonEncoder

class CPlatilloXOrden:
	def __init__(self, pKeyOrden="", pKeyPlatillo="", pCantidad="", pNotaPromocion="", pNotaEspecial=""):
		self.mKeyOrden = ""
		self.mKeyPlatillo = ""
		self.mCantidad = ""
		self.mNotaPromocion = ""
		self.mNotaEspecial = ""
		if type(pKeyOrden) is str:
			self.mKeyOrden = pKeyOrden
		if type(pKeyPlatillo) is str:
			self.mKeyPlatillo = pKeyPlatillo
		if type(pCantidad) is str:
			self.mCantidad = pCantidad
		if type(pNotaPromocion) is str:
			self.mNotaPromocion = pNotaPromocion
		if type(pNotaEspecial) is str:
			self.mNotaEspecial = pNotaEspecial


	def jsonSerialize(self):
		return JsonEncoder.JsonEncoder().serializeJson(self)