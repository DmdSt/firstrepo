//INSERTIONSORT - Sortiert durch Einfügen des Headelements an die richtige Stelle.
def Insertionsort(quelle:List[Int]):List[Int] = {
  //Hilfsfunktion fügt ein Element an richtiger Stelle in eine sortierte Liste ein
  def Insert(x:Int,Lst:List[Int]):List[Int] = Lst match {
    case List() => List(x)
    case y::ys => if (x<=y) x::y::ys
                  else y::Insert(x,ys)
  }
  quelle match {
    case List() => List() //empty list is already sorted
    case q::qs => Insert(q,Insertionsort(qs)) //Insert q into sorted tail-list
  }
}
val z=List(42, 73, 55, 10, 55, 25, 50, 400, -5)
val result = Insertionsort(z)


//MERGESORT - Sortiert durch Verzahnung je zweiter benachbarter Listen
//Hilfsfunktion - Liste aufspalten in Liste von einelem. Listen
def inEinzelne(ls:List[Int]):List[List[Int]] = {
  if (ls.isEmpty) List()
  else List(ls.head)::inEinzelne(ls.tail)
}

//Hauptfunktion Mergesort:
def mergeSort (ls:List[Int]):List[Int] = {

  //Hilfsfunktion "mPass" - verzahnt je zwei benachbarte Listen (unter Nutzung von merge)
  def mPass(tosort:List[List[Int]],result:List[List[Int]]):List[Int] = {

    //Hilfsfunktion "merge" - verzahnt zwei sortierte Listen zu einer sort. Liste
    def merge(a:List[Int],b:List[Int]):List[Int] = (a,b) match {
      case (x::xs,y::ys) =>
        if (x<=y) x::merge(xs,b)
        else y::merge(a,ys)
      case _ => a:::b
    }

    val gesamt = tosort:::result
    if (tosort.length > 1)
      mPass(tosort.tail.tail,merge(tosort.head,tosort.tail.head)::result)
    else if (gesamt.length == 1) gesamt.head
    else mPass(gesamt, List())
  }

if (ls == List()) List()
else mPass(inEinzelne(ls),List())
}

// MAXSORT:
//Hauptfunktion MAXSORT - sortiert Liste in aufsteigender Reihenfolge durch fortgesetzte Maximumextraktion
def maxSort(lst:List[Int]):List[Int] = {
  //Hilfsfunk. maxOfList - Liefert größtes Element einer nichtleeren Liste
  def maxOfList(ls:List[Int]):Int = {
    if (ls.tail.isEmpty) ls.head
    else if (ls.head >= maxOfList(ls.tail)) ls.head
    else maxOfList(ls.tail)
  }
  //Hilfsfunk. streiche - Falls y nicht in Liste, liefer Liste, sonst streiche das erste Vorkommen von y aus Liste.
  def streiche(Streichzahl:Int,ls:List[Int]):List[Int] = ls match {
    case y::ys => if (y==Streichzahl) ls.tail
                  else y::streiche(Streichzahl,ls.tail)
    case _ => ls
  }

  if (lst.length < 2) lst
  else {
    val max=maxOfList(lst)
    maxSort(streiche(max,lst)):::List(max)
  }
}
//Testaufrufe
val r=maxSort(List(2,3,51,2,3,1,27,0,8))
val q=maxSort(List(1,2))
val v=maxSort(List(2,1))
val w=maxSort(List(3))
val p=maxSort(List())