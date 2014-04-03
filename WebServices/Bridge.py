# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import Business.GIngredienteCtrl
class Bridge:

	def __init__(self):
		self.mOperation = -1
		self.mControl = Business.GIngredienteCtrl.GIngredienteCtrl()