package dragon

import Participante.vikingo.Vikingo
import exceptions.PesoNoPuedeSerMayoraVelocidadBaseException

class Dragon(
              val peso: Int,
              val velocidadBase: Int = 60,
              val danio: Int = 0,
              val requisitosParaSerMontado: Array[(Dragon, Vikingo) => Boolean] = Array()
            ) {
  // CONSTRUCTOR
  require(peso > 0)
  require(danio >= 0)
  if (peso > velocidadBase) throw PesoNoPuedeSerMayoraVelocidadBaseException()


  // METHODS
  def getVelocidad = velocidadBase - peso

  def getDanio = danio

  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean = requisitosParaSerMontado.forall(condicion => condicion(this, unVikingo)) && unVikingo.peso <= peso * .2
}
