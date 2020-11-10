// MARKOV-Interpreter Beispiel:

type Algorithmus = List[(String, String)]
// Liste von Regeln, jeweils linke + rechte Seite
val Strich2Dual =
  List(
    ("@II", "I@"),
    ("@I" , "1"),
    ("@"  , "0"),
    ("I"  , "@I"))

// Hilfsfunktion: Bestimme aus der Liste der Regeln die erste, deren linke Seite zum
// aktuellen Kontext passt
def findeRegelNr(w:String, Regelliste:Algorithmus,regelnr:Int) : Int = Regelliste match {
  case List() => -1 //gib -1 aus, wenn alle Regeln erfolglos betrachtet wurden
  case (li,re)::restliste => if (w.indexOf(li)>=0) regelnr // die erste anwendbare Regel wurde gefunden
                             else findeRegelNr(w,restliste,regelnr+1) //weitersuchen ab nächster Regel
}

// Hilfsfunktion: Wende, falls möglich, die erste passende Regel auf den Kontext an.
// Liefere als Ergebnis die resultierende Zeichenkette sowie einen Boolean, ob ein Schritt durchgeführt wurde
def schritt(w:String, Regelliste:Algorithmus):(String,Boolean) = {
  val nr=findeRegelNr(w,Regelliste,0)
  if (nr<0) (w,false) // keine Regel gefunden, nichts geändert
  else {
    val (li, re) = Regelliste(nr) // linke,rechte Seite der anzuwendenden Regel
    val pos = w.indexOf(li) // bestimme Ausführungsposition im Kontext w
    (w.take(pos) + re + w.drop(pos + li.length), true) // Resultat und Schritt ausgeführt
  }
}
//Hilfsfunktion - arbeitet immer wieder Schritte ab, n ist Parameter für max. Anzahl von Schritten
def markovTrace (w:String, Regelliste:Algorithmus, n:Int) : List[String]
= {
  val (wneu, weiter) = schritt(w, Regelliste) // Schritt probieren, liefert für weiter true oder false
  if (n==0 || !weiter) List() // n erschöpft oder kein Schritt mehr möglich
  else wneu::markovTrace(wneu, Regelliste, n-1) // Zwischenergebnis merken und damit weitermachen
}
//MARKOV - Hauptfunktion
def markov(w:String, Regelliste:Algorithmus):String = {
  val start = w + "\n" // Zeilenumbruch hinzufügen
  (start :: markovTrace(start, Regelliste, 100)).mkString // Startzeile vor die anderen hängen
}

val Striche = "IIIIIIIIIIIIIIIII" // Strichzahl 17
println(markov(Striche, Strich2Dual)) // Aufruf Beispiel