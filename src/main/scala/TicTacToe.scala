import io.ConsolePrinter

object TicTacToe {
  def main(args: Array[String]) {
    new Jeu(new ConsolePrinter).initGame()
  }
}
