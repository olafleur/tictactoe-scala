class Jeu(printer: Printer) {
  val maGrille = new Grille
  var aQuiLeTour: Joue = X()

  def initGame() = {
    printer.afficherMessage("Bienvenue dans le Tic-Tac-Toe, veuillez entrer votre premier mouvement\n")
    printer.showGrid(maGrille)

    jouerTour()

    while (!partieEstTerminee()) {
      gameLoop()
    }

    printer.showGrid(maGrille)
    declarerVainqueur()
  }

  private def gameLoop() = {
    printer.showGrid(maGrille)

    if (!partieEstTerminee()) {
      jouerTour()
    }
  }

  private def jouerTour(): Unit = {
    val choix = printer.listenInput("Vous êtes les " + aQuiLeTour + ". Entrez le numéro de case que vous souhaitez")

    maGrille.recupere(choix._1, choix._2) match {
      case Rien() =>
        maGrille.joue(choix._1, choix._2, aQuiLeTour)
        aQuiLeTour match {
          case X() => aQuiLeTour = O()
          case O() => aQuiLeTour = X()
        }
      case _ =>
        printer.afficherMessage("CASE DÉJÀ PRISE\n")
        jouerTour()
    }
  }

  private def partieEstTerminee(): Boolean = {
    ligneVerticale() || ligneHorizontale() || ligneDiagonale()
  }

  private def ligneVerticale() = ligneVerticale1() || ligneVerticale2() || ligneVerticale3()

  private def ligneHorizontale() = ligneHorizontale1() || ligneHorizontale2() || ligneHorizontale3()

  private def ligneDiagonale() = ligneDiagonaleGD() || ligneDiagonaleDG()

  private def ligneVerticale1() =
    maGrille.recupere(Gauche(), Haut()) == maGrille.recupere(Gauche(), Milieu()) &&
      maGrille.recupere(Gauche(), Milieu()) == maGrille.recupere(Gauche(), Bas()) &&
      maGrille.recupere(Gauche(), Milieu()) != Rien()

  private def ligneVerticale2() = maGrille.recupere(Centre(), Haut()) == maGrille.recupere(Centre(), Milieu()) &&
    maGrille.recupere(Centre(), Milieu()) == maGrille.recupere(Centre(), Bas()) &&
    maGrille.recupere(Centre(), Milieu()) != Rien()

  private def ligneVerticale3() = maGrille.recupere(Droite(), Haut()) == maGrille.recupere(Droite(), Milieu()) &&
    maGrille.recupere(Droite(), Milieu()) == maGrille.recupere(Droite(), Bas()) &&
    maGrille.recupere(Droite(), Milieu()) != Rien()

  def ligneHorizontale1() = maGrille.recupere(Gauche(), Haut()) == maGrille.recupere(Centre(), Haut()) &&
    maGrille.recupere(Centre(), Haut()) == maGrille.recupere(Droite(), Haut()) &&
    maGrille.recupere(Gauche(), Haut()) != Rien()

  def ligneHorizontale2() = maGrille.recupere(Gauche(), Milieu()) == maGrille.recupere(Centre(), Milieu()) &&
    maGrille.recupere(Centre(), Milieu()) == maGrille.recupere(Droite(), Milieu()) &&
    maGrille.recupere(Gauche(), Milieu()) != Rien()

  def ligneHorizontale3() = maGrille.recupere(Gauche(), Bas()) == maGrille.recupere(Centre(), Bas()) &&
    maGrille.recupere(Centre(), Bas()) == maGrille.recupere(Droite(), Bas()) &&
    maGrille.recupere(Gauche(), Bas()) != Rien()

  def ligneDiagonaleGD() = maGrille.recupere(Gauche(), Haut()) == maGrille.recupere(Centre(), Milieu()) &&
    maGrille.recupere(Centre(), Milieu()) == maGrille.recupere(Droite(), Bas()) &&
    maGrille.recupere(Gauche(), Haut()) != Rien()

  def ligneDiagonaleDG() = maGrille.recupere(Droite(), Haut()) == maGrille.recupere(Centre(), Milieu()) &&
    maGrille.recupere(Centre(), Milieu()) == maGrille.recupere(Gauche(), Bas()) &&
    maGrille.recupere(Droite(), Milieu()) != Rien()

  def declarerVainqueur() = printer.afficherMessage("Bravo! Le joueur " + aQuiLeTour + " a perdu.")
}
