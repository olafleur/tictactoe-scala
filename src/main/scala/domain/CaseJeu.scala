package domain

abstract class CaseJeu

abstract class Horizontal extends CaseJeu
case class Gauche() extends Horizontal
case class CentreHorizontal() extends Horizontal
case class Droite() extends Horizontal

abstract class Vertical extends CaseJeu
case class Haut() extends Vertical
case class Bas() extends Vertical
case class CentreVertical() extends Vertical
