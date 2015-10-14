package ux

import domain._

class ConsolePrinter extends Printer {
  override def showGrid(grille: Grille) = {
    printLigne(grille.recupere(Gauche(), Haut()),
      grille.recupere(Centre(), Haut()),
      grille.recupere(Droite(), Haut()))
    printLigne(grille.recupere(Gauche(), Milieu()),
      grille.recupere(Centre(), Milieu()),
      grille.recupere(Droite(), Milieu()))
    printLigne(grille.recupere(Gauche(), Bas()),
      grille.recupere(Centre(), Bas()),
      grille.recupere(Droite(), Bas()))
  }

  override def afficherMessage(message: String) = {
    println(message)
  }

  override def listenInput(message: String) = {
    afficherMessage(message)

    val line = io.Source.fromInputStream(System.in).bufferedReader().readLine()

    convertNumberToCase(line) match {
      case Some((x: Horizontal, y: Vertical)) => (x, y)
      case None =>
        afficherMessage("ERREUR DE LECTURE\n")
        listenInput(message)
    }
  }

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

  private def convertNumberToCase(number: String) = {
    number match {
      case "1" => Some(Gauche(), Haut())
      case "2" => Some(Centre(), Haut())
      case "3" => Some(Droite(), Haut())
      case "4" => Some(Gauche(), Milieu())
      case "5" => Some(Centre(), Milieu())
      case "6" => Some(Droite(), Milieu())
      case "7" => Some(Gauche(), Bas())
      case "8" => Some(Centre(), Bas())
      case "9" => Some(Droite(), Bas())
      case _ => None

    }
  }
}
