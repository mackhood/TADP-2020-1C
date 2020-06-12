package dragon

import Participante.vikingo.Vikingo

case class Gronckle(
 var _peso : Int,
 var _velocidadBase : Int = 60,
 var _requisitosParaSerMontado : Array[(Dragon, Vikingo) => Boolean] = Array(),
 var pesoMaximoDelVikingo : Int = 10e20.toInt
) extends Dragon (peso = _peso, velocidadBase = _velocidadBase, requisitosParaSerMontado = _requisitosParaSerMontado :+
  ((dragon: Dragon, vikingo: Vikingo) => vikingo._peso <= pesoMaximoDelVikingo)){
  override def getVelocidad: Int = super.getVelocidad /2

  override def getDanio: Int = 5 * peso
}
