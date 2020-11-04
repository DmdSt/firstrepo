print('Hallo, ich errechne Ihnen alle Teiler für natürliche Zahlen. Bis zu welcher Zahl soll ich alle Teiler finden?')
zahl=input('Zahl: ')
zahl=int(zahl)
liste=[*range(1,zahl+1)]
teiler=[1]
Primzahlen=[1]
max=0
UngeradeTeiler=[]
GeradeTeiler=[]
gefunden=False
print('Ok, Ihre Liste lautet:',liste)
#print('Gehe in aeussere for schleife rein...')

for i in liste:
    #print('i beträgt jetzt ',i)
    teiler=[1]
    if i==1:
        print('Die Teiler von ',i,'sind ',teiler)
    else:
        for y in range(1,i+1):
            #print('Bin in innerer for...')
            #print('y beträgt jetzt ',y)
            if y !=1 and i % y == 0:
                teiler.append(y)
                temp=y
                #print('bin in if reingegangen')
        print('Die Teiler von ',i,'sind ',teiler)
        if len(teiler)==2:
            Primzahlen.append(temp)
        #print('gehe raus aus innerer for.')
        if max < len(teiler):
            max=len(teiler)
            ZahlmaxTeiler=temp
        if len(teiler) == 7:
            gefunden=True
            Magicnumber=temp
        if len(teiler) % 2 ==0: #Teiler ist gerade
            GeradeTeiler.append(temp)
        else:
            UngeradeTeiler.append(temp)

print('\n Die Maximale Anzahl von Teilern hatte',ZahlmaxTeiler,'mit',max,'Teilern.' )
print('\n Die folgenden Zahlen haben eine ungerade Anzahl von Teilern:',UngeradeTeiler)
print('\n Die folgenden Zahlen haben eine gerade Anzahl von Teilern:',GeradeTeiler)
print('\n Die Primzahlen von 1 bis',zahl,'lauten:', Primzahlen)

if gefunden==True:
    print('\n Eine Zahl mit 7 Teilern war vorhanden, nämlich die',Magicnumber,'.')
else:
    print('\n Es gab keine Zahl mit 7 Teilern.')

input()
