# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import Business.GPlatilloCtrl
import Business.GIngredienteCtrl
import Business.GMenuCtrl
import Libraries.Constantes
import Libraries.JsonEncoder

class Bridge:

	def __init__(self,pRequest):
		self.mOperation = ""
		self.mModuloExec = ""
		self.mRequest = pRequest
		self.mReturnValue = {'RETURNVALUE':""}

	def GetValue(self):
			return self.mReturnValue

	def IniciarEjecucion(self):
		self.mModuloExec = str(self.mRequest.get('MOD'))
		self.mOperation = self.mRequest.get("EXECOP")
		if self.mModuloExec == Libraries.Constantes.Constantes().mModuleIngredientes:
			self.mControl = Business.GIngredienteCtrl.GIngredienteCtrl(self.mRequest)
			self.mControl.mOperation = self.mOperation
			self.mControl.Execute()
			self.mReturnValue['RETURNVALUE'] = self.mControl.GetValue()
			self.mReturnValue = Libraries.JsonEncoder.JsonEncoder().serializeJson(self.mReturnValue)
		if self.mModuloExec == Libraries.Constantes.Constantes().mModulePlatillo:
			self.mControl = Business.GPlatilloCtrl.GPlatilloCtrl(self.mRequest)
			self.mControl.mOperation = self.mOperation
			self.mControl.Execute()
			self.mReturnValue['RETURNVALUE'] = self.mControl.GetValue()
			self.mReturnValue = Libraries.JsonEncoder.JsonEncoder().serializeJson(self.mReturnValue)
		if self.mModuloExec == Libraries.Constantes.Constantes().mModuleMenu:
			self.mControl = Business.GMenuCtrl.GMenuCtrl(self.mRequest)
			self.mControl.mOperation = self.mOperation
			self.mControl.Execute()
			self.mReturnValue['RETURNVALUE'] = self.mControl.GetValue()
			self.mReturnValue = Libraries.JsonEncoder.JsonEncoder().serializeJson(self.mReturnValue)

