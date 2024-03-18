package part4pm

import scala.util.Random

object PatternMatching extends App {
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  }

  println(x)
  println(description)

  case class Person(name:String,age:Int)
  val bob = Person("Bob",20)

  val greeting = bob match
    case Person(n,a) if a < 21 => s"Hi, my name is $n and i cannot drink in the usa"
    case Person(n,a) => s"Hi, my name is $n and am $a years old"
    case _ => "I do not know who I am"

  println(greeting)


  sealed class Animal
  case class Dog(bread:String) extends Animal
  case class Parrot(greeting:String) extends  Animal

  val animal:Animal = Dog("Terra Nova")
  animal match
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")


  trait Expr
  case class Number(n:Int) extends Expr
  case class Sum(el:Expr,e2:Expr) extends Expr
  case class Prod(el:Expr,e2:Expr) extends Expr

  def show(e:Expr):String = e match
    case Number(n) => s"$n"
    case Sum(el,e2) => show(el) + " + " + show(e2)
    case Prod(el,e2) => {
      def maybeShowParentheses(exp:Expr) = exp match
        case Prod(_,_) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"

      maybeShowParentheses(el) + " * " + maybeShowParentheses(e2)
    }
    println(show(Sum(Number(2),Number(3))))
}
