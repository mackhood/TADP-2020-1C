package torneo.Regla
import Participante.Participante
import dragon.Dragon
import posta.Posta

class ConHandicap extends Estandar {
  override def previoPosta(participantes: Array[Participante], dragones: Array[Dragon], posta: Posta): Array[Participante] = super.previoPosta(participantes.reverse, dragones, posta)
}
