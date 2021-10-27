package parserFile

import scala.io.Source
import scala.util.Try
import structure.{Coordonnees, Instructions}


// On crée l'objet parser qui va analyser un fichier txt de données
class parserObj {

  // La variable lines correspond à la liste des lignes du fichier
  val lines = Source.fromResource("data.txt").getLines.toList // pour accéder au fichier test dans les Ressources


  def global_checks(): Int = {
    // La fonction global check vient regarder si le fichier est au bon format. Elle renvoie 0 si le format est mauvais
    // et 1 si le format est bon.
    val len: Int = lines.length
    if (len % 2 != 1) { //on vérifie ici si le fichier test a le bon nombre des lignes : 1 pour la taille de terrain et 2 pour chaque tondeuse
      println("Mauvais format du fichier de données")
      0
    }
    else {
      println("Bon format du fichier de données")
      1
    }
  }

  def getMaxPelouse(): Coordonnees = {
    // On crée un nouvelle fonction qui nous permettra de retourner les coordonnées de la pelouse.
    // On définit ci-après, les coordonnées de la pelouse grâce à la première ligne du fichier.
    val coordonneesPelouse = lines(0).split(" ")

    if (coordonneesPelouse.size != 2) {
      println("Les coordonnées de la pelouse entrées dans le fichiers ne sont pas bonnes")
      Coordonnees(0,0,"N")
    } else {
      if( Try(coordonneesPelouse(0).toInt).isSuccess && Try(coordonneesPelouse(1).toInt).isSuccess) {
        Coordonnees(coordonneesPelouse(0).toInt,coordonneesPelouse(1).toInt, "N")
      } else
        {
          println("Les coordonnées de la pelouse ne sont pas numériques")
          Coordonnees(0,0,"N")
        }

    }

  }

  // On crée une fonction qui va renvoyer les coordonnées des tondeuses
  def getCoordonneesTondeuses(): Unit = {
    // Il faut changer le type de données sortant de "Unit" à List[Coordonnees] pour parfaire l'analyse
    val coordonneesTondeusesList = lines.drop(1).zipWithIndex.filter(_._2 % 2 == 0).map(_._1)

    println(coordonneesTondeusesList)

  }

  // On crée un fonction qui va renvoyer les instructions des tondeuses
  def getInstructionsTondeuse(): Instructions = {

    val instructionsTondeusesList:List[String] = lines.drop(1).zipWithIndex.filter(_._2 % 2 == 1).map(_._1)

    Instructions(instructionsTondeusesList)

  }



}
