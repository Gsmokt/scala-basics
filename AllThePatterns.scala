package part4pm

object AllThePatterns extends App{
  val x: Any = "Scala"

  val constans = x match
    case 1 => "a number"
    case "Scala" => "te scala"
    case true => "Tje truth"
    case AllThePatterns => "A sigleton object"


  val matchAnything = x match
    case _ =>

  val matchAVariable = x match
    case someting => s"$someting"
  println(matchAVariable)

  val aTuple = (1,2)
  val matchATuple = aTuple match
    case (1,1) =>
    case (something,2) => s"i have found $something"

  val nestedTuple = (1,(2,3))
  val nestedTupleMatch = nestedTuple match
    case (_,(2,v)) =>

  val aList:MyList[Int] = Cons(1,Cons(2,Empty))
  val matchAAList = aList match
    case Empty =>
    case Cons(head,Cons(subhead,subtail)) =>

  val aStandardList = List(1,2,3,42)
  val aStandardListMatching = aStandardList match
    case List(1,_,_,_) =>
    case List(1,_*) =>
    case 1 :: List(_) =>
    case List(1,2,3) :+ 42


  val unknown: Any = 2
  val unknownMatch = unknown match
    case list:List[Int] =>
    case _ =>


  val nameBindingMatch = aList match
    case nonEmptyList @ Cons(_,_) =>
    case Cons(1,rest @ Cons(2,_)) =>

  val multiPattern = aList match
    case Empty | Cons(0,_) =>

  val secondElementSpecial = aList match
    case Cons(_,Cons(specialElement,_)) if specialElement % 2 == 0 =>


  val numbers = List(1,2,3)
  val numbersMatch = numbers match

    // JVM doesn't understand generics

    case listOfString: List[String] => "some string"
    case listOfNumbers: List[Int] => "list of numbers"
    case _ => ""

  // so in this example will be print "some strings"
  // cause JVM will read List[String] as just List
  println(numbersMatch)
}
