package reading_file
import scala.io.Source

object reading_file extends App {

  val filename = "src/main/scala/reading_file/data.txt"
  val fichier = Source.fromFile(filename).getLines()

  for (line <- fichier) {
    println(line)
  }



}
