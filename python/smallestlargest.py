largestsofar=None
smallestsofar=None
while True:
    number=input('Enter a number:')
    if number=='done':
        break
    try:
        inumber=int(number)
        if largestsofar is None:
            largestsofar=inumber
            smallestsofar=inumber
        elif largestsofar < inumber:
            largestsofar=inumber
    #    if smallestsofar is None:
        #    smallestsofar=inumber
        elif smallestsofar > inumber:
            smallestsofar=inumber
    except:
        print('Invalid input')
print('Maximum is', largestsofar)
print('Minimum is', smallestsofar)
input()
exit()
