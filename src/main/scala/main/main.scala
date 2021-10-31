package main

import parser.parserFile
import deplacement.DeplacementTondeuse
import structure.{Pelouse, Tondeuse}
import scala.reflect.io.File


object main extends App {

  // On instancie la classe parser
  val parsedFile = new parserFile()

  // On vient tout d'abord effectuer quelques vérifications de base issues de la méthode "global_check"
  if(parsedFile.global_checks() == 0){
    println("Mauvais format du fichier de données")
  } else {
    // Si les vérifications sont bonnes on passe à la vérification des coordonnées de la pelouse
    val maxPelouse = parsedFile.getCoord()
    if(maxPelouse.orientation == "S"){
      // Si un "S" est renvoyé, nous avons programmé dans la méthode getCoord() que les coordonnées du fichiers ne sont
      // pas bonnes
      println("Les coordonnées de la pelouse entrées dans le fichiers ne sont pas bonnes")
    } else if(maxPelouse.orientation == "E"){
      // Si un "E" est renvoyé, nous avons programmé dans la méthode getCoord() que les coordonnées du fichiers ne sont
      // pas numériques
      println("Les coordonnées de la pelouse ne sont pas numériques")
    } else{
      // On récupère les listes des coordonnées et des tondeuses
      val coordTondeuses = parsedFile.getCoordonneesTondeuses()
      val instrTondeuses = parsedFile.getInstructionsTondeuse()

      // On instancie la classe de déplacement de tondeuse
      val nvCoord = new DeplacementTondeuse()

      // On établit une boucle for pour parcourir toutes les tondeuses (coordonnées et instructions) et renvoyer le
      // résultat escompté
      for(i <- 0 until coordTondeuses.size){
        val tdz:Tondeuse = Tondeuse(coordTondeuses(i), Pelouse(maxPelouse))
        println("Tondeuse " + (i+1) + " ")
        nvCoord.move(tdz, instrTondeuses(i), Pelouse(maxPelouse))
      }
    }
  }
}
