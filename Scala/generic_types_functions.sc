//Beispiel Currying - nicht unbedingt sinvoll, aber demonstrativ
def Multi(x:Int)(y:Int)=x*y

val elfmal=Multi(11)_
val zwoelfmal=Multi(12)_

val r=elfmal(3)
val s=zwoelfmal(4)

def willkommensgruss(name:String)(alter:Int)={
  println("Lieber "+name+", willkommen bei Cinemaxx' Horrormovies!")
  if (alter<18) println("Leider bist du zu jung f\u00FCr diese Filme.")
  else println("Du hast freie Filmwahl! Viel Spaß!")
}

val gruss=willkommensgruss("Horrorfan")_
gruss(21)

//Beispiel Anonyme Funktion:
val add= (x:Int,y:Int)=>x+y // links wird der Variable die anonyme Funktion (x:Int,y:Int)=>x+y zugewiesen
add(17,4)



//Beispiel Generische Funktionen:
def klebe[T] (list1:List[T], list2:List[T]) : List[T] = //das [T] hinter Klebe definiert die generische Art
  list1 match {
    case List() => list2
    case x::xs => x::klebe(xs, list2)
  } //Listenelemente können Int, String o.ä. sein

klebe(List(1, 2), List(3,4,5))
klebe(List('a', 'b'), List('c'))

//bei Currysierung generischer Funktion muss explizit Typ vergeben werden
val klebInt = klebe[Int]_
klebInt(List(3,2,1),List(0,-1,-2))

val klebString = klebe[String]_
klebString(List("Hallo "),List("Zelda"))

//Beispiel Insertionsort - generisch, dazu muss die <= Prüfung in der Hilfsfunktion angepasst werden für generische Typen
def Insertionsort[T](kg:(T,T)=>Boolean)(quelle:List[T]):List[T] = {
  //Hilfsfunktion fügt ein Element an richtiger Stelle in eine sortierte Liste ein
  def Insert(kleinergleich:(T,T)=>Boolean,x:T,Lst:List[T]):List[T] = Lst match {
    case List() => List(x)
    case y::ys => if (kleinergleich(x,y)) x::y::ys
    else y::Insert(kg,x,ys)
  }
  quelle match {
    case List() => List()
    case q::qs => Insert(kg,q,Insertionsort(kg)(qs))
  }
}
//currysierter Aufruf dann z.B. mit:
val sortInt = Insertionsort[Int](_<=_)_
val beispiel = sortInt(List(2,1,6,4,8,0))

val sortStrings = Insertionsort[String](_<=_)_
val beispiel2 = sortStrings(List("Birne","Ananas","Apfel","Banane"))
val beispiel3 = sortStrings(List("The", "quick", "brown", "fox", "jumped", "over", "the",
"lazy", "dog"))


















