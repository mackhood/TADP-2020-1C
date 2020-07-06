package torneo.Regla
import java.lang.Integer

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import dragon.Dragon
import posta.Posta

import scala.collection.mutable.ArrayBuffer

class Estandar() extends Regla {
  override def previoPosta(participantes: Array[Participante], dragones: Array[Dragon],posta: Posta): Array[Participante] = {
    if(participantes.headOption.isDefined){
      val asignado : Participante = participantes.head match {
        case a: Vikingo => a.mejorMontura(dragones,posta)
        case b => b
      }
      if(participantes.length > 1){
        previoPosta(participantes.tail, dragones.filterNot(dragon_ => dragon_.estaDisponible(participantes.+:(asignado))), posta).+:(asignado)
      }else{
        Array(asignado)
      }
    }else{
      Array()
    }
  }

  override def postPosta(participantes: Array[Participante]): Array[Participante] = {
    if(participantes.length > 1){
      participantes.splitAt(math.ceil((participantes.length / 2)).toInt)._1
    }else{
      participantes
    }
  }

  def filtrarDragones(listaDeDragones : Array[Dragon]) : Array[Dragon] = listaDeDragones
}
