package torneo.Regla
import Participante.Participante

class Inverso extends Estandar {
  override def postPosta(participantes: Array[Participante]): Array[Participante] = super.postPosta(participantes.reverse)
}
