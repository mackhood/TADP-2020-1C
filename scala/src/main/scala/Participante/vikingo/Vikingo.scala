package Participante.vikingo

import Participante.Participante
import _root_.Participante.jinete.Jinete
import dragon.Dragon
import exceptions.NoPuedeSerMontadoException

case class Vikingo  (
                    var peso: Int,
                    var velocidad: Int,
                    var barbarosidad: Int,
                    var hambre: Int
                    //var item : ItemAgregado TODO: Implement ItemAgregado class & Arma as subclass
                  )extends Participante {

  // CONSTRUCTOR
  require(peso > 0)
  require(velocidad > 0)
  require(barbarosidad > 0)
  require(hambre >= 0)
  require(hambre <= 100)

  //METHODS
  def aumentarHambre(porcentaje: Int): Vikingo = {
    var nuevoHambre = hambre
    if (hambre + porcentaje < 100) nuevoHambre = (hambre + porcentaje) else nuevoHambre = 100
    this.copy(hambre = nuevoHambre)
  }

  def montar(unDragon: Dragon): Jinete ={
    if (unDragon.puedeSerMontadoPor(this)) new Jinete(unDragon, this) else throw new NoPuedeSerMontadoException
  }
}
