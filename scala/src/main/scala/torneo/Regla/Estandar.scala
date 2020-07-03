package torneo.Regla
import java.lang.Integer

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import dragon.Dragon
import posta.Posta

case class Estandar() extends Regla {
  override def previoPosta(participantes: Array[Participante], dragones: Array[Dragon],posta: Posta): Array[Participante] = {
      val participantesGenerados:  Array[Participante] = Array()
      val listaDragonesAuxiliares: Array[Dragon] = dragones.clone()

      participantes.foreach { x =>
        x match {
          case a: Vikingo => participantesGenerados.+:(a.mejorMontura(listaDragonesAuxiliares,posta))
          case _ => println("Caso todavia no contemplado")
        }

        listaDragonesAuxiliares.filterNot(dragon_ => dragon_.estaDisponible(participantesGenerados))
      }
      participantesGenerados
    }


  override def postPosta(participantes: Array[Participante]): Array[Participante] = participantes.splitAt((participantes.length / 2).asInstanceOf[Int])._1
}
