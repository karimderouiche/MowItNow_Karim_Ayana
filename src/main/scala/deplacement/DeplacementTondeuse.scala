package deplacement

import structure.{Coordonnees, Instructions, Pelouse, Tondeuse}
import parserFile.ParserTest


class DeplacementTondeuse {

  def move(tondeuse: Tondeuse, instructions: Instructions, pelouse: Pelouse): Unit = {

    for(instruction <- instructions.instrucs) {
      instruction match {
        case "A" => tondeuse.coordonnees.orientation match
        {
          case "N" => tondeuse.coordonnees.copy(y = tondeuse.coordonnees.y + 1)
          case "W" => tondeuse.coordonnees.copy(x = tondeuse.coordonnees.x - 1)
          case "S" => tondeuse.coordonnees.copy(y = tondeuse.coordonnees.y - 1)
          case "E" => tondeuse.coordonnees.copy(x = tondeuse.coordonnees.x + 1)
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        case "G" => tondeuse.coordonnees.orientation match
        {
          case "N" => tondeuse.coordonnees.copy(orientation = "W")
          case "W" => tondeuse.coordonnees.copy(orientation = "S")
          case "S" => tondeuse.coordonnees.copy(orientation = "E")
          case "E" => tondeuse.coordonnees.copy(orientation = "N")
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        case "D" => tondeuse.coordonnees.orientation match
        {
          case "N" => tondeuse.coordonnees.copy(orientation = "E")
          case "W" => tondeuse.coordonnees.copy(orientation = "S")
          case "S" => tondeuse.coordonnees.copy(orientation = "W")
          case "E" => tondeuse.coordonnees.copy(orientation = "N")
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        case _ => println("Erreur, une instruction n'est pas comprise")
      }
    }


  }


}
