require_relative "spec_helper"

describe "Trait" do
  describe "Test unitarios de Traits" do
    let!(:miTrait) {
      Trait.define do
        name :MiTrait
        method :metodo1 do
          "Hola"
        end
        method :metodo2 do |un_numero|
          un_numero * 0 + 42
        end
      end
    }

    let!(:miOtroTrait) {
      Trait.define do
        name :MiOtroTrait
        method :metodo1 do
          "kawuabonga"
        end
        method :metodo3 do
          "zaraza"
        end
      end
    }

    describe ".define" do
      describe "a_trait class" do
        it "Show class of object" do
          puts miTrait
          expect(miTrait.class).to eq Trait
        end
      end
      describe " #method" do
        it "Creates method in object" do
          expect(miTrait.methods_trait.include? :metodo1).to eq true
        end
      end
      describe " #name" do
        it "Gives name to object" do
          byebug
          expect(miTrait.name).to eq MiTrait
        end
      end
    end
  end
end

# describe '#materia' do
#   it 'debería pasar este test' do
#     expect(prueba.materia).to be :tadp
#   end
# end
