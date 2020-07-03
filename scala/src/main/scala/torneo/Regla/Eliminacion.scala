package torneo.Regla
import Participante.Participante

class Eliminacion(val cantidadQueNoPasa : Integer) extends Estandar {
  override def postPosta(participantes: Array[Participante]): Array[Participante] = participantes.take(participantes.length - cantidadQueNoPasa)
}
