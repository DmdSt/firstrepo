
def jumpsToBinary(b:str)->str:
    if b=="JGT":
        return "001"
    if b=="JEQ":
        return "010"
    if b=="JGE":
        return "011"
    if b=="JLT":
        return "100"
    if b=="JNE":
        return "101"
    if b=="JLE":
        return "110"
    if b=="JMP":
        return "111"
    if b=="null":
        return "000"
    else:
        print("something's fucked")

def compTobinary(c:str)->str:
    if c=="0": return   "0101010"
    if c=="1": return   "0111111"
    if c=="-1": return  "0111010"
    if c=="D": return   "0001100"
    if c=="A": return   "0110000"
    if c=="M": return   "1110000"
    if c=="!D": return  "0001101"
    if c=="!A": return  "0110001"
    if c=="!M": return  "1110001"
    if c=="-D": return  "0001111"
    if c=="-A": return  "0110011"
    if c=="-M": return  "1110011"
    if c=="D+1": return "0011111"
    if c=="A+1": return "0110111"
    if c=="M+1": return "1110111"
    if c=="D-1": return "0001110"
    if c=="A-1": return "0110010"
    if c=="M-1": return "1110010"
    if c=="D+A": return "0000010"
    if c=="D+M": return "1000010"
    if c=="D-A": return "0010011"
    if c=="D-M": return "1010011"
    if c=="A-D": return "0000111"
    if c=="M-D": return "1000111"
    if c=="D&A": return "0000000"
    if c=="D&M": return "1000000"
    if c=="D|A": return "0010101"
    if c=="D|M": return "1010101"

def destToBinary(d:str)->str:
    if d=="null": return "000"
    if d=="M": return "001"
    if d=="D": return "010"
    if d=="MD": return "011"
    if d=="A": return "100"
    if d=="AM": return "101"
    if d=="AD": return "110"
    if d=="AMD": return "111"
