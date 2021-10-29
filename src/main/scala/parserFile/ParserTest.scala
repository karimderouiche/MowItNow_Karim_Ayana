package parserFile
import scala.reflect.io.File

import parserFile.parserObj

object ParserTest extends App {

  val test0 = new parserObj()

  var test = test0.global_checks()
  println(test)

  val maxPelouse = test0.getMaxPelouse()
  println(maxPelouse)

  val coordTondeuses = test0.getCoordonneesTondeuses()
  print(coordTondeuses)

  val instrTondeuses = test0.getInstructionsTondeuse()
  println(instrTondeuses)

}