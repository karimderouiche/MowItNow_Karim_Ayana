package main

import parser.parserFile
import deplacement.DeplacementTondeuse
import structure.{Pelouse, Tondeuse}
import scala.reflect.io.File


object main extends App {

  // On instancie la classe parser
  val parsedFile = new parserFile()

  if(parsedFile.global_checks() == 0){
    println("Mauvais format du fichier de données")
  } else {
    val maxPelouse = parsedFile.getCoord()
    if(maxPelouse.orientation == "S"){
      println("Les coordonnées de la pelouse entrées dans le fichiers ne sont pas bonnes")
    } else if(maxPelouse.orientation == "E"){
      println("Les coordonnées de la pelouse ne sont pas numériques")
    } else{
      // On récupère les listes des coordonnées et des tondeuses
      val coordTondeuses = parsedFile.getCoordonneesTondeuses()
      val instrTondeuses = parsedFile.getInstructionsTondeuse()

      // On instancie la classe de déplacement de tondeuse
      val nvCoord = new DeplacementTondeuse()

      for(i <- 0 until coordTondeuses.size){
        val tdz:Tondeuse = Tondeuse(coordTondeuses(i), Pelouse(maxPelouse))
        println("Tondeuse " + i + " ")
        nvCoord.move(tdz, instrTondeuses(i), Pelouse(maxPelouse))
      }
    }
  }
}
