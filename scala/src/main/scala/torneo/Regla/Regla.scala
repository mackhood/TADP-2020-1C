package torneo.Regla

import Participante.Participante
import dragon.Dragon
import posta.Posta

abstract  class Regla {
  def previoPosta(participantes: Array[Participante],dragones :Array[Dragon], posta: Posta): Array[Participante]
  def postPosta(participantes: Array[Participante]): Array[Participante]
  def filtrarDragones(listaDeDragones : Array[Dragon]) : Array[Dragon]
}
