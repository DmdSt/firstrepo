
teiler=[1]
Primzahlen=[1]
max=0
i=1
gefunden=False

while gefunden==False:
    #print('i beträgt jetzt ',i)
    teiler=[1]
    if i==1:
        print('Die Teiler von ',i,'sind ',teiler)
    else:
        for y in range(1,i+1):
            if y !=1 and i % y == 0:
                teiler.append(y)
                temp=y
        print('Die Teiler von ',i,'sind ',teiler)
        if len(teiler) ==7:
            gefunden=True
            Magicnumber=i
    i=i+1
print('Gefunden! Die Zahl mit 7 Teilern beträgt:',Magicnumber)
input()
