import domain._
import input_output.Printer

class Jeu(printer: Printer) {
  val maGrille = new Grille
  var aQuiLeTour: Joueur = X()

  def initGame() = {
    printer.afficherMessage("Bienvenue dans le Tic-Tac-Toe.\n")
    printer.showGrid(maGrille)
  }

  def jouerTour(): Unit = {
    val choix = listenInput("Vous êtes les " + nomJoueur(aQuiLeTour) + ". Entrez le numéro de case que vous souhaitez")

    maGrille.recupere(choix._1, choix._2) match {
      case Rien() =>
        maGrille.joue(choix._1, choix._2, aQuiLeTour)
        aQuiLeTour = contraire(aQuiLeTour)
      case _ =>
        printer.afficherMessage("CASE DÉJÀ PRISE\n")
        jouerTour()
    }
  }

  def partieTerminee() = maGrille.partieEstTerminee()

  def showGrid() = printer.showGrid(maGrille)

  //TODO : Si la grille est pleine et que quelqu'un a gagné, il va dire nul, je crois
  def declarerVainqueur() = {
    if(maGrille.partieNulle()) {
      printer.afficherMessage("La partie est nulle.")
    } else {
      printer.afficherMessage("Bravo! Le joueur " + nomJoueur(contraire(aQuiLeTour)) + " a gagné.")
    }
  }

  private def contraire(joueur: Joueur) =
    joueur match {
      case X() => O()
      case O() => X()
    }

  private def nomJoueur(joueur: Joueur) =
    joueur match {
      case X() => "X"
      case O() => "O"
    }

  private def listenInput(message: String): (Horizontal, Vertical) = {
    printer.afficherMessage(message)

    val line = io.Source.fromInputStream(System.in).bufferedReader().readLine()

    convertNumberToCase(line) match {
      case Some((x: Horizontal, y: Vertical)) => (x, y)
      case None =>
        printer.afficherMessage("ERREUR DE LECTURE\n")
        listenInput(message)
    }
  }

  private def convertNumberToCase(number: String) = {
    number match {
      case "1" => Some(Gauche(), Haut())
      case "2" => Some(CentreHorizontal(), Haut())
      case "3" => Some(Droite(), Haut())
      case "4" => Some(Gauche(), CentreVertical())
      case "5" => Some(CentreHorizontal(), CentreVertical())
      case "6" => Some(Droite(), CentreVertical())
      case "7" => Some(Gauche(), Bas())
      case "8" => Some(CentreHorizontal(), Bas())
      case "9" => Some(Droite(), Bas())
      case _ => None

    }
  }
}
