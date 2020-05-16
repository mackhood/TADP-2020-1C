require 'byebug'

class Trait 
   attr_accessor :methodos

   def name simbolo
      Object.const_set(simbolo, self)
   end
   def initialize
      self.methodos = {}
   end
   def method unSimbolo, &unBloque
      methodos[unSimbolo] = [unBloque]
   end
   def self.define &unBloque
      miTrait = Trait.new 
      miTrait.instance_eval(&unBloque)
      
   end

   def + algo
      #byebug
      newTrait = self.clonar
      algo.methodos.each do | clave,valor |
         if(newTrait.methodos.key? clave) 
            newTrait.methodos[clave] = [proc {raise "Aca la cagamos."}]
         else 
            newTrait.methodos[clave] = valor
         end
      end
      newTrait
   end

   def clonar 
      unTrait = Trait.new
      self.methodos.each do | clave,valor |
         unTrait.methodos[clave] = valor
      end
      unTrait
   end

   
end

class Class 
   def uses algo
      algo.methodos.each do | clave,valor |
         self.define_method(clave,valor.first) 
      end
   end
end

