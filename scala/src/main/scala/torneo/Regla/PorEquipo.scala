package torneo.Regla

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import _root_.Participante.Equipo.Equipo
import dragon.Dragon
import exceptions.EstoNoEsUnequipoException
import posta.Posta

class PorEquipo extends Estandar {


  override def previoPosta(participantes: Array[Participante], dragones: Array[Dragon], posta: Posta): Array[Participante] = {

    var participantesGenerados: Array[Participante] = participantes.map((equipo: Participante)=> equipo match {

      case equipo: Equipo => equipo.participantes
      case _ => throw new EstoNoEsUnequipoException()
    }).flatten.toArray
    super.previoPosta(participantesGenerados, dragones, posta)
  }

  override def postPosta(participantes: Array[Participante]): Array[Participante] = super.postPosta(participantes)


}
