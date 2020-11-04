# Find the day the mails were sent in mbox-short.txt

tname=input('Enter name of text file: ')
way1=False
try:
    textfile=open(tname)
    print('opened the textfile.')
    way1=True
except:
    print('cannot open textfile!')
    handtext=input('Enter a text by hand: ')
    way1=False

ddd=dict()
max=1
mostoftenword='fuckdontknow'

print('way1 is', way1)
if way1==True:
    print('reading textfile...')
    for line in textfile:
        # this part erases characters like ( that i don't want in the lines
        for character in ['(',')','.',':','&',',']:
            if character in line:
                line=line.replace(character,"")
        words=line.split()
        if words == []:
            continue
        #print('words in this line are:', words)
        for word in words:
            ddd[word]=ddd.get(word,0)+1
            if max < ddd[word]:
                max = ddd[word]
                mostoftenword = word
else:
    print('reading handwritten text...')
    handtext=handtext.rstrip()
    handtext=handtext.split()
    for word in handtext:
        ddd[word]=ddd.get(word,0)+1
        if max < ddd[word]:
            max = ddd[word]
            mostoftenword = word
print('finished the dictionary.')
print('this is the histogram:', ddd)
print('the most frequent word with',max,'occurrances is:',mostoftenword)
print('----------------------------')

# after counting all words and finding to most frequent one, i want to sort the dictionary
# of words by their occurance-frequency.
liste=list()
#for key,value in ddd.items():
#    liste.append((value,key))
#newlist=sorted(liste)
#print('here is the list sorted by occurance:',newlist)

# kürzere Variante, but weird:
print('Here is the sorted list:', sorted(   [(val,key) for (key,val) in ddd.items()],reverse=True   )      )

input()
