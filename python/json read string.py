
import json

data='''{
    "name": "Till",
    "phone": {
        "type": "intl",
        "number": "+1734 303 4456"
    },
    "email": {
        "hide": "yes"
    }
}'''

infolist=json.loads(data)
print('Name:',infolist['name'])
print('Phone number:',infolist['phone']['number'])
input()
