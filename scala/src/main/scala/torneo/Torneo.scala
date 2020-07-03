package torneo

import dragon.Dragon
import posta.Posta

import scala.util.Success
import Participante.Participante
import _root_.Participante.jinete.Jinete
import _root_.Participante.vikingo.Vikingo
import torneo.Regla.{Estandar, Regla}

import scala.collection.mutable.ArrayBuffer

case class Torneo(
                   participantes: Array[Participante],
                   postas: Array[Posta],
                   dragonesDisponibles: Array[Dragon] = Array(),
                   regla: Regla= Estandar()
                 ) {

  def resultadoTorneo: Array[Participante] = {postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta: Posta) => {

    if (participantesRestantes.length == 1) {
        // TODO: Se ejecutan postas sin participantes
        participantesRestantes
      } else {

        regla.postPosta(unaPosta.podioPosta(regla.previoPosta(participantesRestantes, dragonesDisponibles, unaPosta)))
    }
    })
  }


}
