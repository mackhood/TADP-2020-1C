package torneo.Regla

import Participante.Participante
import dragon.Dragon

abstract  class Regla {

  def previoPosta(participantes: Array[Participante],dragones :Array[Dragon]): Array[Participante]
  def postPosta(participantes: Array[Participante]): Array[Participante]
}
