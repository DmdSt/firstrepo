
#trying to find numbers in given text and add them:

import re
thandle=open('HTW.txt')
liste=list()
for line in thandle:
    line=line.rstrip()
    #print(line)
    foundstrings=re.findall('[0-9]+\.*[0-9]*',line)
    for i in range(len(foundstrings)):
        liste.append(foundstrings[i])
print(liste)
# nun addieren wir die zahlen als float zahlen
sum=0
for string in liste:
    number=float(string)
    sum=sum+number
print('die summe ist:',sum)
input()
#[0-9]+\.*[0-9]*
#([0-9]+)
