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
    otroTrait.metodos.keys.each do |simbolo|
      #chequeo que no exista un metodo con ese simbolo, para crearlo
      if @metodos.has_key?(simbolo)
        @metodos[simbolo] = proc{ raise "Error. Este metodo esta definido en mas de un trait." }
      else
        @metodos[simbolo] = otroTrait.metodos[simbolo]
      end
    end
    self
  end
end