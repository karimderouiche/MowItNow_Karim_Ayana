package parserFile
import scala.io.Source

object Parser extends App {

  val filename = "src/main/scala/reading_file/data.txt"
  val fichier = Source.fromFile(filename).getLines()

  val coordonnees = fichier.slice(0,1).toList.headOption.mkString.split(" ")

  var fichier_list = fichier.toList
  println(fichier_list)

  val size_fichier = fichier_list.size

  //test test

  println("taille du fichier est de ")
  println(size_fichier)

  println("Les coordonnées du point supérieur du jardin sont ")
  println(coordonnees.mkString(","))

  // On crée les array de positions et d'instructions
  val positions = fichier_list.zipWithIndex.filter(_._2 % 2 == 0).map(_._1)
  val instructions = fichier_list.zipWithIndex.filter(_._2 % 2 == 1).map(_._1)

  // Il faut peut être gérer le cas où le nombre de ligne n'est pas pair

  println("Liste des positions:")
  println(positions)

  println("Liste des instructions:")
  println(instructions)

}
