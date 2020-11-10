type Kassenzettel = List[(Int,String,Double)]

val z:Kassenzettel = List((1, "Milch", 0.95), (5, "Semmel", 0.35), (2, "Apfelsaft", 0.75))

// Achtung beim Patternmatching müssen die Matchvariablennamen klein anfangen!!!
def Einkaufspreis(bon:Kassenzettel):Double = bon match {
  case List() => 0.0
  case (menge,sache,einzelpreis)::restbon => menge*einzelpreis + Einkaufspreis(restbon)
}
//Einkaufspreis(z)

