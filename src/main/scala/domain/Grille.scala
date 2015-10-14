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
}
