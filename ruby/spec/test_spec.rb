describe Trait do
  let(:miTrait) { Trait.define do
    name :MiTrait
    method :metodo1 do
      "Hola"
    end
    method :metodo2 do |un_numero|
      un_numero * 0 + 42
    end
  end
   }
  let(:miOtroTrait) {Trait.define do
    name :MiOtroTrait
    method :metodo1 do
    "kawuabonga" 
    end
    method :metodo3 do
    "zaraza" 
  end 
  end
   }   

  
   describe '#materia' do
    before do
      it 'debería pasar este test' do
       expect(prueba.materia).to be :tadp
      end
    end
   end
  describe '#materia' do
    it 'debería pasar este test' do
      expect(prueba.materia).to be :tadp
    end
  end


end