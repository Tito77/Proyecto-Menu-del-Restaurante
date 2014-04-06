# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import sys, os
sys.path.append(os.path.join(os.path.dirname(__file__), '..', 'Libraries'))
import JsonEncoder

class CMenu:
	def __init__(self, pNombreMenu="", pDescripcion="", pFechaInicioAplicacion = "", pFechaFinalAplicacion = "", pEstadoAplicacion = "" , pKeyValue=""):
		self.mNombreMenu = ""
		self.mDescripcion = ""
		self.mKeyValue = ""
		self.mFechaInicioAplicacion = ""
		self.mFechaFinalAplicacion = ""
		self.mEstadoAplicacion = ""
		if type(pNombreMenu) is str:
			self.mNombreMenu = pNombreMenu
		if type(pDescripcion) is str:
			self.mDescripcion = pDescripcion
		if type(pFechaInicioAplicacion) is str:
			self.mFechaInicioAplicacion = pFechaInicioAplicacion
		if type(pFechaFinalAplicacion) is str:
			self.mFechaFinalAplicacion = pFechaFinalAplicacion
		if type(pEstadoAplicacion) is str:
			self.mEstadoAplicacion = pEstadoAplicacion
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

			#Setters y Getters
	def setKeyValue(self,pKeyValue):
		if type(pKeyValue) is str:
			self.mKeyValue = pKeyValue

	def setNombreMenu(self, pNombreMenu=""):
		if type(pNombreMenu) is str:
			self.mNombreMenu = pNombreMenu

	def setDescripcion(self, pDescripcion = 0):
		if type(pDescripcion) is int:
			self.mDescripcion = pDescripcion

	def setFechaInicioAplicacion(self, pFechaInicioAplicacion):
		if type(pFechaInicioAplicacion) is str:
			self.mFechaInicioAplicacion = pFechaInicioAplicacion

	def setFechaInicioAplicacion(self, pFechaFinalAplicacion):
		if type(pFechaFinalAplicacion) is str:
			self.mFechaFinalAplicacion = pFechaFinalAplicacion

	def setEstadoAplicacion(self, pEstadoAplicacion):
		if type(pEstadoAplicacion) is str:
			self.mEstadoAplicacion = pEstadoAplicacion

	def getNombreMenu(self):
		return self.mNombreMenu

	def getDescripcion(self):
		return self.mDescripcion

	def jsonSerialize(self):
		return JsonEncoder.JsonEncoder().serializeJson(self)