import xml.etree.ElementTree as ET
data='''<all>
    <person>
        <name>DmdSt</name>
        <phone type="intl">+49 30 60 95 00 60</phone>
        <email hide="yes">j.***@***.de</email>
        <passport hide="no"/>
    </person>
    <users>
        <user id="22">
            <name>Babyface</name>
            <email>britneydiditagain@gmail.com</email>
        </user>
        <user id="24">
            <name>Santa Clause</name>
            <email>SC@northpole.com</email>
        </user>
        <user id="30">
            <name>Penguin</name>
            <email>cycle-maniac@web.de</email>
        </user>
    </users>
    </all>
    '''

tree=ET.fromstring(data)
print('This is the author:')
print('Name =',tree.find('person/name').text)
print('Email hidden =',tree.find('person/email').get('hide'))
print('Email =',tree.find('person/email').text)
print('Phone =',tree.find('person/phone').text)
try:
    print('Passport',tree.find('person/passport').text)
except:
    print('doesn\'t work')

print('Now checking for plattform users...')
lst=tree.findall('users/user')
print('Number of found users:',len(lst))
#print(lst)
for item in lst:
    print('User-ID:',item.get('id'))
    print('   Name:',item.find('name').text)
    print('   Email:',item.find('email').text)
input()
