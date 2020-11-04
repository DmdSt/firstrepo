//Aufg.1a)
type Nametyp = (String, String)
type Emailtyp = List[String]
type Telefontyp = List[String]
type Kontakttyp = (Nametyp, Emailtyp, Telefontyp)

val kontakt1: Kontakttyp = (("Micha","Mueller"),List("m.muelli@gmx.de"),List("03009823409","0152190934"))
val kontakt2: Kontakttyp = (("Steffi","Graf"),List("s.graf@web.de","steffi234@sexchat.de"),List("39302100222"))
val kontakt3: Kontakttyp = (("Boris","Becker"),List("bor-beck@gmail.com"),Nil)

val Kontaktverzeichnis = List(kontakt1,kontakt2,kontakt3)
//***********************************************************

//Aufg.1b:
//Hilfsfunktion für Zugriff auf Mailadressen eines Kontakts
def MailsFromContact(contact:Kontakttyp):List[String] = contact._2

//Hauptfunktion, die alle Mailadressen als Liste herausgibt
def allMails(biglist:List[Kontakttyp]):List[String] = biglist match {
  case x::xs => MailsFromContact(x):::allMails(xs)
  case Nil => List()
}
//Testaufruf: allMails(Kontaktverzeichnis)
//****************************************************

//Aufg.1c:
// Hilfsfunktion: guckt, ob tel in Liste d. Tel.nummern
def isNumberInList(tellist:Telefontyp,tel:String):Boolean = tellist match{
  case x::xs if (x == tel) => true
  case x::xs => isNumberInList(xs,tel)
  case _ => false
}

//Hauptfunktion: sucht Namen zur Tel
def NameForNumber(contactlst:List[Kontakttyp],tel:String):String = contactlst match {
  case y::ys if (isNumberInList(y._3,tel)) => y._1._1 // liefert Vorname aus (Vorn.,Name)
  case y::ys if (ys.isEmpty) =>  "nix gefunden"
  case y::ys => NameForNumber(ys,tel)
}

NameForNumber(Kontaktverzeichnis,"0152190934")