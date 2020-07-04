package torneo.Regla

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import _root_.Participante.Equipo.Equipo
import dragon.Dragon
import exceptions.EstoNoEsUnequipoException
import posta.Posta

class PorEquipo (var equiposParticipantes : Array[Equipo]) extends Estandar {
/*

  override def previoPosta(participantes: Array[Participante], dragones: Array[Dragon], posta: Posta): Array[Participante] = {

    var participantesGenerados: Array[Participante] = participantes.flatMap((equipo: Participante) => equipo match {
      case equipo: Equipo => equipo.participantes
      case _ => throw new EstoNoEsUnequipoException()
    }).toArray
    super.previoPosta(participantesGenerados, dragones, posta)
  }

  override def postPosta(participantes: Array[Participante]): Array[Participante] = {
    val intermedio = super.postPosta(participantes)
    intermedio.map( (unEquipo : Equipo) => {
      new Equipo(
        unEquipo.participantes.filter(participantes.contains(_))
      )
    })
    equiposParticipantes =
  }

*/
}
