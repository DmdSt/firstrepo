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

def kgPostenpreis(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = {
  def Postenpreis(bonzeile:Kassenzetteleintrag):Double = bonzeile._1 * bonzeile._3
  Postenpreis(e1) <= Postenpreis(e2)
}

def gleich(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = (e1,e2) match {
  case ((m,s,p),(m2,s2,p2)) => m==m2 && s==s2 && p==p2
}

//Aufg. 2c)
// Wandle den MaxSort-Algo in generische Variante um:

//Hauptfunktion MAXSORT - sortiert Liste in aufsteigender Reihenfolge durch fortgesetzte Maximumextraktion
def maxSort[T](kg:(T,T)=>Boolean)(lst:List[T]):List[T] = {
  //Hilfsfunk. maxOfList - Liefert größtes Element einer nichtleeren Liste
  def maxOfList[T](kg:(T,T)=>Boolean)(ls:List[T]):T = {
    if (ls.tail.isEmpty) ls.head
    else if (kg(ls.head,maxOfList(kg)(ls.tail))) maxOfList(kg)(ls.tail)
    else ls.head
  }
  //Hilfsfunk. streiche - Falls y nicht in Liste, liefer Liste, sonst streiche das erste Vorkommen von y aus Liste.
  def streiche[T](Streichzahl:T,ls:List[T]):List[T] = ls match {
    case y::ys => if (y==Streichzahl) ls.tail
    else y::streiche(Streichzahl,ls.tail)
    case _ => ls
  }

  if (lst.length < 2) lst
  else {
    val max=maxOfList(kg)(lst)
    maxSort(kg)(streiche(max,lst)):::List(max)
  }
}

//Aufg. 2d) Bilde Sortierfunktionen für Anzahl, Name, Preis und Postenpreis (=Anzahl*Preis) eines Kassenzettels
def maxSortAnzahl = maxSort[Kassenzetteleintrag](kgAnzahl)_
def maxSortName = maxSort[Kassenzetteleintrag](kgName)_
def maxSortPreis = maxSort[Kassenzetteleintrag](kgPreis)_
def maxSortPostenpreis = maxSort[Kassenzetteleintrag](kgPostenpreis)_


val z1:Kassenzettel = List((50,"Benzin",1.37),(2,"Scheibenwischer",5.00),(1,"Autowaesche",10.00),(1,"Kaugummi",0.99))
val z1AnzahlSortiert = maxSortAnzahl(z1)
val z1NameSortiert = maxSortName(z1)
val z1PreisSortiert = maxSortPreis(z1)
val z1PostenpreisSortiert = maxSortPostenpreis(z1)

val z2:Kassenzettel = List((3,"Mars",0.80),(1,"Kaffee",1.60),(10,"Scheibenwischer",1.59))
val z2AnzahlSortiert = maxSortAnzahl(z2)
val z2NameSortiert = maxSortName(z2)
val z2PreisSortiert = maxSortPreis(z2)
val z2PostenpreisSortiert = maxSortPostenpreis(z2)

val z3:Kassenzettel = List((5,"Mars",0.80),(10,"Scheibenwischer",1.59))
val z3AnzahlSortiert = maxSortAnzahl(z3)
val z3NameSortiert = maxSortName(z3)
val z3PreisSortiert = maxSortPreis(z3)
val z3PostenpreisSortiert = maxSortPostenpreis(z3)

//Aufg. 2e)
// kgGesamtpreis vergleicht Gesamtpreise der Bons (=Summe der Postenpreise)
def kgGesamtpreis(k1:Kassenzettel,k2:Kassenzettel):Boolean = {
  def Gesamtpreis(Bon:Kassenzettel):Double = Bon match {
    case List() => 0.0
    case ((m,_,p)::xs) => m*p+Gesamtpreis(xs)
  }
  // vergleiche im Falle gleichen Gesamtpreises die Anzahl der Einträge der Bons
  if (Gesamtpreis(k1)==Gesamtpreis(k2)) k1.length <= k2.length
  else (Gesamtpreis(k1) < Gesamtpreis(k2))
}

// Funktion, die überprüft, ob zwei Bons identisch sind (hab ich nicht benötigt)
def gleichKassenzettel(k1:Kassenzettel,k2:Kassenzettel):Boolean = {
  def gleich(e1:Kassenzetteleintrag,e2:Kassenzetteleintrag):Boolean = (e1,e2) match {
    case ((m1,s1,p1),(m2,s2,p2)) => m1==m2 && s1==s2 && p1==p2
  }
  if (k1.length == k2.length) {
    if (k1.isEmpty) true
    else {
      val y::ys = k1
      val x::xs = k2
      gleich(y,x) && gleichKassenzettel(ys,xs)
    }
  }
  else false
}

//Aufg. 2f)
// Sortiere Listen nun nach dem Gesamtpreis
def maxSortGesamtpreis = maxSort[Kassenzettel](kgGesamtpreis)_
val BonsNachGesamtpreisSortiert = maxSortGesamtpreis(List(z1,z2,z3))