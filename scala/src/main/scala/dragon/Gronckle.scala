package dragon

import Participante.vikingo.Vikingo

case class Gronckle(
                     _peso: Int,
                     _velocidadBase: Int = 60,
                     _requisitosParaSerMontado: Array[(Dragon, Vikingo) => Boolean] = Array(),
                     pesoMaximoDelVikingo: Int = 10e20.toInt
                   ) extends Dragon(peso = _peso, velocidadBase = _velocidadBase, requisitosParaSerMontado = _requisitosParaSerMontado :+
  ((dragon: Dragon, vikingo: Vikingo) => vikingo.peso <= pesoMaximoDelVikingo)) {
  override def getVelocidad: Int = super.getVelocidad / 2

  override def getDanio: Int = 5 * peso
}
