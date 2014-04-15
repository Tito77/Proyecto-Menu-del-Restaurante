# coding: utf-8

#Proyecto 1
#Electiva Desarrollo de Aplicaciones Moviles
#Andres Gonzalez
#David Montero
#Emanuel Avendano

import webapp2

class MainPage(webapp2.RequestHandler):

    def get(self):
        self.response.write("")


#application = webapp2.WSGIApplication([
#    ('/', MainPage),
#], debug=True)