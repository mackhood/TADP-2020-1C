package posta

import Participante.Participante
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer
abstract class Posta ( var requisitosParaParticipar: Array[( Participante) => Boolean] = Array() ){
  //def competir(): ArrayBuffer[Participante]
  //def filtrarParticipante(): ArrayBuffer[Participante]
  //def nivelHambreFinalizarPosta(vikingo: Vikingo): Int

  def resultadoParticipante(participante: Participante):Double

  def mejorResultado(participantes : Array[Participante]) : Participante = {
    var participantesAdmitidos = this.admisionVariosParticipantesSegunRequisitos(participantes)
    var primerParticipante = participantesAdmitidos.head
    var otrosParticipantes = participantesAdmitidos.tail
    otrosParticipantes.foldLeft(primerParticipante)((unParticipante : Participante, otroParticipante : Participante) => {
      if (this.resultadoParticipante(unParticipante) >= this.resultadoParticipante(otroParticipante))
        unParticipante
      else
        otroParticipante
    })
  }

  def admisionVariosParticipantesSegunRequisitos (participantes : Array[Participante]) : Array[Participante] = participantes.filter((unParticipante : Participante) => this.admiteaParticipante(unParticipante))

  def admiteaParticipante(participante: Participante):Boolean = requisitosParaParticipar.forall( condicion => condicion(participante))

  //def tieneHambre(vikingo: Vikingo): Boolean ={
   // nivelHambreFinalizarPosta(vikingo) >100
  //}
}
