package torneo

import dragon.Dragon
import posta.Posta

import scala.util.Success
import Participante.Participante
import _root_.Participante.jinete.Jinete
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer

case class Torneo(
                   participantes: Array[Participante],
                   postas: Array[Posta],
                   dragonesDisponibles: Array[Dragon] = Array()
                 ) {

  def resultadoTorneo: Array[Participante] = {postas.foldLeft(participantes)((participantesRestantes: Array[Participante], unaPosta: Posta) => {
      if (participantesRestantes.length == 1) {
        // TODO: Se ejecutan postas sin participantes
        participantesRestantes
      } else {
        unaPosta.podioPosta(prepararParticipantes(participantesRestantes, dragonesDisponibles, unaPosta))
      }
    })
  }




 def prepararParticipantes(participantes: Array[Participante],dragonesDisponibles: Array[Dragon],posta: Posta):Array[Participante] ={
    var participantesGenerados:  Array[Participante] = Array()
    var listaDragonesAuxiliares: Array[Dragon] = dragonesDisponibles.clone()

    participantes.foreach { x =>
      x match {
        case a: Vikingo => participantesGenerados.+:(a.mejorMontura(listaDragonesAuxiliares,posta))
        case _ => println("Caso todavia no contemplado")
      }

      //listaDragonesAuxiliares.filterNot(dragon_ => dragon_.estaDisponible(participantesGenerados))
    }
   participantesGenerados
    }







}
