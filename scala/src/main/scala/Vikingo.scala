case class Vikingo (
  var peso : Int,
  var velocidad : Int,
  var barbarosidad : Int,
  var hambre : Int
  //var item : ItemAgregado (TODO: Implement ItemAgregado class & Arma as subclass
  ){

  // CONSTRUCTOR
  require(peso > 0)
  require(velocidad > 0)
  require(barbarosidad > 0)
  require(hambre >= 0)

  //METHODS
  def aumentarHambre(porcentaje: Int): Vikingo = {
    var nuevoHambre = hambre
    if (hambre + porcentaje < 100) nuevoHambre = (hambre + porcentaje) else nuevoHambre = 100
    this.copy(hambre = nuevoHambre)
  }
}
