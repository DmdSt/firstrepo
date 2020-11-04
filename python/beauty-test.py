import urllib.request, urllib.parse, urllib.error
from bs4 import BeautifulSoup
import ssl
print('got past the imports.')

# Ignores SSL Certificate errors
ctx=ssl.create_default_context()
ctx.check_hostname=False
ctx.verfiy_mode=ssl.CERT_NONE

url=input('Enter URL: ')
html=urllib.request.urlopen(url, context=ctx).read()
soup=BeautifulSoup(html,'html.parser')

#Retrieves all of the anchor (a-) tags
tags=soup('a')
for tag in tags:
    print(tag.get('href',None))

#working only for non-ssl sites atm.. try sth like "http://www.ferienwohnung-obersdorf.de/kontakt.html"
