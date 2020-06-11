package posta
import Participante.Participante
import _root_.Participante.jinete.Jinete
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer

case class Pesca() /*extends Posta */{

  def competir(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???

  def filtrarParticipante(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???
  def nivelHambreFinalizarPosta(vikingo: Vikingo):Int = ???

  def resultadoVikingo(participante: Participante):Double = participante match {
    case vikingo: Vikingo => vikingo.peso * 0.5 + vikingo.barbarosidad *2
    case jinete: Jinete => jinete.dragon.peso * 0.2 - jinete.vikingo.peso
  }

}
