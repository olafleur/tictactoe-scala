package input_output

class ConsoleReceiver extends Receiver {
  override def userInput: String = io.Source.fromInputStream(System.in).bufferedReader().readLine()
}
