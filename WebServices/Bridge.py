# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import Business.GIngredienteCtrl
import Libraries.Constantes

class Bridge:

	def __init__(self,pRequest):
		self.mOperation = ""
		self.mModuloExec = ""
		self.mReturnValue = ""
		self.mRequest = pRequest

	def GetValue(self):
			return self.mReturnValue

	def IniciarEjecucion(self):
		self.mModuloExec = self.mRequest.get('MOD')
		self.mOperation = self.mRequest.get('EXECOP')
		if self.mModuloExec == "GI":
			self.mControl = Business.GIngredienteCtrl.GIngredienteCtrl(self.mRequest)
			self.mControl.setOperation(self.mOperation)
			if self.mOperation == Libraries.Constantes.Constantes().mOperacionSelect:
				self.mReturnValue = self.mControl.Select()
			else:
				self.mControl.Execute()

