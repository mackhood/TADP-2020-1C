package dragon

import Participante.vikingo.Vikingo

case class NadderMortifero(
    _peso : Int,
    _velocidadBase : Int = 60,
    _requisitosParaSerMontado : Array[(Dragon, Vikingo) => Boolean] = Array()
  ) extends Dragon (peso = _peso, velocidadBase = _velocidadBase, danio = 150, requisitosParaSerMontado = _requisitosParaSerMontado :+
  ((dragon: Dragon, vikingo: Vikingo) => vikingo.danio() < 150)){
}
