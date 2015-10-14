import domain._
import ux.Printer

class Jeu(printer: Printer) {
  val maGrille = new Grille
  var aQuiLeTour: Joue = X()

  def initGame() = {
    printer.afficherMessage("Bienvenue dans le Tic-Tac-Toe, veuillez entrer votre premier mouvement\n")
    printer.showGrid(maGrille)

    jouerTour()

    while (!maGrille.partieEstTerminee()) {
      gameLoop()
    }

    printer.showGrid(maGrille)
    declarerVainqueur()
  }

  private def gameLoop() = {
    printer.showGrid(maGrille)

    if (!maGrille.partieEstTerminee()) {
      jouerTour()
    }
  }

  private def jouerTour(): Unit = {
    val choix = printer.listenInput("Vous êtes les " + aQuiLeTour + ". Entrez le numéro de case que vous souhaitez")

    maGrille.recupere(choix._1, choix._2) match {
      case Rien() =>
        maGrille.joue(choix._1, choix._2, aQuiLeTour)
        aQuiLeTour = contraire(aQuiLeTour)
      case _ =>
        printer.afficherMessage("CASE DÉJÀ PRISE\n")
        jouerTour()
    }
  }

  //TODO : Si la grille est pleine et que quelqu'un a gagné, il va dire nul, je crois
  private def declarerVainqueur() = {
    if(maGrille.partieNulle()) {
      printer.afficherMessage("La partie est nulle.")
    } else {
      printer.afficherMessage("Bravo! Le joueur " + contraire(aQuiLeTour) + " a gagné.")
    }
  }

  private def contraire(joueur: Joue) =
    joueur match {
      case X() => O()
      case O() => X()
    }
}
