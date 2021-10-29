package deplacement

import structure.{Coordonnees, Instructions, Pelouse, Tondeuse}
import parserFile.ParserTest


class DeplacementTondeuse {

  def move(tondeuse: Tondeuse, instructions: Instructions, pelouse: Pelouse): Unit = {

    for(instruction <- instructions.instrucs) {
      instruction match {
        case "A" => tondeuse.coordonnees.orientation match
        {
          case "N" => tondeuse.coordonnees.y = tondeuse.coordonnees.y + 1
          case "W" => tondeuse.coordonnees.x = tondeuse.coordonnees.x - 1
          case "S" => tondeuse.coordonnees.y = tondeuse.coordonnees.y - 1
          case "E" => tondeuse.coordonnees.x = tondeuse.coordonnees.x + 1
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        println(tondeuse.coordonnees.x)
        println(tondeuse.coordonnees.orientation)
        case "G" => tondeuse.coordonnees.orientation match
        {
          case "N" => tondeuse.coordonnees.orientation = "W"
          case "W" => tondeuse.coordonnees.orientation = "S"
          case "S" => tondeuse.coordonnees.orientation = "E"
          case "E" => tondeuse.coordonnees.orientation = "N"
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        case "D" => tondeuse.coordonnees.orientation match
        {
          case "N" => tondeuse.coordonnees.orientation = "E"
          case "W" => tondeuse.coordonnees.orientation = "N"
          case "S" => tondeuse.coordonnees.orientation = "W"
          case "E" => tondeuse.coordonnees.orientation = "S"
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        case _ => println("Erreur, une instruction n'est pas comprise")
      }
    }
    println("Tondeuse " + tondeuse.coordonnees.x + " " + + tondeuse.coordonnees.y + " " + tondeuse.coordonnees.orientation)

  }


}
