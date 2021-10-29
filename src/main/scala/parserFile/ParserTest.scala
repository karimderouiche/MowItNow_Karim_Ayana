package parserFile
import scala.reflect.io.File

import parserFile.parserObj

object ParserTest extends App {

  val test0 = new parserObj()

  var test = test0.global_checks()
  println(test)

  val maxPelouse = test0.getCoord()
  println("Le max de la pelouse est " + maxPelouse)

  val coordTondeuses = test0.getCoordonneesTondeuses()
  print("les tondeuses ont pour coord " + coordTondeuses)

  val instrTondeuses = test0.getInstructionsTondeuse()
  println("\n les instructions sont " + instrTondeuses)

}
