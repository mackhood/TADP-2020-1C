package torneo

import Participante.Participante
import _root_.Participante.Equipo.Equipo
import dragon.Dragon
import exceptions.EstoNoEsUnequipoException
import posta.Posta
import torneo.Regla.{Estandar, Regla}

case class TorneoPorEquipos(var equiposParticipantes: Array[Participante], _postas: Array[Posta], _dragonesDisponibles: Array[Dragon] = Array(), _regla: Regla = new Estandar()) extends Torneo(
  participantes = equiposParticipantes,
  postas = _postas,
  dragonesDisponibles = _dragonesDisponibles,
  regla = _regla
) {
  override def avanzarUnaPosta(posta: Posta, participantes: Array[Participante]): Array[Participante] = {
    var participantesGenerados: Array[Participante] = participantes.flatMap((equipo: Participante) => equipo match {
      case equipo: Equipo => equipo.participantes
      case _ => throw new EstoNoEsUnequipoException()
    }).toArray

    val resultadoPosta = _regla.postPosta(posta.podioPosta(_regla.previoPosta(participantesGenerados, _dragonesDisponibles, posta)))

    equiposParticipantes.map((unEquipo: Participante) => {
      unEquipo match {
        case a: Equipo => a.copy(a.participantes.filter(resultadoPosta.contains(_)))
      }
    })
  }

}
