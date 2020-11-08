
package test
//Aufg.2a)
// Funktion die von User Eingabe einer Zahl zw 0 und 100 erfragt und diese dann durch Intervallhalbierung errät
object Bl4Aufg2Obj {
  def main(args: Array[String]): Unit = {
    zahlenRaten()
  }
  def zahlenRaten():Unit = {
    print("Bitte geben Sie eine Zahl zwischen 0 und 100 ein: ")
    val Zahl= scala.io.StdIn.readInt()
    if (Zahl < 0 || 100 < Zahl) zahlenRaten()
    else sucheZahl(Zahl,0,100)
  }

  def sucheZahl(i:Int, unten:Int, oben:Int):Boolean = {
    val Mitte = (unten+oben)/2
    if (Mitte==i) {
      println(s"Die gesuchte Zahl ist $Mitte")
      true
    } else if (i<Mitte) {
      println(s"Die gesuchte Zahl ist kleiner als $Mitte")
      sucheZahl(i,unten,Mitte-1)
    } else {
      println(s"Die gesuchte Zahl ist groesser als $Mitte")
      sucheZahl(i,Mitte+1,oben)
    }
  }
}
//Aufg.2b) Funktion, die Randomzahl zw 0 und 100 wählt und User raten lässt.
object Aufg2b {
  def main(args: Array[String]):Unit = {
    RandZahlErraten()
  }

  def RandZahlErraten():Unit = {
    val r = scala.util.Random
    //val z:Int=r.nextInt(101)
    rateweiter(r.nextInt(101))

    def rateweiter(randnum:Int):Unit = {
      print("Rate mal, welche Zahl ich ausgewählt habe: ")
      val userguess = scala.io.StdIn.readInt()
      if (userguess > randnum) {
        print("Nee, die Zahl ist kleiner als das.\n")
        rateweiter(randnum)
      } else if (userguess < randnum) {
        print("Nee, die Zahl ist groesser als das.\n")
        rateweiter(randnum)
      } else print(s"Du hast es erraten! Die Zahl ist $randnum")
    }
  }
}