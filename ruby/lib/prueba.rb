require_relative './trait.rb'
require_relative './class.rb'

  Trait.define do
    name :Operaciones
    method :saluda do
      "Hola en oper"
    end
    method :duplica do |un_numero|
      un_numero * 2
    end
  end

Trait.define do
  name :Saludo
  method :saluda do
    "Hola"
  end
  method :saludaNombre do |un_nombre|
    "Hola " + un_nombre
  end
end




class Prueba
  uses Operaciones + (Saludo - :saluda)
  def materia
    :tadp
  end
end
