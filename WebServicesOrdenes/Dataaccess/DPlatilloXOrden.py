# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

from google.appengine.ext import ndb

class DPlatilloXOrden(ndb.Model):
	mKeyOrden = ndb.StringProperty(indexed=True)
	mKeyPlatillo = ndb.StringProperty(indexed=True)
	mCantidad = ndb.StringProperty(indexed=True)
	mNotaPromocion = ndb.StringProperty(indexed=True)
	mNotaEspecial = ndb.StringProperty(indexed=True)
