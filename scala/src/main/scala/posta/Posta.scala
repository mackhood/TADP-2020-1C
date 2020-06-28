package posta

import Participante.Participante
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer

abstract class Posta(requisitosParaParticipar: Array[(Participante) => Boolean] = Array()) {
  //def competir(): ArrayBuffer[Participante]
  //def filtrarParticipante(): ArrayBuffer[Participante]
  //def nivelHambreFinalizarPosta(vikingo: Vikingo): Int

  def resultadoParticipante(participante: Participante): Double

  def mejorResultado(participantes: Array[Participante]) = {
    Option(podioPosta(participantes).head)
  }

  def podioPosta(participantes: Array[Participante]): Array[Participante] = admisionVariosParticipantesSegunRequisitos(participantes).sortBy((unParticipante: Participante) => -this.resultadoParticipante(unParticipante))

  def admisionVariosParticipantesSegunRequisitos(participantes: Array[Participante]): Array[Participante] = participantes.filter((unParticipante: Participante) => this.admiteaParticipante(unParticipante))

  def admiteaParticipante(participante: Participante): Boolean = requisitosParaParticipar.forall(condicion => condicion(participante))


  //def tieneHambre(vikingo: Vikingo): Boolean ={
  // nivelHambreFinalizarPosta(vikingo) >100
  //}
}
