package Participante.jinete

import Participante.Participante
import _root_.Participante.vikingo.Vikingo
import dragon.Dragon

case class Jinete(
   var dragon: Dragon,
   var vikingo: Vikingo
 ) extends Participante(){

 def getVelocidad = dragon.getVelocidad - vikingo._peso
 def getPeso = dragon.peso * 0.2 - vikingo._peso


}
