import cgi
import urllib

from google.appengine.ext import ndb
import webapp2
import Bridge


class MainPage(webapp2.RequestHandler):

	def get(self):
		b = Bridge.Bridge(self.request)
		b.IniciarEjecucion()
		self.response.write(b.mReturnValue)


application = webapp2.WSGIApplication([
    ('/', MainPage),
], debug=True)