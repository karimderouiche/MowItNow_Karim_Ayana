//import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position
//import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source
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

  //on capte ici les erreurs de direction, pour ne pas avoir d'autres lettres que DGA (pattern matching)
  val direction = i match {
    case "D" => "pivoter la tondeuse de 90° à droite"
    case "G" => "pivoter la tondeuse de 90° à gauche"
    case "A" => "Avancer"
    case _ => "Commande invalide" // catch-all case
  }

  //on gère les erreurs de positions qui doivent être que des valeurs numériques de 0 à 9 (pattern matching)
  val positions = i match {
    case positions:Int => "Les coordonnées de la tondeuse sont ($x , $y)"
    case _ => "Les coordonnées doivent être des valeurs numériques dans la plage [0; 9]"
  }

}
