package domain

abstract class Etat
abstract class Joue extends Etat
case class Rien() extends Etat

case class X() extends Joue
case class O() extends Joue
