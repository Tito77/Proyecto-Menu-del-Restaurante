# coding: utf-8
"""
Proyecto 1
Electiva Desarrollo de Aplicaciones Moviles
Andres Gonzalez
David Montero
Emanuel Avendano
"""
#import Libraries.JsonEncoder

class Ingrediente:
	#Constructor de la clase
	def __init__(self, pNombreIngrediente="", pCalorias=0):
		if type(pNombreIngrediente) is str:
			self._NombreIngrediente = pNombreIngrediente
		if type(pCalorias) is int:
			self._Calorias = pCalorias
	

	#Setters y Getters

	def setParentKey(self,pParentKey):
		self._ParentKeyValue = pParentKey

	def setNombreIngrediente(self, pNombreIngrediente=""):
		if type(pNombreIngrediente) is str:
			self._NombreIngrediente = pNombreIngrediente

	def setCalorias(self, pCalorias = 0):
		if type(pCalorias) is int:
			self._Calorias = pCalorias

	def getNombreIngrediente(self):
		return self._NombreIngrediente

	def getCalorias(self):
		return self._Calorias

	#def parseJsonEncoding(self):
	#	return Libraries.JsonEncoder.serializeJson(self)

	