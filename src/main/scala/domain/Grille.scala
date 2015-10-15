package domain

class Grille {
  private var cases: Map[(Horizontal, Vertical), Etat] = Map(
    (Gauche(), Haut()) -> Rien(),
    (Gauche(), CentreVertical()) -> Rien(),
    (Gauche(), Bas()) -> Rien(),
    (CentreHorizontal(), Haut()) -> Rien(),
    (CentreHorizontal(), Bas()) -> Rien(),
    (CentreHorizontal(), CentreVertical()) -> Rien(),
    (Droite(), Haut()) -> Rien(),
    (Droite(), CentreVertical()) -> Rien(),
    (Droite(), Bas()) -> Rien()
  )

  def joue(x: Horizontal, y: Vertical, joue: Joueur) = {
    if (cases(x, y).equals(Rien())) {
      cases = cases.updated((x, y), joue)
    }
  }

  def recupere(x: Horizontal, y: Vertical) = {
    cases(x, y)
  }

  def partieEstTerminee(): Boolean = {
    ligneVerticale() || ligneHorizontale() || ligneDiagonale() || partieNulle()
  }

  def partieNulle(): Boolean = {
    var trouveVide = false

    for(caseJeu <- cases) {
      if(caseJeu._2 == Rien()) {
        trouveVide = true
      }
    }

    !trouveVide
  }

  private def ligneVerticale() = ligneVerticale1() || ligneVerticale2() || ligneVerticale3()

  private def ligneHorizontale() = ligneHorizontale1() || ligneHorizontale2() || ligneHorizontale3()

  private def ligneDiagonale() = ligneDiagonaleGD() || ligneDiagonaleDG()

  private def ligneVerticale1() =
    cases(Gauche(), Haut()) == cases(Gauche(), CentreVertical()) &&
      cases(Gauche(), CentreVertical()) == cases(Gauche(), Bas()) &&
      cases(Gauche(), CentreVertical()) != Rien()

  private def ligneVerticale2() = cases(CentreHorizontal(), Haut()) == cases(CentreHorizontal(), CentreVertical()) &&
    cases(CentreHorizontal(), CentreVertical()) == cases(CentreHorizontal(), Bas()) &&
    cases(CentreHorizontal(), CentreVertical()) != Rien()

  private def ligneVerticale3() = cases(Droite(), Haut()) == cases(Droite(), CentreVertical()) &&
    cases(Droite(), CentreVertical()) == cases(Droite(), Bas()) &&
    cases(Droite(), CentreVertical()) != Rien()

  private def ligneHorizontale1() = cases(Gauche(), Haut()) == cases(CentreHorizontal(), Haut()) &&
    cases(CentreHorizontal(), Haut()) == cases(Droite(), Haut()) &&
    cases(Gauche(), Haut()) != Rien()

  private def ligneHorizontale2() = cases(Gauche(), CentreVertical()) == cases(CentreHorizontal(), CentreVertical()) &&
    cases(CentreHorizontal(), CentreVertical()) == cases(Droite(), CentreVertical()) &&
    cases(Gauche(), CentreVertical()) != Rien()

  private def ligneHorizontale3() = cases(Gauche(), Bas()) == cases(CentreHorizontal(), Bas()) &&
    cases(CentreHorizontal(), Bas()) == cases(Droite(), Bas()) &&
    cases(Gauche(), Bas()) != Rien()

  private def ligneDiagonaleGD() = cases(Gauche(), Haut()) == cases(CentreHorizontal(), CentreVertical()) &&
    cases(CentreHorizontal(), CentreVertical()) == cases(Droite(), Bas()) &&
    cases(Gauche(), Haut()) != Rien()

  private def ligneDiagonaleDG() = cases(Droite(), Haut()) == cases(CentreHorizontal(), CentreVertical()) &&
    cases(CentreHorizontal(), CentreVertical()) == cases(Gauche(), Bas()) &&
    cases(Droite(), CentreVertical()) != Rien()
}
