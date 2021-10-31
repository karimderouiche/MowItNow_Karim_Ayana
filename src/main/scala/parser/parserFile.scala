package parser

import scala.io.Source
import scala.util.Try
import structure.{Coordonnees, Instructions}
import scala.collection.immutable._


// On crée l'objet parser qui va analyser un fichier .txt de données
class parserFile {

  // La variable lines correspond à la liste des lignes du fichier
  val lines = Source.fromResource("data.txt").getLines.toList // on accède au fichier test dans les Ressources
  // On définit ci-après, les coordonnées de la pelouse grâce à la première ligne du fichier data.
  val coordonneesPelouse = lines(0).split(" ")

  def global_checks(): Int = {
    // La fonction global check vient vérifier si le fichier est au bon format. Elle renvoie 0 si le format est mauvais
    // et 1 si le format est bon.
    val len: Int = lines.length
    if (len % 2 != 1) { //on vérifie ici si le fichier test a le bon nombre des lignes : 1 pour la taille de terrain et
      // 2 pour chaque tondeuse
      0
    }
    else {
      println("Bon format du fichier de données")
      1
    }
  }

  def getCoord(): Coordonnees = {
    // On crée une nouvelle fonction qui nous permettra de retourner les coordonnées de la pelouse.
    // on vérifie si la taille des coordonnées est bonne, si ce n'est pas bon on renvoie des coordonnées de 0 et
    // une orientation Sud (0,0,"S")
    if (coordonneesPelouse.size != 2) {
      Coordonnees(0,0,"S")
    } else {
      // On vérifie si les coordonnées sont bien des int, le cas échéant on renvoie les coordonnées et une orientation N
      if( Try(coordonneesPelouse(0).toInt).isSuccess && Try(coordonneesPelouse(1).toInt).isSuccess) {
        Coordonnees(coordonneesPelouse(0).toInt,coordonneesPelouse(1).toInt, "N")
      } else // si ce n'est pas le cas on renvoie des coordonnées de 0 et une orientation E.
        {
          Coordonnees(0,0,"E")
        }
    }
  }

  // On crée une fonction qui va renvoyer les coordonnées des tondeuses
  def getCoordonneesTondeuses(): List[Coordonnees]= {
    // La fonction va donc renvoyer une liste de coordonnées

    //On récupère ici les coordonnées du fichier texte de données
    val coordonneesTondeusesList = lines.drop(1).zipWithIndex.filter(_._2 % 2 == 0).map(_._1)

    //On crée ici une liste de coordonnées vide que l'on va remplir au fur et à mesure et qui sera renvoyée
    var listCoord =  List[Coordonnees]()

    // On crée une boucle FOR qui va parcourir les coordonnées,vérifier qu'elles sont bonnes et les ajouter à la liste.
    for(coord0 <- coordonneesTondeusesList) {
      // On crée la variable coord qui va créer une liste pour chaque tondeuse (x,y,Direction)
      val coord = coord0.split(" ")
      // On vérifie si les coordonnées ont bien été entrées, si ce n'est pas le cas on renvoie des coordonnées négatives
      // On commence par la taille.
      if (coord.size != 3) {
        println("Les coordonnées de la pelouse entrées dans le fichier ne sont pas bonnes")
        listCoord :+= Coordonnees(-1,-1,"N")
      } else {
        //On vérifie après que les deux premiers caractères sont bien numériques
        // Si c'est le cas on ajoute à la liste des coordonnées les coordonnées du fichier data.
        if( Try(coord(0).toInt).isSuccess && Try(coord(1).toInt).isSuccess) {
          listCoord :+= Coordonnees(coord(0).toInt,coord(1).toInt, coord(2))
        } else // Si ce n'est pas le cas on renvoie des coordonnées négatives dans la liste
        {
          println("Les coordonnées de la pelouse ne sont pas numériques")
          listCoord :+= Coordonnees(-1,-1,"N")
        }
      }
    }
    //On revoie alors la liste des coordonnées
    listCoord
  }

  // On crée une fonction qui va renvoyer une liste d'instructions des tondeuses
  def getInstructionsTondeuse(): List[Instructions] = {

    // On récupère les instructions brutes du fichier data
    val instructionsTondeusesList:List[String] = lines.drop(1).zipWithIndex.filter(_._2 % 2 == 1).map(_._1)
    // On crée une valeur d'instructions possibles qui nous servira dans les checks
    val instrPossibles = List("A","G","D")

    // On initie la variable de liste d'instruction à remplir
    var instructionsList = List[Instructions]()

    // Boucle for qui parcourt toutes les instructions et qui les qualifie
    for(instr <- instructionsTondeusesList){
      // On prend une instruction et on la divise en liste de commandes
      val instrList: List[String] = instr.split("").toList

      // On vérifie ci-après si la différence symétrique entre les deux listes d'instructions est nulle
      if(instrList.toSet.filterNot(instrPossibles.toSet).size == 0) {
        instructionsList :+= Instructions(instrList)
      }
      else {
        // Dans le cas où les instructions ne sont pas bonnes on renvoie un pivotage vers la gauche
        // et vers la droite par défaut. La tondeuse ne bougera donc pas (et garde sa direction initiale) .
        println("Les instructions ne sont pas bonnes pour la tondeuse")
        instructionsList :+= Instructions(List("G", "D"))
      }
    }
    // On revoie la liste des instructions
    instructionsList
  }



}
