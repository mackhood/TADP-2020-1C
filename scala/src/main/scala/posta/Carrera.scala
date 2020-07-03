package posta

import Participante.vikingo.Vikingo
import Participante.Participante
import Participante.jinete.Jinete

case class Carrera(kilometros: Int = 10) extends Posta {

  def resultadoParticipante(participante: Participante): Double = participante match {
    case vikingo: Vikingo => vikingo.velocidad
    case jinete: Jinete => jinete.getVelocidad
  }

  override def aumentoDeNivelDeHambre(): Int = kilometros
}
