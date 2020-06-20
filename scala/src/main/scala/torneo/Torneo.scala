package torneo

import Participante.Participante
import dragon.Dragon
import posta.Posta

case class Torneo(
  val participantes : Array[Participante],
  val postas : Array[Posta],
  val dragonesDisponibles : Array[Dragon] = Array()
  ){

  def resultadoTorneo = postas.foldLeft(participantes)((participantesRestantes : Array[Participante], unaPosta: Posta) => {
    participantesRestantes.length match {
      case 1 => participantesRestantes
      case _ => unaPosta.podioPosta(participantesRestantes)
    }
  })
}
