# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

from google.appengine.ext import ndb

class DMenu(ndb.Model):
	mNombreMenu = ndb.StringProperty(indexed=True)
	mDescripcion = ndb.StringProperty(indexed=True)
	mFechaInicioAplicacion = ndb.StringProperty(indexed=True)
	mFechaFinalAplicacion = ndb.StringProperty(indexed=True)
	mEstadoAplicacion = ndb.StringProperty(indexed=True)