package posta

import Participante.vikingo.Vikingo
import Participante.Participante
import _root_.Participante.jinete.Jinete

case class Carrera() extends Posta  {

  def resultadoParticipante(participante: Participante):Double = participante match {
    case vikingo: Vikingo => vikingo.velocidad
    case jinete: Jinete => jinete.dragon.getVelocidad - jinete.vikingo._peso
  }


}
