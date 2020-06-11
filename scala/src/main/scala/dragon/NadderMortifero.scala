package dragon

import Participante.vikingo.Vikingo

case class NadderMortifero(
    var _peso : Int,
    var _velocidadBase : Int = 60,
    var _requisitosParaSerMontado : Array[(Dragon, Vikingo) => Boolean] = Array()
  ) extends Dragon (peso = _peso, velocidadBase = _velocidadBase, danio = 150, requisitosParaSerMontado = _requisitosParaSerMontado :+
  ((dragon: Dragon, vikingo: Vikingo) => vikingo.danio() < 150)){
}
