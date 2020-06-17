package posta
import Participante.Participante
import _root_.Participante.jinete.Jinete
import _root_.Participante.vikingo.Vikingo

import scala.collection.mutable.ArrayBuffer

case class Pesca(var _requisitosParaParticipar : Array[(Participante) => Boolean] = Array())
          extends Posta ( requisitosParaParticipar = _requisitosParaParticipar){

  //def competir(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???

 // def filtrarParticipante(participantes:ArrayBuffer[Participante]): ArrayBuffer[Participante] = ???
 // def nivelHambreFinalizarPosta(vikingo: Vikingo):Int = ???

  def resultadoParticipante(participante: Participante):Double = participante match {
    case vikingo: Vikingo => vikingo._peso * 0.5 + vikingo.barbarosidad *2
    case jinete: Jinete => jinete.dragon.peso * 0.2 - jinete.vikingo._peso
  }



  def agregarRequisitoPeso(valor: Int):Unit = {

    val requisito = (x: Participante) => x match {
      case Jinete(dragon, vikingo) => ((dragon.peso * .2) - vikingo._peso) >= valor
      case Vikingo(_peso, velocidad, barbarosidad, hambre, item) => (_peso *.5 + barbarosidad * 2) >= valor
    }
    _requisitosParaParticipar :+ requisito

  }


}
