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
val result=Insertionsort(z)


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

    val gesamt = tosort:::result;
    if (tosort.length > 1)
      mPass(tosort.tail.tail,merge(tosort.head,tosort.tail.head)::result)
    else if (gesamt.length == 1) gesamt.head
    else mPass(gesamt, List())
  }

if (ls == List()) List()
else mPass(inEinzelne(ls),List())
}

val g=List(42, 73, 55, 10, 55, 25, 50, 400, -5)
mergeSort(g)