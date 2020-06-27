package Participante.jinete

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import dragon.Dragon

case class Jinete(
   dragon: Dragon,
   vikingo: Vikingo
 ) extends Participante(){

 def getVelocidad = dragon.getVelocidad - vikingo.peso
 def getPeso = dragon.peso * 0.2 - vikingo.peso


}
