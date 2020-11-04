//Aufg.2a)

def binKoeff(m:Int,n:Int):Int = {
  def fakultaet(n:Int):Int = {
    if (n==0) 1
    else n*fakultaet(n-1)
  }
  fakultaet(m)/(fakultaet(n)*fakultaet(m-n))
}

//Aufruf: binKoeff(3,0)
//******************************

//Aufg.2b+c auf Papier
//Aufg.2d)
def binKoeffRek(m:Int,n:Int):Int = (m,n) match {
  case (m,n) if (m==n)||(n==0) => 1
  case (m,n) => binKoeffRek(m-1,n)+binKoeffRek(m-1,n-1)
}

