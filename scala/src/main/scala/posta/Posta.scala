package posta

import Participante.Participante
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer

trait Posta {
  def competir(): ArrayBuffer[Participante]
  def filtrarParticipante(): ArrayBuffer[Participante]
  def nivelHambreFinalizarPosta(vikingo: Vikingo): Int

  def tieneHambre(vikingo: Vikingo): Boolean ={
    nivelHambreFinalizarPosta(vikingo) >100
  }
}
