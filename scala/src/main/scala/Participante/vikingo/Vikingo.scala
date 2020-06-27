package Participante.vikingo

import Participante.Participante
import _root_.Participante.jinete.Jinete
import dragon.Dragon
import exceptions.NoPuedeSerMontadoException
import items.Item
import posta.Posta

case class Vikingo  (

                      peso: Int,
                      velocidad: Int,
                      barbarosidad: Double,
                      hambre: Int,
                      item: Option[Item] = None
                  )extends Participante() {

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
    if (unDragon.puedeSerMontadoPor(this)) Jinete(unDragon, this) else throw NoPuedeSerMontadoException()
  }




  def poseeUnItemDelTipo[T] (): Boolean = {
    this.item.isInstanceOf[Some[T]]
  }

  def danio() : Double = barbarosidad + (if(this.item.isDefined) this.item.get.danio else 0)

  def mejorMontura(dragones : Array[Dragon], postaAParticipar : Posta) : Participante = {
    // Veo las combinaciones Jinete (las que se montan ok)
    val jinetes : Array[Participante] = dragones.filter((dragon : Dragon) => dragon.puedeSerMontadoPor(this)).map((dragon : Dragon) => this.montar(dragon))
    val participantesPosibles : Array[Participante] = jinetes.+:(this)
    postaAParticipar.mejorResultado(participantesPosibles) match{
      case Some(combinacion) => combinacion
      case None => this
    }
  }
}
