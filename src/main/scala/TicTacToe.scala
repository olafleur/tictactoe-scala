import input_output.ConsolePrinter

object TicTacToe {
  private val printer = new ConsolePrinter
  private val jeu = new Jeu(printer)

  def main(args: Array[String]) {
    jeu.initGame()
    jeu.jouerTour()

    while (!jeu.partieTerminee()) {
      gameLoop()
    }

    jeu.showGrid()
    jeu.declarerVainqueur()
  }

  private def gameLoop() = {
    jeu.showGrid()

    if (!jeu.partieTerminee()) {
      jeu.jouerTour()
    }
  }
}
