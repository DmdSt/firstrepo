
//Summe der Liste berechnen
def sumlistP(l:List[Int]):Int = l match {
  case List() => 0
  case y::ys => y + sumlistP(ys)
}
//Alternative mit head/tail
def sumlist(l:List[Int]):Int = {
  if (l.length == 0) 0
  else l.head + sumlist(l.tail)
}
//Eigene Def. von length-Funktion für Liste bel. Typs
def ownlength(l:List[Any]):Int = l match {
  case List() => 0
  case y::ys => 1+ownlength(ys)
}
//Wie oft kommt Wert x in der Liste vor?
def howmany(l:List[Int],x:Int):Int = {
  if (l.length ==0) 0
  else {
    val y::ys = l
    if (x==y) 1+howmany(ys,x)
    else howmany(ys,x)
  }
}
//Schöner mit Pattern Matching:
def howmanyP(l:List[Int],x:Int): Int = l match {
  case List() => 0
  case y::ys => (if (y==x) 1 else 0) + howmanyP(ys,x)
}
// Beispielaufruf: howmanyP(List(2,3,4,5,4,2,43,5,76,7,4,112,2,3321,43,4,743,2,3),2)

//Liste umkehren mit Append Operation (:::) :
def reverselist(ls:List[_]):List[_] =  {
  if (ls.length == 1 || ls.length == 0) ls
  else reverselist(ls.tail):::ls.head::List()
}
//Effizientere Variante ohne :::
def reverselist2(ls:List[Int]):List[Int] = {
  def helpreverse(todo:List[Int],done:List[Int]):List[Int] = {
    if (todo == List()) done
    else helpreverse(todo.tail,todo.head::done)
  }
  helpreverse(ls,List())
}
//Eigene Take-Funktion (statt .take):
def owntake(ls:List[_],i:Int):List[_] = {
  if (i==0 || ls.isEmpty) List()
  else ls.head::owntake(ls.tail,i-1)
}

//Liste aufspalten in Liste von einelem. Listen
def inEinzelne(ls:List[_]):List[List[_]] = {
  if (ls.isEmpty) List()
  else List(ls.head)::inEinzelne(ls.tail)
}

//Umkehrung von inEinzelne (verschmelze zur List)
def fusetolist(lls:List[List[_]]):List[_] = {
  if (lls.isEmpty) List()
  else lls.head:::fusetolist(lls.tail)
}

//Prüft, ob eine Liste aufsteigend sortiert ist
def istAufsteigend(ls:List[Int]):Boolean = {
  if (ls.tail.isEmpty) true
  else if (ls.head <= ls.tail.head) istAufsteigend(ls.tail)
  else false
}


//Prüft, ob zwei Listen bis auf Reihenfolge gleich sind (Permutation voneinander)
def istPerm(l1:List[Int],l2:List[Int]):Boolean =  {
  val gesamtlst=l1:::l2
  def hilfsPerm(l0:List[Int],l1:List[Int],l2:List[Int]):Boolean = l0 match {
    case List() => true
    case y::ys => (howmany(l1,y)==howmany(l2,y))&&hilfsPerm(ys,l1,l2)

  }
  hilfsPerm(gesamtlst,l1,l2)
}

