package torneo

import dragon.Dragon
import posta.Posta
import Participante.Participante
import _root_.Participante.Equipo.Equipo
import exceptions.EstoNoEsUnequipoException
import torneo.Regla.{Estandar, PorEquipo, Regla}

import scala.collection.mutable.ArrayBuffer

class Torneo(
              participantes: Array[Participante],
              postas: Array[Posta],
              dragonesDisponibles: Array[Dragon] = Array(),
              regla: Regla = new Estandar()
            ) {

  def resultadoTorneo: Array[Participante] = {
    postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta: Posta) => {
      if (participantesRestantes.length == 1) {
        participantesRestantes
      }
      else {
        unaPosta.afectarNivelDeHambre(avanzarUnaPosta(unaPosta, participantesRestantes))
      }
    })
  }

  def avanzarUnaPosta(posta: Posta, participantes: Array[Participante]): Array[Participante] = regla.postPosta(posta.podioPosta(regla.previoPosta(participantes, regla.filtrarDragones(dragonesDisponibles), posta)))
}
