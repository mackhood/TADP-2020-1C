package torneo
import dragon.Dragon
import posta.Posta
import Participante.Participante
import _root_.Participante.Equipo.Equipo
import exceptions.EstoNoEsUnequipoException
import torneo.Regla.{Estandar, PorEquipo, Regla}

import scala.collection.mutable.ArrayBuffer

case class Torneo(
                   participantes: Array[Participante],
                   postas: Array[Posta],
                   dragonesDisponibles: Array[Dragon] = Array(),
                   regla: Regla = new Estandar()
                 ) {

    def resultadoTorneo: Array[Participante] = {
        regla match {
            case porEquipo: PorEquipo => {
                this.resolucionPorEquipos
            }
            case _ => {
                this.resolucionEstandar
            }
        }
    }

    def resolucionPorEquipos: Array[Participante] = {
        val resultado = postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta:Posta) => {
            if(participantesRestantes.length == 1) {
                participantesRestantes
            }
            else {
                var resultadoDeParticipantes = regla.postPosta(unaPosta.podioPosta(regla.previoPosta(participantesRestantes, regla.filtrarDragones(dragonesDisponibles), unaPosta)))
                var postMap = participantes.map {
                  case equipo: Equipo => {
                    equipo.participantes.filter(participante => resultadoDeParticipantes.contains(participante)).asInstanceOf[Equipo]
                  }
                }
                postMap.filter(equipo =>
                    equipo.participantes.length > 0
                ).asInstanceOf[Array[Participante]]
            }
        })
      if(resultado.isEmpty)
        resultado
      else if (resultado.length == 1)
        resultado
      else {
        resultado.sortBy((unEquipo: Array[Participante]) => case equipo: Equipo => equipo.participantes.length)
      }

    }

    def resolucionEstandar: Array[Participante] = {
        val resultado = postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta: Posta) => {
            if (participantesRestantes.length == 1) {
                participantesRestantes
            }
            else {
                regla.postPosta(unaPosta.podioPosta(regla.previoPosta(participantesRestantes, regla.filtrarDragones(dragonesDisponibles), unaPosta)))
            }
        })
        resultado
    }
}
