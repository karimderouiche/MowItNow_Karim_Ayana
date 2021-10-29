package parserFile

import scala.io.Source
import scala.util.Try
import structure.{Coordonnees, Instructions}
import scala.collection.immutable._


// On crée l'objet parser qui va analyser un fichier txt de données
class parserObj {

  // La variable lines correspond à la liste des lignes du fichier
  val lines = Source.fromResource("data.txt").getLines.toList // pour accéder au fichier test dans les Ressources
  val coordonneesPelouse = lines(0).split(" ")

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

  def getCoord(): Coordonnees = {
    // On crée un nouvelle fonction qui nous permettra de retourner les coordonnées de la pelouse.
    // On définit ci-après, les coordonnées de la pelouse grâce à la première ligne du fichier.

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
  def getCoordonneesTondeuses(): List[Coordonnees]= {
    // La fonction va donc renvoyer une liste de coordonnées

    //On récupère ici les coordonnées du fichier texte de données
    val coordonneesTondeusesList = lines.drop(1).zipWithIndex.filter(_._2 % 2 == 0).map(_._1)

    //On crée ici une liste de coordonnées vide que l'on va remplir au fur et à mesure
    var listCoord =  List[Coordonnees]()

    // On crée une boucle for qui va parcourir les coordonnées, vérifier quelles sont bonnes et les ajouter à la liste.
    for(coord0 <- coordonneesTondeusesList) {
      // On crée la variable coord qui va créer une liste pour chaque tondeuse (x,y,Direction)
      val coord = coord0.split(" ")
      // On vérifie si les coordonnées ont bien été entrés, si ce n'est pas le cas on renvoie une coordonnée vide.
      if (coord.size != 3) {
        println("Les coordonnées de la pelouse entrées dans le fichiers ne sont pas bonnes")
        listCoord :+= Coordonnees(0,0,"N")
      } else {
        //On vérifie que les deux premiers caractères sont bien numériques.
        if( Try(coord(0).toInt).isSuccess && Try(coord(1).toInt).isSuccess) {
          listCoord :+= Coordonnees(coord(0).toInt,coord(1).toInt, coord(2).toString)
        } else
        {
          println("Les coordonnées de la pelouse ne sont pas numériques")
          listCoord :+= Coordonnees(0,0,"N")
        }
      }
    }
    //On revoie alors la liste des coordonnées
    listCoord
  }

  // On crée un fonction qui va renvoyer les instructions des tondeuses
  def getInstructionsTondeuse(): List[Instructions] = {

    val instructionsTondeusesList:List[String] = lines.drop(1).zipWithIndex.filter(_._2 % 2 == 1).map(_._1)
    val instrPossibles = List("A","G","D")

    var instructionsList = List[Instructions]()

    for(instr <- instructionsTondeusesList){
      var instrList:List[String] = instr.split("").toList

      // On vérifie ci-après si la différence symétrique entre les deux listes d'instructions est nulle
      if(instrList.toSet.filterNot(instrPossibles.toSet).size == 0) {
        instructionsList :+= Instructions(instrList)
      }
      else {
        // Dans le cas où les instructions ne sont pas bonnes on renvoie un pivotage vers la gauche par défaut.
        println("Les instructions ne sont pas bonnes pour la tondeuse")
        instructionsList :+= Instructions(List("G", "D"))
      }
    }
    // On revoie la liste des instructions
    instructionsList
  }



}
