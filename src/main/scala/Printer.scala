trait Printer {
  def listenInput(inputMessage: String): (Horizontal, Vertical)

  def afficherMessage(message: String): Unit

  def showGrid(grille: Grille): Unit
}
