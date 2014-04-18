# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

from google.appengine.ext import ndb

class DOrden(ndb.Model):
	mKeyMesa = ndb.StringProperty(indexed=True)
	mTotal = ndb.StringProperty(indexed=True)
	mTMPSTMP = ndb.StringProperty(indexed=True)
	mEstadoServido = ndb.StringProperty(indexed=True)