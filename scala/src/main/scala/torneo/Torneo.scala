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

//  def resultadoTorneo: Array[Participante] = {postas.foldLeft(participantes)
//    ((participantesRestantes: Array[Participante], unaPosta: Posta) => {
//    if(participantesRestantes.length <= 1) {
//      // TODO: Se ejecutan postas sin participantes
//      participantesRestantes
//    }else{
//      unaPosta.podioPosta(participantesRestantes)
//    }
//
//  })
//  }


//  def prepararParticipantes(participantes: Array[Participante],dragonesDisponibles: Array[Dragon],posta: Posta):Array[Participante] ={
//    var participantesGenerados:  Array[Participante] = Array()
//    var listaDragonesAuxiliares: ArrayBuffer[Dragon] = dragonesDisponibles.clone()
//
//    val participante2 = Vikingo(50, 200, 5, 70)
//
////    participantes.foreach { participante =>
////       val variable = participante.match {
////         //
////       }
////    }







}
