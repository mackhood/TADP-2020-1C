package torneo
import dragon.Dragon
import posta.Posta
import Participante.Participante
import torneo.Regla.{Estandar, Regla}

case class Torneo(
                   participantes: Array[Participante],
                   postas: Array[Posta],
                   dragonesDisponibles: Array[Dragon] = Array(),
                   regla: Regla = new Estandar()
                 ) {

  def resultadoTorneo: Participante = {
    val resultado = postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta: Posta) => {

      if (participantesRestantes.length == 1) {
        // TODO: Se ejecutan postas sin participantes
        participantesRestantes
      } else {

        regla.postPosta(unaPosta.podioPosta(regla.previoPosta(participantesRestantes, regla.filtrarDragones(dragonesDisponibles), unaPosta)))
      }
    })
    resultado.head
  }

}
