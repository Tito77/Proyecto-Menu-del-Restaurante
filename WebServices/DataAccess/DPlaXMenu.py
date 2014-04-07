# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

from google.appengine.ext import ndb

class DPlaXMenu(ndb.Model):
	mKeyMenu = ndb.StringProperty(indexed=True)
	mKeyPlatillo = ndb.StringProperty(indexed=True)
