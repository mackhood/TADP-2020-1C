package torneo

import Participante.Participante
import dragon.Dragon
import posta.Posta

import scala.util.Success


case class Torneo(
                   participantes: Array[Participante],
                   postas: Array[Posta],
                   dragonesDisponibles: Array[Dragon] = Array()
                 ) {

  def resultadoTorneo: Array[Participante] = postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta: Posta) => {
    if(participantesRestantes.length == 1) {
      // TODO: Se ejecutan postas sin participantes
      participantesRestantes
    }else{
      unaPosta.podioPosta(participantesRestantes)
    }

  })
}
