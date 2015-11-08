package io

import domain._

trait Printer {
  def listenInput(message: String): (Horizontal, Vertical) = {
    afficherMessage(message)

    val line = userInput

    convertNumberToCase(line) match {
      case Some((x: Horizontal, y: Vertical)) => (x, y)
      case None =>
        afficherMessage("ERREUR DE LECTURE\n")
        listenInput(message)
    }
  }

  def afficherMessage(message: String): Unit

  def showGrid(grille: Grille): Unit

  def userInput: String

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
