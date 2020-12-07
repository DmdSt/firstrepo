# building an assembler for hack
import re  # imports regular expressions
from patternmatch import jumpsToBinary,compTobinary,destToBinary

outputtext="".rstrip()
instruction = ""
lineresult = ""
instructionnumber=-1
ramadress=16
symboltable=dict()
symboltable={"R0":0,"R1":1,"R2":2,"R3":3,"R4":4,"R5":5,"R6":6,"R7":7,"R8":8,
"R9":9,"R10":10,"R11":11,"R12":12,"R13":13,"R14":14,"R15":15,"R16":16,
"SCREEN":16384,"KBD":24576,"SP":0,"LCL":1,"ARG":2,"THIS":3,"THAT":4}

#print('------------------------------------------------------START')
tname=input('Enter name of text file: ')
try:
    textfile=open(tname)
    #print('opened the textfile.')
except:
    #print('cannot open textfile!')
    handtext=input('Enter a text by hand: ')

# 1. Durchlauf: Labels und Symbole verarbeiten
for line in textfile:
    if not line.strip() or line.startswith("//"): #line.strip = false, wenn Leerzeile
        continue
    else:
        if line.startswith("("): # Label (xxx) gefunden
            #print(line)
            matchobject = re.search(r"\(([A-Za-z0-9_]+)\)", line) #extrahiert String xxx aus "(xxx)"
            #print(matchobject)
            symbol=matchobject.group(1)
            symboltable[symbol]=instructionnumber+1
            continue
        # Gibt es Randkommentare? Entferne sie.
        foundsidecomments=(line.find("//")!=-1) # find gibt -1 wenn false
        if foundsidecomments:
            #print("found side comments")
            listwocomments=line.rsplit("//")
            del listwocomments[-1] #deletes last list element
            strwocomments = ' '.join([str(element) for element in listwocomments])
            newline=strwocomments.strip()
        else:
            #print("no side comments")
            newline=line.strip()
        if newline.startswith("@") and not newline.strip("@").rstrip().isnumeric():
            # Zeile mit @Stringvariable gefunden
            symbol=newline.strip("@").rstrip()
            # speichern in Symboltabelle, sofern noch nicht vorhanden
            if symboltable.get(symbol)==None:
                symboltable[symbol]=ramadress
                ramadress+=1
        instructionnumber+=1

print("1. Durchlauf beendet, Symboltabelle:\n", symboltable)

textfile=open(tname)
for line in textfile:
    # ignoriere Leerzeilen und Kommentarzeilen
    if not line.strip() or line.startswith("//"): #line.strip = false, wenn Leerzeile
        continue
    else:
        instructionnumber+=1
        #print("\n newline:",line)
        # gibt es Randkommentare?
        foundsidecomments=(line.find("//")!=-1) # find gibt -1 wenn false
        if foundsidecomments:
            #print("found side comments")
            listwocomments=line.rsplit("//")
            del listwocomments[-1] # deletes last list element
            strwocomments = ' '.join([str(element) for element in listwocomments])
            newline=strwocomments.strip()
        else:
            #print("no side comments")
            newline=line.strip()
        # Fall: A-instruction --------------------------------------------------
        if newline.startswith("@"):
            instruction = "A"
            #print("This line is an",instruction,"-instruction")
            valuestr=newline.strip("@") # Achtung: valuestr ist ein str
            valuestr=valuestr.rstrip()
            try:
                value=int(valuestr) # convert valuestr to integer
            except:
                value=symboltable.get(valuestr)
            #print("Value is:",value,"with type:",type(value))
            lineresult="0"+format(value, '015b')
            #print(lineresult, "length of line is",len(lineresult))
        # Fall: C-instruction OHNE jump ---------------------------------------
        elif "=" in newline:
            instruction = "C"
            #print("This line is a", instruction,"-instruction w/o Jumps")
            splitlist=newline.split("=")
            #print(splitlist)
            dest=splitlist[0].strip() # ersten Listenelement
            #print("dest=",dest)
            comp=splitlist[-1].strip() # zweites/letztes Element ohne "\n" am Ende
            #print("comp=",comp)
            jump="null"

        # Fall: C-instruction MIT jump ----------------------------------------
        elif newline.find("J") != -1: #dann hat er einen Index im String gefunden (andernfalls returned er -1)
            instruction = "C"
            #print("This line is a", instruction,"-instruction w/ Jumps")
            #splitte jump-Anweisung ab
            jumplist=newline.split(";")
            jump=jumplist[-1].rstrip() # -1 is the index of the last item in list
            comp=jumplist[0].strip()
            dest="null"

        # Endbehandlung der Fälle: --------------------------------------------
        # je nach instruction-Typ:
        if instruction=="A":
            print(lineresult)
            outputtext=outputtext+"\n"+lineresult
        elif instruction=="C":
            #print("dest=",dest)
            #print("comp=",comp)
            #print("jump=",jump)
            lineresult="111"+compTobinary(comp)+destToBinary(dest)+jumpsToBinary(jump)
            print(lineresult)
            outputtext=outputtext+"\n"+lineresult

hackfile = open("hack.txt","w")
hackfile.write(outputtext.lstrip())
hackfile.close()
print("End of program")
input()
