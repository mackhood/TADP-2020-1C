package posta

import Participante.Participante
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer
abstract class Posta ( var requisitosParaParticipar: Array[( Participante) => Boolean] = Array() ){
  //def competir(): ArrayBuffer[Participante]
  //def filtrarParticipante(): ArrayBuffer[Participante]
  //def nivelHambreFinalizarPosta(vikingo: Vikingo): Int

  def resultadoVikingo(participante: Participante):Double

  //def tieneHambre(vikingo: Vikingo): Boolean ={
   // nivelHambreFinalizarPosta(vikingo) >100
  //}
}
