package posta

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import exceptions.NingunParticipanteEsAdmitidoEnEstaPostaException

import scala.collection.mutable.ArrayBuffer

abstract class Posta(requisitosParaParticipar: Array[(Participante) => Boolean] = Array()) {
  //def competir(): ArrayBuffer[Participante]
  //def filtrarParticipante(): ArrayBuffer[Participante]
  //def nivelHambreFinalizarPosta(vikingo: Vikingo): Int

  def resultadoParticipante(participante: Participante): Double

  def mejorResultado(participantes: Array[Participante]):Option[Participante] = {
    mejorPosibilidad(participantes) match{
      case Array() => None
      case _ =>Option(mejorPosibilidad(participantes).head)

  }
  }

  def mejorPosibilidad(participantes: Array[Participante]):Array[Participante] = {
    val participantesAdmitidos = admisionVariosParticipantesSegunRequisitos(participantes)
    if(participantesAdmitidos.length  <= 1 ){
      participantesAdmitidos
    }else{
      participantesAdmitidos.sortBy((unParticipante: Participante) => -this.resultadoParticipante(unParticipante))
    }
  }


  def podioPosta(participantes: Array[Participante]): Array[Participante] = {
    val participantesAdmitidos = admisionVariosParticipantesSegunRequisitos(participantes)
    if(participantesAdmitidos.length == 0 ){
      throw NingunParticipanteEsAdmitidoEnEstaPostaException(this.nombre);
    }
    participantesAdmitidos.sortBy((unParticipante: Participante) => -this.resultadoParticipante(unParticipante))
  }

  def nombre = this.getClass.getSimpleName

  def admisionVariosParticipantesSegunRequisitos(participantes: Array[Participante]): Array[Participante] = participantes.filter((unParticipante: Participante) => this.admiteaParticipante(unParticipante))

  def admiteaParticipante(participante: Participante): Boolean = requisitosParaParticipar.forall(condicion => condicion(participante))


  //def tieneHambre(vikingo: Vikingo): Boolean ={
  // nivelHambreFinalizarPosta(vikingo) >100
  //}
}
