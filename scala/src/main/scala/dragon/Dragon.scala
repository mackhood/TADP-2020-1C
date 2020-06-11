package dragon
import Participante.vikingo.Vikingo
import exceptions.PesoNoPuedeSerMayoraVelocidadBaseException
class Dragon(
   var peso : Int,
   var velocidadBase : Int = 60,
   var danio : Int = 0,
   var requisitosParaSerMontado : Array[(Dragon, Vikingo) => Boolean] = Array()
) {
  // CONSTRUCTOR
  require(peso > 0)
  require(danio >= 0)
  if (peso > velocidadBase) throw new PesoNoPuedeSerMayoraVelocidadBaseException()


  // METHODS
  def getVelocidad = velocidadBase - peso

  def getDanio = danio

  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean = requisitosParaSerMontado.forall(condicion => condicion(this, unVikingo)) && unVikingo.peso <= peso * .2
}
