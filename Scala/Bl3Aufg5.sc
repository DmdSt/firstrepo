//Aufg5.a)
def AnzDreieckeRek(n:Int):Int = {
  def oben(k:Int):Int = { k*(k+1)/2 }

  def unten(k:Int):Int = {
    def Sigma(n:Int,i:Int):Int = {
      if (i>n/2) 0
      else n+1-2*i + Sigma(n,i+1)
    }
    Sigma(n,1)
  }
  if (n==1) 1
  else AnzDreieckeRek(n-1)+oben(n)+unten(n)
}

AnzDreieckeRek(3)

//Aufg. 5c)
def dreieckeDiff(n:Int):Int = {
  def hilfsfunc(n:Int,i:Int,insg:Int,oben:Int,unten:Int):Int =
    if (i == n)
      insg
    else {
      val obenNeu = oben + i + 1
      val untenNeu = unten + (i + 1) / 2
      hilfsfunc(n, i + 1, insg + obenNeu + untenNeu, obenNeu, untenNeu)
    }
  hilfsfunc(n, 1, 1, 1, 0)
}
dreieckeDiff(4)