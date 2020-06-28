package torneo

import Participante.Participante
import dragon.Dragon
import posta.Posta


case class Torneo(
                   participantes: Array[Participante],
                   postas: Array[Posta],
                   dragonesDisponibles: Array[Dragon] = Array()
                 ) {

  // Ver: Devolver try, si no tiene participantes, tirar excepcion con info de la posta
  def resultadoTorneo: Array[Participante] = postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta: Posta) => {
    // Se ejecutan postas sin participantes
    // Cual es la posta que dejo a todos afuera?
    participantesRestantes.length match { // SACAR PATTERN MATCHING + TODO: Implementar reglas
      case 1 => participantesRestantes
      case _ => unaPosta.podioPosta(participantesRestantes)
    }
  })
}
