require_relative './trait.rb'
require_relative './class.rb'

  Trait.define do
    name :Operaciones
    method :saluda do
      print "Hola"
    end
    method :duplica do |un_numero|
      un_numero * 2
    end
  end




class Prueba uses Operaciones
  def materia
    :tadp
  end
end
