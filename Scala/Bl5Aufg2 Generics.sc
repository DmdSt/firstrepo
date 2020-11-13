//Aufg. 2a)
type Menge = Int
type Bezeichnung = String
type Stueckpreis = Double
type Kassenzetteleintrag = (Menge,Bezeichnung,Stueckpreis)
type Kassenzettel = List[Kassenzetteleintrag]

val Bon:Kassenzettel = List((1, "Milch", 0.95), (5, "Semmel", 0.35), (2, "Apfelsaft", 0.75),(3,"Milch",0.59),(2,"Klorolle",2.49))

//Aufg. 2b)
// Implementiere folgende Funktionen: kgAnzahl, kgPreis, kgName (kg=kleinergleich) für Kassenzetteleinträge:
def vergleichEintraege[T](x:T,y:T,kg:(T,T)=>Boolean):Boolean = kg(x,y)  // generische Vergleichsfunktion

def kgAnzahl2(eintrag1:Kassenzetteleintrag,eintrag2:Kassenzetteleintrag):Boolean =
  vergleichEintraege[Int](eintrag1._1,eintrag2._1,_<=_)
def kgPreis2(eintrag1:Kassenzetteleintrag,eintrag2:Kassenzetteleintrag):Boolean =
  vergleichEintraege[Double](eintrag1._3,eintrag2._3,_<=_)
def kgName2(eintrag1:Kassenzetteleintrag,eintrag2:Kassenzetteleintrag):Boolean =
  vergleichEintraege[String](eintrag1._2,eintrag2._2,_<=_)

kgAnzahl2(Bon.head,Bon.tail.head)
kgPreis2(Bon.head,Bon.tail.head)
kgName2(Bon.head,Bon.tail.head)

//ohne generische Funktion als Parameter:
def kgAnzahl(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = e1._1 <= e2._1
def kgName(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = e1._2 <= e2._2
def kgPreis(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = e1._3 <= e2._3

def Postenpreis(bonzeile:Kassenzetteleintrag):Double = bonzeile._1 * bonzeile._3
def kgPostenpreis(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = Postenpreis(e1) <= Postenpreis(e2)
def gleich(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = (e1,e2) match {
  case ((m,s,p),(m2,s2,p2)) => m==m2 && s==s2 && p==p2
}

//Aufg. 2c)
// Wandle den MaxSort-Algo in generische Variante um:

//Hauptfunktion MAXSORT - sortiert Liste in aufsteigender Reihenfolge durch fortgesetzte Maximumextraktion
def maxSort[T](grgl:(T,T)=>Boolean)(lst:List[T]):List[T] = {
  //Hilfsfunk. maxOfList - Liefert größtes Element einer nichtleeren Liste
  def maxOfList[T](grgl:(T,T)=>Boolean)(ls:List[T]):T = {
    if (ls.tail.isEmpty) ls.head
    else if (grgl(ls.head,maxOfList(grgl)(ls.tail))) ls.head
    else maxOfList(grgl)(ls.tail)
  }
  //Hilfsfunk. streiche - Falls y nicht in Liste, liefer Liste, sonst streiche das erste Vorkommen von y aus Liste.
  def streiche[T](Streichzahl:T,ls:List[T]):List[T] = ls match {
    case y::ys => if (y==Streichzahl) ls.tail
    else y::streiche(Streichzahl,ls.tail)
    case _ => ls
  }

  val max=maxOfList[T](grgl)(lst)
  if (streiche(max,lst).isEmpty) List()
  else maxSort[T](grgl)(streiche(max,lst)):::List(max)
}

def maxSortInt = maxSort[Int](_>=_)_
def maxSortString = maxSort[String](_>=_)_
def maxSortDouble = maxSort[Double](_>=_)_



val r=List(2,3,51,2,3,1,27,0,8)
val w=maxSortInt(r)
