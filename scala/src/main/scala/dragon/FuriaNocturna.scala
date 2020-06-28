package dragon

import Participante.vikingo.Vikingo

case class FuriaNocturna(
                          _peso: Int,
                          _velocidadBase: Int = 60,
                          _danio: Int = 0,
                          _requisitosParaSerMontado: Array[(Dragon, Vikingo) => Boolean] = Array()
                        ) extends Dragon(peso = _peso, velocidadBase = _velocidadBase, danio = _danio, requisitosParaSerMontado = _requisitosParaSerMontado) {
  override def getVelocidad: Int = super.getVelocidad * 3
}
