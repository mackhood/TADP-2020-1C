package dragon

case class NadoerMortifero(
    var _peso : Int,
    var _velocidadBase : Int = 60
  ) extends Dragon (peso = _peso, velocidadBase = _velocidadBase, danio = 150){
}
