package dragon

import Participante.vikingo.Vikingo

case class FuriaNocturna(
  var _peso : Int,
  var _velocidadBase : Int = 60,
  var _danio : Int = 0,
  var _requisitosParaSerMontado : Array[(Dragon, Vikingo) => Boolean] = Array()
) extends Dragon (peso = _peso, velocidadBase = _velocidadBase, danio = _danio, requisitosParaSerMontado = _requisitosParaSerMontado){
  override def getVelocidad: Int = super.getVelocidad * 3
}
