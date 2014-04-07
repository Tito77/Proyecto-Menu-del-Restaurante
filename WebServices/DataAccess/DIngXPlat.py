# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

from google.appengine.ext import ndb

class DIngXPlat(ndb.Model):
	mKeyPlatillo = ndb.StringProperty(indexed=True)
	mKeyIngrediente = ndb.StringProperty(indexed=True)
