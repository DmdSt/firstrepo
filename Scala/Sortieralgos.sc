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
//Hilfsfunktion "merge" - verzahnt zwei sortierte Listen zu einer sort. Liste
def merge(a:List[Int],b:List[Int]):List[Int] = (a,b) match {
  case (x::xs,y::ys) =>
    if (x<=y) x::merge(xs,b)
    else y::merge(a,ys)
  case _ => a:::b
}
//TeSt: merge(List(1,4),List(2,3))

//Hilfsfunktion "mPass" - verzahnt je zwei benachbarte Listen (unter Nutzung von merge)
def mPass(tosort:List[List[Int]],result:List[List[Int]]):List[Int] = {
  ...
}