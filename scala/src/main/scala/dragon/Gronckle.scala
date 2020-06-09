package dragon

case class Gronckle(
 var _peso : Int,
 var _velocidadBase : Int = 60
) extends Dragon (peso = _peso, velocidadBase = _velocidadBase){
  override def getVelocidad: Int = super.getVelocidad /2

  override def getDanio: Int = 5 * peso
}
