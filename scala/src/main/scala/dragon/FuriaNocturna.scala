package dragon

case class FuriaNocturna(
  var _peso : Int,
  var _velocidadBase : Int = 60,
  var _danio : Int = 0
) extends Dragon (peso = _peso, velocidadBase = _velocidadBase, danio = _danio){
  override def getVelocidad: Int = super.getVelocidad * 3
}
