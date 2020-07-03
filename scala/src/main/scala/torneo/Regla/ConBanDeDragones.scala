package torneo.Regla
import dragon.Dragon

class ConBanDeDragones (val condicionParaDragon : (Dragon) => Boolean) {
  def filtrarDragones(listaDeDragones : Array[Dragon]) : Array[Dragon] = {
    listaDeDragones.filter((unDragon : Dragon) => condicionParaDragon(unDragon))
  }
}
