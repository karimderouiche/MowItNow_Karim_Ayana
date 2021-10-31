package deplacement

import structure.{Coordonnees, Instructions, Pelouse, Tondeuse}

// Création de la classe déplacement de tondeuse
class DeplacementTondeuse {

  def move(tondeuse: Tondeuse, instructions: Instructions, pelouse: Pelouse): Unit = {
    // On parcours chaque instruction de la tondeuse
    for(instruction <- instructions.instrucs) {
      instruction match {
        // Dans un premier temps on vérifie la commande "avancer" ou "tourner"
        case "A" => tondeuse.coordonnees.orientation match
        {
          // Dans le cas d'un mouvement on modifie les coordonnées en fonction de l'orientation
          case "N" => tondeuse.coordonnees.y = tondeuse.coordonnees.y + 1
          case "W" => tondeuse.coordonnees.x = tondeuse.coordonnees.x - 1
          case "S" => tondeuse.coordonnees.y = tondeuse.coordonnees.y - 1
          case "E" => tondeuse.coordonnees.x = tondeuse.coordonnees.x + 1
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        case "G" => tondeuse.coordonnees.orientation match
        {
          // dans le cas d'une rotation sur la gauche on modifie l'orientation en conséquence
          case "N" => tondeuse.coordonnees.orientation = "W"
          case "W" => tondeuse.coordonnees.orientation = "S"
          case "S" => tondeuse.coordonnees.orientation = "E"
          case "E" => tondeuse.coordonnees.orientation = "N"
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        case "D" => tondeuse.coordonnees.orientation match
          // dans le cas d'une rotation sur la droite on modifie l'orientation en conséquence
        {
          case "N" => tondeuse.coordonnees.orientation = "E"
          case "W" => tondeuse.coordonnees.orientation = "N"
          case "S" => tondeuse.coordonnees.orientation = "W"
          case "E" => tondeuse.coordonnees.orientation = "S"
          case _ => println("Erreur, une orientation n'est pas comprise")
        }
        // Pour le reste on renvoie un message d'erreur.
        case _ => println("Erreur, une instruction n'est pas comprise")
      }
    }
    println(tondeuse.coordonnees.x + " " + + tondeuse.coordonnees.y + " " + tondeuse.coordonnees.orientation)

  }


}
