package posta

import Participante.Participante
import _root_.Participante.jinete.Jinete
import _root_.Participante.vikingo.Vikingo
import exceptions.NingunParticipanteEsAdmitidoEnEstaPostaException

import scala.collection.mutable.ArrayBuffer

abstract class Posta(requisitosParaParticipar: Array[(Participante) => Boolean] = Array()) {
  //def competir(): ArrayBuffer[Participante]
  //def filtrarParticipante(): ArrayBuffer[Participante]
  //def nivelHambreFinalizarPosta(vikingo: Vikingo): Int
  def aumentoDeNivelDeHambre() : Int
  def resultadoParticipante(participante: Participante): Double

  def mejorResultado(participantes: Array[Participante]):Option[Participante] = {
    mejorPosibilidad(participantes).headOption
  }

  def mejorPosibilidad(participantes: Array[Participante]):Array[Participante] = {
    val participantesAdmitidos = admisionVariosParticipantesSegunRequisitos(participantes)
    participantesAdmitidos.sortBy((unParticipante: Participante) => -this.resultadoParticipante(unParticipante))
  }


  def podioPosta(participantes: Array[Participante]): Array[Participante] = {
    val participantesAdmitidos = admisionVariosParticipantesSegunRequisitos(participantes)
    if(participantesAdmitidos.length == 0 ){
      throw NingunParticipanteEsAdmitidoEnEstaPostaException(this.nombre);
    }
    this.afectarNivelDeHambre(participantesAdmitidos.sortBy((unParticipante: Participante) => -this.resultadoParticipante(unParticipante)))
  }

  def afectarNivelDeHambre(participantes: Array[Participante]): Array[Participante] = {
    participantes.map((unParticipante : Participante) => {
      unParticipante match {
        case Jinete(dragon, vikingo) => Jinete(dragon, vikingo.aumentarHambre(5))
        case a: Vikingo=> a.aumentarHambre(this.aumentoDeNivelDeHambre())
      }
    })
  }


  def nombre = this.getClass.getSimpleName

  def admisionVariosParticipantesSegunRequisitos(participantes: Array[Participante]): Array[Participante] = participantes.filter((unParticipante: Participante) => this.admiteaParticipante(unParticipante))

  def admiteaParticipante(participante: Participante): Boolean = requisitosParaParticipar.forall(condicion => condicion(participante))


  //def tieneHambre(vikingo: Vikingo): Boolean ={
  // nivelHambreFinalizarPosta(vikingo) >100
  //}
}
