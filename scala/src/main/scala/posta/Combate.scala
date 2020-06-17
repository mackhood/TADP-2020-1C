package posta

import Participante.Participante
import _root_.Participante.jinete.Jinete
import _root_.Participante.vikingo.Vikingo
import items.{Arma, Comestible}

import scala.collection.mutable.ArrayBuffer

case class Combate() extends Posta {

  def competir(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???

  def filtrarParticipante(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???
  def nivelHambreFinalizarPosta(vikingo: Vikingo):Int = ???

  def resultadoParticipante(participante: Participante):Double = participante match {
    case vikingo: Vikingo => vikingo.barbarosidad.+(vikingo.item match {
      case item: Arma => item.valor
      case item: Any =>  0
    })
    case jinete: Jinete => jinete.dragon.danio + jinete.vikingo.danio()
  }


}
