package dragon

class Dragon(
   var peso : Int,
   var velocidadBase : Int = 60,
   var danio : Int = 0
){
  // CONSTRUCTOR
  require(peso > 0)
  require(danio >= 0)
  if(peso > velocidadBase) throw new PesoNoPuedeSerMayoraVelocidadBaseException()


  // METHODS
  def getVelocidad = velocidadBase - peso
  def getDanio = danio
}

final case class PesoNoPuedeSerMayoraVelocidadBaseException(private val message: String = "",
                                 private val cause: Throwable = None.orNull)
  extends Exception(message, cause)