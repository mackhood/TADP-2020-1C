package torneo

import dragon.Dragon
import posta.Posta
import scala.util.Success
import Participante.Participante
import _root_.Participante.jinete.Jinete
import _root_.Participante.vikingo.Vikingo

case class Torneo(
                   participantes: Array[Participante],
                   postas: Array[Posta],
                   dragonesDisponibles: Array[Dragon] = Array()
                 ) {

  def resultadoTorneo: Array[Participante] = {postas.foldLeft(participantes)
    ((participantesRestantes: Array[Participante], unaPosta: Posta) => {
    if(participantesRestantes.length <= 1) {
      // TODO: Se ejecutan postas sin participantes
      participantesRestantes
    }else{
      unaPosta.podioPosta(participantesRestantes)
    }

  })
  }


  def prepararParticipantes(participantes: Array[Participante],dragonesDisponibles: Array[Dragon],posta: Posta):Array[Participantes] ={
    var participantesGenerados:  Array[Participantes] = Array()
    var listaDragonesAuxiliares: ArrayBuffer[Dragon] = dragonesDisponibles.clone().toBuffer

    //val participante2 = Vikingo(50, 200, 5, 70)


    participantes.foreach { participante =>
       val variable = participante.match {
         //
       }
    }






  }
}
