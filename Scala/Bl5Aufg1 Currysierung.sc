//Aufg. 1a)
// Definiere eine anonyme Funktion, die zwei Int-Werte addiert und speichere in Variable
val add:(Int,Int)=>Int = (x:Int,y:Int)=> x+y
add(34,43)

//Aufg. 1b)
// Definierte currysierte Funktion für Multiplikation
def mult(a:Int)(b:Int):Int=a*b
val siebenmal = mult(7)_
siebenmal(8)

//Aufg. 1c)
// Baue Funktion, die nicht-currysierte Funktion mit zwei Int-Parametern entgegen nimmt und diese currysiert:

//Hauptfunktion curryZweist. nimmt 2-stellige Funktion an & liefert Hilfsfunktion curry zurück
def curryZweistelligInt(f:(Int,Int)=>Int): /*Ausgabe:*/  Int => (Int => Int) = {
  //Hilfsfunktion curry nimmt nur ein Argument und liefert dann f_y(x), also f(x,y) currysiert, siehe Beispiel unten.
  def curry(x:Int): /*Ausgabe:*/ Int => Int = (y:Int) => f(x,y)
  curry
}

//Beispielaufruf:
val t= curryZweistelligInt((a:Int,b:Int)=>a-b) // t ist jetzt die Funktion curry
val r = t(3) // t(3) ist jetzt curry(3:Int) und liefert f(3,y) = f(3)_
val s = r(5) // r(5) ist dann f(3)(5) und liefert f(3,5) = 3-5 = -2

//Aufg. 1d)
// Baue Funktion, die currysierte Funktion erhält diese ent-currysiert.
def unCurryZweistelligInt(f: Int => (Int => Int)): (Int,Int) => Int = {
  def uncurry(x:Int,y:Int): Int =  f(x)(y)
  uncurry
}

//Test: Einmal eine Multiplikationsfunktion currysieren und entcurrysieren:
def f(a:Int,b:Int):Int = a*b
val cuf = curryZweistelligInt(f)
cuf(3)(4)  // liefert 12 wie erhofft

val entcuf = unCurryZweistelligInt(cuf) // cuf von eben wird wieder ent-currysiert
entcuf(2,5) // liefert 10 wie erhofft

//Aufg. 1d)
// Nutze Funktion modifylist, um jedes Elem. einer geg. Liste um 5 zu erhöhen (mit Currying der Additionsfunktion):
def modifyList(l: List[Int], f: Int => Int): List[Int] = l match {
  case x::xs => f(x) :: modifyList(xs, f)
  case List() => List()
}
val l: List[Int] = List(1,2,3,4,5)
val curadd5 = curryZweistelligInt((x:Int,y:Int)=>x+y)(5)
val l2 = modifyList(l,curadd5)

// noch kürzer:
val l3 = modifyList(l,curryZweistelligInt((x:Int,y:Int)=>x+y)(5))