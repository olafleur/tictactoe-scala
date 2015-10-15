package domain

abstract class Etat
abstract class Joueur extends Etat
case class Rien() extends Etat

case class X() extends Joueur
case class O() extends Joueur
