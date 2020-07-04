package Participante.Equipo

import Participante.vikingo.Vikingo
import Participante.Participante

case class Equipo(participantes: Array[Participante]) extends Participante(){
  override def aumentarHambre(nivelHambre: Int): Equipo = {
    this.copy(participantes = this.participantes.map(_.aumentarHambre(nivelHambre)))
  }
}
