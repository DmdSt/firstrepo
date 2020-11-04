#Sorting by keys:
d={'c':10,'a':1,'b':22}
t=sorted(d.items())
print(t)
print('dictionary sorted via tuples in list')

for key,value in t:
    print(key,value)

#Sorting by values:
c={'a':10,'b':1,'c':22}
tmp=list()
for k,v in c.items()
    tmp.append((v,k))
print(tmp)
#Ausgabe [(10,'a'),(22,'c'),(1,'b')]
tmp=sorted(tmp,reverse=True)
print(tmp)
#Ausgabe:[(22,'c'),(10,'a'),(1,'b')]

#G2k:
#Dies (macht Liste mit umgekehrten Tupeln):
lst=list()
for key,val in count.items()
newtup=(val,key)
lst.append(newtup)

#...ist noch einfacher zu machen mit:
print(sorted([(v,k) for k,v in count.items()]))
