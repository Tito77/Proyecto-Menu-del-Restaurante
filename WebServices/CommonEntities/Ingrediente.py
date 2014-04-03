# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

#import Libraries.JsonEncoder

class Ingrediente:
	#Constructor de la clase
	def __init__(self, pNombreIngrediente="", pCalorias=0):
		if type(pNombreIngrediente) is str:
			self.mNombreIngrediente = pNombreIngrediente
		if type(pCalorias) is int:
			self.mCalorias = pCalorias
	

	#Setters y Getters

	def setParentKey(self,pParentKey):
		self._ParentKeyValue = pParentKey

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

	#def parseJsonEncoding(self):
	#	return Libraries.JsonEncoder.serializeJson(self)

	