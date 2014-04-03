# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
import Constantes

#Gestion Ingrediente Control
class GIngredienteCtrl:
	def __init__(self):
		self.mConstantes = "Esta es mi constante"
		self.mConstantes2 = Constantes.Constantes()