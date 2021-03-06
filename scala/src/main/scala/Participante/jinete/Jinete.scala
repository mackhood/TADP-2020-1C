package Participante.jinete

import Participante.Participante
import Participante.vikingo.Vikingo
import dragon.Dragon

case class Jinete(
                   dragon: Dragon,
                   vikingo: Vikingo
                 ) extends Participante {

  def getVelocidad = dragon.getVelocidad - vikingo.peso

  def getPeso = dragon.peso * 0.2 - vikingo.peso

  override def aumentarHambre(porcentaje: Int):Jinete = {
    this.copy(dragon, vikingo.aumentarHambre(5))
  }
}
