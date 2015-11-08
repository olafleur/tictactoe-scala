package input_output

import domain._

trait Printer {
  def afficherMessage(message: String): Unit

  def showGrid(grille: Grille): Unit
}
