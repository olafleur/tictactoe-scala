import input_output.{ConsoleReceiver, ConsolePrinter}

object TicTacToe {
  def main(args: Array[String]) {
    new Jeu(new ConsolePrinter, new ConsoleReceiver).initGame()
  }
}
