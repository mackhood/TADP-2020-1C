package Participante

import posta.Posta

abstract class Participante() {
  def esMejorQue(elOtroParticipante: Participante)(posta: Posta): Boolean = posta.resultadoParticipante(this) >= posta.resultadoParticipante(elOtroParticipante)
}
