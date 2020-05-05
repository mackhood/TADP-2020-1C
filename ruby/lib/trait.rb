class Trait
  attr_accessor :metodos
  def initialize
    @metodos = {}
    super
  end

  def method(nombre, &bloque) #este metodo debe guardar el metodo a definir en las clases que incluyan este trait
    @metodos[nombre] = bloque
  end

  def name(simbolo) #esto le deberia asignar el simbolo al trait
    Object.const_set(simbolo, self) # segun https://stackoverflow.com/a/4113502 esto deberia cambiar el simbolo...
  end

  def self.define (&contenido) #La singleton class funciona como factory
    elTrait = Trait.new
    elTrait.instance_eval(&contenido)
  end

  def +(otroTrait)
    nuevoTrait = clonar
    otroTrait.metodos.keys.each do |simbolo|
      #chequeo que no exista un metodo con ese simbolo, para crearlo
      if @metodos.has_key?(simbolo)
        nuevoTrait.metodos[simbolo] = proc{ raise "Error. Este metodo esta definido en mas de un trait." } #Guardar lista de bloques para estrategias
      else
        nuevoTrait.metodos[simbolo] = otroTrait.metodos[simbolo]
      end
    end
    nuevoTrait
  end

  def -(simbolo)
    nuevoTrait = clonar
    if @metodos.has_key?(simbolo)
      nuevoTrait.metodos.delete(simbolo)
      nuevoTrait
    else
      raise "Error en la resta de metodos. El simbolo " + simbolo + " no esta definido."
    end
  end

  private

  def clonar
    nuevoTrait = Trait.new
    nuevoTrait.metodos = @metodos.clone
    nuevoTrait
  end
end