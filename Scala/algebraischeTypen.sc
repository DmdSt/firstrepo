//Algebraische Typen: Brüche
abstract class Rational

case class Bruch(z: Int, n: Int) extends Rational {
  require(n != 0)
}

def myggT(a: Int, b: Int): Int = {
  if (a >= b) { //Achtung: Bei 5 >= -10 liefert a mod b wieder a! 5 mod -10 => 5, somit Endlosschleife!
    if (b == 0) a
    else myggT(b, a % b)
  }
  else myggT(b, a)
}

def kuerze(zaehler: Int, nenner: Int): Option[Bruch] = {
  if (nenner==0) None else {
  val d = myggT(zaehler.abs, nenner.abs) // ggt muss die abs. Werte erhalten
  Some(Bruch(zaehler / d, nenner / d)) }
}

//println(kuerze(1,0)) => liefert exception

def normiere(zaehler: Int, nenner: Int): Option[Bruch] = { //soll den Nenner positiv machen
  if (nenner==0) None
  else (kuerze(zaehler*nenner.sign, nenner.abs))
}
/*
normiere(5,-10)
normiere(0,1)
kuerze(1,0)
normiere(1,0)
*/

//Rekursive Algebraische Typen: Binärbäumchen
// Baum = Typkonstruktor, Knoten & Leer = Datenkonstruktoren
abstract class Baum
case class Knoten (wert:Int, li:Baum, re:Baum) extends Baum
case class Leer () extends Baum

val testbaum = Knoten(3,Knoten(1, Leer(), Knoten(2, Leer(), Leer())),
    Knoten(5, Knoten(4, Leer(), Leer()),
      Knoten(7, Knoten(6, Leer(), Leer()), Leer())))

// Berechnet: Höhe insg. von Wurzel bis zu letztem nicht-leeren Knoten, wobei
// Wurzelhöhe = 1, Höhe Leer() = 0
def hoeheB(b:Baum):Int = b match {
  case Leer() => 0
  case Knoten(wert,links,rechts) => 1+math.max(hoeheB(links),hoeheB(rechts))
}
//hoeheB(testbaum)

def Knotensumme(b:Baum):Int = b match {
  case Leer() => 0
  case Knoten(wert,links,rechts) => wert+Knotensumme(links)+Knotensumme(rechts)
}

def wertliste(b:Baum):List[Int] = b match {
  case Leer() => List()
  case Knoten(wert,links,rechts) => wertliste(links):::List(wert):::wertliste(rechts)
}

//Prüft ob ein Suchwert im Baum enthalten ist
def enthaelt(b:Baum,suchwert:Int):Boolean = b match {
  case Leer () => false
  case Knoten(wert,links,rechts) => (wert==suchwert)||enthaelt(links,suchwert)||enthaelt(rechts,suchwert)
}

def blattAnz(b:Baum):Int = b match {
  case Leer () => 1
  case Knoten (wert,links,rechts) => blattAnz(links)+blattAnz(rechts)
}

def innereKnotenAnz(b:Baum):Int = b match {
  case Leer () => 0
  case Knoten(wert,links,rechts) => 1 + innereKnotenAnz(links)+innereKnotenAnz(rechts)
}

//mapBaum wendet eine Funktion auf alle Knotenwerte an
def mapBaum(b:Baum, f:(Int)=>Int):Baum = b match {
  case Leer () => Leer()
  case Knoten(wert,links,rechts) => Knoten(f(wert),mapBaum(links,f),mapBaum(rechts,f))
}
//Testaufruf: wertliste(mapBaum(testbaum)((a:Int)=>2*a))

//foldbaum foldet Knotenwerte durch paarweise Anwendung der Funktion auf einen Int-Wert
def foldBaum(f:(Int,Int)=>Int,start:Int,b:Baum):Int = b match {
  case Leer() => start
  case Knoten(wert,links,rechts) => f(f(foldBaum(f,start,links),wert),(foldBaum(f,start,rechts)))
}
//testaufruf: foldBaum((a,b)=>a+b,0,testbaum)



