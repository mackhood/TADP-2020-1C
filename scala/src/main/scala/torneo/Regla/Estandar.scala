package torneo.Regla
import java.lang.Integer

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import dragon.Dragon
import posta.Posta

import scala.collection.mutable.ArrayBuffer

class Estandar() extends Regla {
  override def previoPosta(participantes: Array[Participante], dragones: Array[Dragon],posta: Posta): Array[Participante] = {
      var participantesGenerados:  ArrayBuffer[Participante] = ArrayBuffer()
      var listaDragonesAuxiliares: ArrayBuffer[Dragon] = collection.mutable.ArrayBuffer(dragones: _*)


      participantes.foreach { x =>
        x match {
          case a: Vikingo => participantesGenerados.append(a.mejorMontura(listaDragonesAuxiliares.toArray,posta))
          case _ => println("Caso todavia no contemplado")
        }

        listaDragonesAuxiliares.filterNot(dragon_ => dragon_.estaDisponible(participantesGenerados.toArray))
      }
      participantesGenerados.toArray;
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
