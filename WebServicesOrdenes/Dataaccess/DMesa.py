# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

from google.appengine.ext import ndb

class DMesa(ndb.Model):
    mNumeroMesa = ndb.StringProperty(indexed=True)
    mEstadoRegistro = ndb.StringProperty(indexed=True)
    mTMPSTMP = ndb.StringProperty(indexed=True)