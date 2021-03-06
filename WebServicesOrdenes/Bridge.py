# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import Business.GMesaCtrl
import Business.GOrdenCtrl
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
		if self.mModuloExec == Libraries.Constantes.Constantes().mModuleMesa:
			self.mControl = Business.GMesaCtrl.GMesaCtrl(self.mRequest)
			self.mControl.mOperation = self.mOperation
			self.mControl.Execute()
			self.mReturnValue['RETURNVALUE'] = self.mControl.GetValue()
			self.mReturnValue = Libraries.JsonEncoder.JsonEncoder().serializeJson(self.mReturnValue)
		if self.mModuloExec == Libraries.Constantes.Constantes().mModuleOrden:
			self.mControl = Business.GOrdenCtrl.GOrdenCtrl(self.mRequest)
			self.mControl.mOperation = self.mOperation
			self.mControl.Execute()
			self.mReturnValue['RETURNVALUE'] = self.mControl.GetValue()
			self.mReturnValue = Libraries.JsonEncoder.JsonEncoder().serializeJson(self.mReturnValue)