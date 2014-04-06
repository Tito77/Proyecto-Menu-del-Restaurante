# coding: utf-8
"""
Proyecto 1
Electiva Desarrollo de Aplicaciones Moviles
Andres Gonzalez
David Montero
Emanuel Avendano
"""
from google.appengine.ext import ndb

class DPlatillo(ndb.Model):
    mNombrePlatillo = ndb.StringProperty(indexed=True)
    mPrecio = ndb.StringProperty(indexed=True)