package io

import domain._

class ConsolePrinter extends Printer {
  override def showGrid(grille: Grille) = {
    printLigne(grille.recupere(Gauche(), Haut()),
      grille.recupere(CentreHorizontal(), Haut()),
      grille.recupere(Droite(), Haut()))
    printLigne(grille.recupere(Gauche(), CentreVertical()),
      grille.recupere(CentreHorizontal(), CentreVertical()),
      grille.recupere(Droite(), CentreVertical()))
    printLigne(grille.recupere(Gauche(), Bas()),
      grille.recupere(CentreHorizontal(), Bas()),
      grille.recupere(Droite(), Bas()))
    println("\n")
  }

  override def afficherMessage(message: String) = {
    println(message)
  }

  override def userInput = io.Source.fromInputStream(System.in).bufferedReader().readLine()

  private def printLigne(etat1: Etat, etat2: Etat, etat3: Etat) = {
    println(affiche(etat1) + affiche(etat2) + affiche(etat3))
  }

  private def affiche(etat: Etat) = {
    etat match {
      case Rien() => "_ "
      case X() => "X "
      case O() => "O "
    }
  }
}
