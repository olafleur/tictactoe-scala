package domain

class Grille {
  private var cases: Map[(Horizontal, Vertical), Etat] = Map(
    (Gauche(), Haut()) -> Rien(),
    (Gauche(), Milieu()) -> Rien(),
    (Gauche(), Bas()) -> Rien(),
    (Centre(), Haut()) -> Rien(),
    (Centre(), Bas()) -> Rien(),
    (Centre(), Milieu()) -> Rien(),
    (Droite(), Haut()) -> Rien(),
    (Droite(), Milieu()) -> Rien(),
    (Droite(), Bas()) -> Rien()
  )

  def joue(x: Horizontal, y: Vertical, joue: Joue) = {
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
    cases(Gauche(), Haut()) == cases(Gauche(), Milieu()) &&
      cases(Gauche(), Milieu()) == cases(Gauche(), Bas()) &&
      cases(Gauche(), Milieu()) != Rien()

  private def ligneVerticale2() = cases(Centre(), Haut()) == cases(Centre(), Milieu()) &&
    cases(Centre(), Milieu()) == cases(Centre(), Bas()) &&
    cases(Centre(), Milieu()) != Rien()

  private def ligneVerticale3() = cases(Droite(), Haut()) == cases(Droite(), Milieu()) &&
    cases(Droite(), Milieu()) == cases(Droite(), Bas()) &&
    cases(Droite(), Milieu()) != Rien()

  private def ligneHorizontale1() = cases(Gauche(), Haut()) == cases(Centre(), Haut()) &&
    cases(Centre(), Haut()) == cases(Droite(), Haut()) &&
    cases(Gauche(), Haut()) != Rien()

  private def ligneHorizontale2() = cases(Gauche(), Milieu()) == cases(Centre(), Milieu()) &&
    cases(Centre(), Milieu()) == cases(Droite(), Milieu()) &&
    cases(Gauche(), Milieu()) != Rien()

  private def ligneHorizontale3() = cases(Gauche(), Bas()) == cases(Centre(), Bas()) &&
    cases(Centre(), Bas()) == cases(Droite(), Bas()) &&
    cases(Gauche(), Bas()) != Rien()

  private def ligneDiagonaleGD() = cases(Gauche(), Haut()) == cases(Centre(), Milieu()) &&
    cases(Centre(), Milieu()) == cases(Droite(), Bas()) &&
    cases(Gauche(), Haut()) != Rien()

  private def ligneDiagonaleDG() = cases(Droite(), Haut()) == cases(Centre(), Milieu()) &&
    cases(Centre(), Milieu()) == cases(Gauche(), Bas()) &&
    cases(Droite(), Milieu()) != Rien()
}
