import cgi
import urllib

from google.appengine.api import users
from google.appengine.ext import ndb
import webapp2
import Bridge

# Variables
mOperation = ""


class MainPage(webapp2.RequestHandler):

    def get(self):
        executeOperation = self.request.get('EXECOP',mOperation)
        self.response.write(executeOperation);
        b = Bridge.Bridge()
        self.response.write(b.mControl.mConstantes)
        self.response.write(b.mControl.mConstantes2.mOperacionSelect)


application = webapp2.WSGIApplication([
    ('/', MainPage),
], debug=True)