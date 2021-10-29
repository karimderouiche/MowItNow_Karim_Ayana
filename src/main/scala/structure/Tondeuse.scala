package structure

case class Tondeuse(coordonnees: Coordonnees, pelouse: Pelouse){

  try {
    assert(coordonnees.x < pelouse.coinSuperieur.x && coordonnees.y < pelouse.coinSuperieur.y &&
      coordonnees.x > 0 && coordonnees.y > 0)
  } catch {
    case _ => println("La tondeuse est sortie de la pelouse !")
  }

}
