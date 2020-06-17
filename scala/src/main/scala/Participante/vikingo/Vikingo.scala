package Participante.vikingo

import Participante.Participante
import _root_.Participante.jinete.Jinete
import dragon.Dragon
import exceptions.NoPuedeSerMontadoException
import items.Item
import posta.Posta

case class Vikingo  (
                      var _peso: Int,
                      var velocidad: Int,
                      var barbarosidad: Double,
                      var hambre: Int,
                      item: Option[Item] = None
                  )extends Participante() {

  // CONSTRUCTOR
  require(_peso > 0)
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


  def esMejorQue(vikingo: Vikingo)(posta: Posta):Boolean = posta.resultadoParticipante(this) >= posta.resultadoParticipante(vikingo)

  def poseeUnItemDelTipo[T] (): Boolean = {
    if (this.item.isDefined) {
      this.item.get match {
        case _: T => true
        case _ => false
      }
    }else{
      false
    }
  }

  def danio() : Double = barbarosidad + (if(this.item.isDefined) this.item.get.danio else 0)

  def mejorMontura(dragones : Array[Dragon], postaAParticipar : Posta) : Participante = {
    // Veo las combinaciones Jinete (las que se montan ok)
    var jinetes : Array[Participante] = dragones.filter((dragon : Dragon) => dragon.puedeSerMontadoPor(this)).map((dragon : Dragon) => this.montar(dragon))
    var participantesPosibles : Array[Participante] = jinetes.+:(this)
    postaAParticipar.mejorResultado(participantesPosibles)
  }
}
