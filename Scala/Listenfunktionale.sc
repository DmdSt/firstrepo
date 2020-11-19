val test= (5 to 9)
val squaretest = for (i <- (5 to 9)) yield (i,i^2)
val testalphabet = ('a' to 'z').toList

// map-Funktional "mache für jedes Element der Liste f(...)"
(1 to 7).map(i=>i*i)
("abc").toList.map((a:Char) => a.toUpper) //("abc").toList = List(a,b,c)
("bcd").toList.map((a:Char)=> a.toByte)
("bcd").toList.map(_.toByte)
("def").toList.map((a:Char)=>(a.toUpper,a.toUpper.toByte))

//ASCII Auschnitt...
//33 to 90).map((z)=>(z+" "+z.toChar+"\n")).mkString

// Rekursive Definition von "map":
def mymap[A,B](f:(A)=>B)(lst:List[A]):List[B] = lst match {
  case List() => List()
  case x::xs => f(x)::mymap(f)(xs)
}
// Definition mit Listenkomprehension:
def mymap2[A,B](f:(A)=>B)(lst:List[A]):List[B] = {
  for (x<-lst) yield f(x)
}
//Testaufruf:
val squaremymap= mymap((n:Int)=>n*n)_
val testmap = squaremymap((1 to 12).toList)
val testmap2 = mymap2((n:Int)=>n*n)((1 to 12).toList)

// filter-Funktional ^= List(...).filter(einstellige Wahrheitsfunktion/Prädikat)
def qSortF (is:List[Int]) : List[Int] = is match {
  case List() => List()
  case x :: xs => {
    val kleiner = xs.filter(_ <= x)
    val groesser = xs.filter(_ > x)
  qSortF(kleiner) ::: List(x) ::: qSortF(groesser)
  }
}

// Rekursive Definition von filter:
def myFilter[A](f:(A)=>Boolean)(lst:List[A]):List[A] = lst match {
  case List() => List()
  case x::xs => {
    if (f(x)) x::myFilter(f)(xs)
    else myFilter(f)(xs)
  }
}

// ...mit Listenkomprehension:
def myFilter2[A](f:(A)=>Boolean)(lst:List[A]):List[A] = {
  for (x <- lst; if (f(x))) yield x
}
//Testaufruf:
val nurgerade = myFilter2((n:Int)=>n%2==0)_
val filterGeradenRaus = nurgerade((1 to 15).toList)
// entspricht: (1 to 15).filter(_%2==0)

// reduce und fold:
(1 to 20).map((i) => BigInt(i)).reduceLeft(_*_)
(1 /: (1 to 6))(_*_)
val letters =List("a","b","c","d")
//noinspection ScalaDeprecation
// foldright-Funktion definiert mit: ([Startwert:B] /: [Liste/Range:A]) ((A,B)=>B)
val testfoldleft=("" /: letters)((m:String,n:String)=>{println("m:"+m+",n:"+n);m+n})

//eigene FoldRight - folded Listenelemente und gibt nur das Ergebnis aus (Liste gibt's dann nicht mehr!)
def myFoldR[A,B](startwert:B, operation:(A,B)=>B, lst:List[A]):B = lst match {
  case List() => startwert
  case y::ys => operation(y,myFoldR(startwert,operation,ys))
}

val testfR = myFoldR(0,(a:Int,b:Int)=>a+b,List(1,2,3,4,5))

//eigene FoldLeft
def myFoldL[A,B](startwert:B,operation:(A,B)=>B,lst:List[A]):B = lst match {
  case List() => startwert
  case y::ys => myFoldL(operation(y,startwert),operation,ys)
}
val testfL = myFoldR(0,(a:Int,b:Int)=>a+b,List(1,2,3,4,5).map((i:Int)=>i*i))


// Fakultät unter Nutzung von myFoldR:
def FoldRightFakultaet(i:Int):Int = {
  myFoldR[Int,Int](1,_*_,(1 to i).toList)
  //alternativ: myFoldR(1,(a:Int,b:Int)=>a*b,(1 to i).toList)
}
val fRFak = FoldRightFakultaet(6)

// Flatten-Funktion:
// Beispiel:
(List(List(1),List(),List(2,3),List(2,1))).flatten

// eigene Flatten-Funktion (für List[Int]):
def myFlattenIntList(lstoflst:List[List[Int]]):List[Int] = lstoflst match{
  case List() => List()
  case y::ys => y:::myFlattenIntList(ys)
}
myFlattenIntList((List(List(1),List(),List(2,3),List(2,1))))
// eigene Flatten-Funktion (generisch):
def myFlattenListGen[T](lstoflst:List[List[T]]):List[T] = lstoflst match {
  case List() => List()
  case y::ys => y:::myFlattenListGen(ys)
}
// alternative Definition mittels foldRight (/:) :
def FlattnLstByFoldR[T](lstoflst:List[List[T]]):List[T] = {
  (lstoflst :\ List[T]())(_:::_)
}
FlattnLstByFoldR((List(List("H"),List("A"),List("N","N"),List("A","H"))))

// FlatMap ^= map + flatten, Def. flatMap((a:T)=>T)
List("Montag","nicht","ist","Heute").map(_+" ")
List("Montag","nicht","ist","Heute").map(_+" ").reverse
List("Montag","nicht","ist","Heute").map(_+" ").reverse.map(_.toList)  // + .flatten = flatMap
List("Montag","nicht","ist","Heute").map(_+" ").reverse.flatMap(_.toList)  // = map(_.toList).flatten
List("Montag","nicht","ist","Heute").map(_+" ").reverse.flatMap(_.toList).mkString

// eigene flatMap Funktion:
      //z.B.: (lst:List[String) (f:(String)=>List[Char])
def myFlatMap[A,B](lst:List[A])(f:(A)=>List[B]): List[B] = {
  (lst.map(f) :\ List[B]())(_:::_)
}
val testmyflatmap=myFlatMap(List("Auch ","das ","noch!"))((_:String).toList)