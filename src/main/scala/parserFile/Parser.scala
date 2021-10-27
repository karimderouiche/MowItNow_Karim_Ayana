package parserFile
import scala.io.Source
import parserFile.Tondeuse             //mettre le nom de fichier de tondeuse
import structure.Coordonnees
//import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position
//import org.scalatest.flatspec.AnyFlatSpec

object Parser extends App {

  val filename = "src/main/scala/reading_file/data.txt"
  val fichier = Source.fromFile(filename).getLines()

  val coordonnees = fichier.slice(0,1).toList.headOption.mkString.split(" ")

  var fichier_list = fichier.toList
  println(fichier_list)

  val size_fichier = fichier_list.size


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

package object errortests extends App {
  //test de validité du fichier test & les commandes par tondeuse
  def main(args: Array[String]): Unit = {
    var lines = Source.fromResource("test").getLines.toList // pour accéder au fichier test dans les Ressources
    val len: Int = lines.length
    if (len % 2 != 1) { //on vérifie ici si le fichier test a le bon nombre des lignes : 1 pour la taille de terrain et 2 pour chaque tondeuse
      println("Mauvais format du fichier")
    }
    else () // à mettre le reste de programme car il n'y a pas de souci de fichier test
  }

  //on capte ici les erreurs de instruction, pour ne pas avoir d'autres lettres que DGA (pattern matching)
  val instruction: = i match {
    case "D" => "la tondeuse a été pivoté à droite"
    case "G" => "la tondeuse a été pivoté à gauche"
    case "A" => "la tondeuse a avancé"
    case _ => "Commande invalide" // catch-all other case
  }

  //on gère les erreurs de positions qui doivent être que des valeurs numériques de 0 à 9 (pattern matching)
  val Coordonnees = i match {
    case i:Int => "Les coordonnées de la tondeuse sont (" + Coordonnees(x) + "," + Coordonnees(y) + ")"
    case _ => "Les coordonnées doivent être des valeurs numériques dans la plage [0; 9]"
  }

}
