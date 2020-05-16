require_relative "sacrebleu.rb"

Trait.define do
    name :MiTrait
    method :metodo1 do
      "Hola"
    end
    method :metodo2 do |un_numero|
      un_numero * 0 + 42
    end
end
  

class MiClase uses MiTrait
    def metodo1
      "mundo"
    end
 end

 Trait.define do
    name :MiOtroTrait
    method :metodo1 do
    "kawuabonga" 
    end
    method :metodo3 do
    "zaraza" 
  end 
  end
  
  class Conflicto
    uses MiTrait + MiOtroTrait 
  end
  
  