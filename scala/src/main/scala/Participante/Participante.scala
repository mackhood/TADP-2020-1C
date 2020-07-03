package Participante

import posta.Posta

trait Participante {
  def esMejorQue(elOtroParticipante: Participante)(posta: Posta): Boolean = posta.resultadoParticipante(this) >= posta.resultadoParticipante(elOtroParticipante)
  def aumentarHambre(nivelHambre: Int)

}
