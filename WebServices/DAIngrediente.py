# coding: utf-8
"""
Proyecto 1
Electiva Desarrollo de Aplicaciones Moviles
Andres Gonzalez
David Montero
Emanuel Avendano
"""
from google.appengine.ext import ndb

class DAIngrediente(ndb.Model):
    _NombreIngrediente = ndb.StringProperty(indexed=False)
    _Calorias = ndb.IntegerProperty(indexed=False)


    def setNombreIngrediente(self, pNombreIngrediente=""):
    	if type(pNombreIngrediente) is str:
    		self._NombreIngrediente = pNombreIngrediente

    def setCalorias(self,pCalorias):
    	if type(pCalorias) is int:
    		self._Calorias = pCalorias

    def getNombreIngrediente(self):
    	return self._NombreIngrediente

    def saveDS(self):
    	self.put()
