/*def istGerade(x:Int):Boolean = {
  if (x==0) true
  else istUngerade(x-1)
}

def istUngerade(x:Int):Boolean = {
  if (x==0) false
  else istGerade(x-1)
}*/

def pred(x:Int):Int = x-1
def succ(x:Int):Int = x+1
def isZero(x:Int):Boolean = x==0

def add(a:Int,b:Int):Int = {
  if (isZero(a)) b
  else pred(a)+succ(b)
}

add(7,3)

def mult(a:Int,b:Int):Int = {
  if (b==1) a
  else  add(mult(a,b-1),a)
}

def newton(x:Float,fxi:Float):Float = {
  if (fxi==0) 1
  else (newton(x,fxi-1)+(x/newton(x,fxi-1)))/2
}

def root(x:Float):Float = {
  if (x==0) 0
  else newton(x,4)
}



root(16)
