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




