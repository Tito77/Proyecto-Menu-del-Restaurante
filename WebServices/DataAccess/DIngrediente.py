# coding: utf-8
"""
Proyecto 1
Electiva Desarrollo de Aplicaciones Moviles
Andres Gonzalez
David Montero
Emanuel Avendano
"""
from google.appengine.ext import ndb

class DIngrediente(ndb.Model):
    mNombreIngrediente = ndb.StringProperty(indexed=True)
    mCalorias = ndb.StringProperty(indexed=True)