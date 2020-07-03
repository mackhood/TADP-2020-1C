package torneo.Regla
import java.lang.Integer

import Participante.Participante
import dragon.Dragon

case class Estandar() extends Regla {
  override def previoPosta(participantes: Array[Participante], dragones: Array[Dragon]): Array[Participante] = ???

  override def postPosta(participantes: Array[Participante]): Array[Participante] = participantes.splitAt((participantes.length / 2).asInstanceOf[Int])._1
}
