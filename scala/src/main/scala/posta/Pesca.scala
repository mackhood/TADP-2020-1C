package posta
import Participante.Participante
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer

case class Pesca extends Posta {

   def competir(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???
   def filtrarParticipante(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???
  def nivelHambreFinalizarPosta(vikingo: Vikingo):Int = ???
}
