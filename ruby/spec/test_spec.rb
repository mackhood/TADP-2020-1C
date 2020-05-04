require_relative "spec_helper"

describe "Trait" do
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
        expect(miTrait.trait_name).to eq "MiTrait"
      end
    end

    it "Agrego Trait a clase MiClase" do
      class MiClase uses MiTrait
        def metodo1
        "mundo"
      end       end

      mi_clase = MiClase.new
      expect(mi_clase.metodo1).to eq("mundo")
      expect(mi_clase.metodo2((33))).to eq(42)
    end

    it "Sumo Traits" do
      class Conflicto
        uses MiTrait + MiOtroTrait
      end

      conflicto_suma = Conflicto.new
      expect(conflicto_suma.metodo2(84)).to eq(42)
      expect(conflicto_suma.metodo3).to eq("zaraza")
      self.expect { conflicto_suma.metodo1 }.to raise_error(TraitMethodRepeatException)
    end
    it "Elimino metodo de trait para que no haya conflicto" do
      class TodoBienTodoLegal
        uses MiTrait + (MiOtroTrait - :metodo1)
      end

      todo_bien = TodoBienTodoLegal.new
      expect(todo_bien.metodo3).to eq("zaraza")
      expect(todo_bien.metodo2(84)).to eq(42)
      expect(todo_bien.metodo1).to eq("Hola")
    end

    it "Renombro metodo conflictivo" do
      class ConAlias
        uses MiTrait << (:metodo1 >> :saludo)
      end

      object_alias = ConAlias.new
      expect(object_alias.saludo).to eq("Hola")
      expect(object_alias.metodo1).to eq("Hola")
      expect(object_alias.metodo2(84)).to eq(42)
    end

    it "Estrategia Default" do
      class ConflictoDefaultStrategy
        uses MiTrait + MiOtroTrait
      end

      object_strategy_default = ConflictoDefaultStrategy.new
      self.expect { object_strategy_default.metodo1 }.to raise_error(TraitMethodRepeatException)
    end
    it "Estrategia In_Order" do
      class ConflictoIn_OrderStrategy
        uses (MiTrait + MiOtroTrait) ^ Strategy::In_Order.new
      end

      object_strategy_in_order = ConflictoIn_OrderStrategy.new
      byebug
      expect(object_strategy_in_order.metodo1).to eq("kawuabonga")
    end
    it "Estrategia Function_fold" do
      class ConflictoFunction_foldStrategy
        uses (MiTrait + MiOtroTrait) ^ Strategy::Function_fold.new
      end

      object_strategy_function_fold = ConflictoFunction_foldStrategy.new
      a_proc = proc { |object, another_object| object + another_object }
      expect(object_strategy_function_fold.metodo1(a_proc)).to eq("Holakawuabonga")
    end
    it "Estrategia Conditional_return" do
      class ConflictoConditional_returnStrategy
        uses (MiTrait + MiOtroTrait) ^ (Strategy::Conditional_return.new(proc { |a| a.length > 5 }))
      end

      object_strategy_conditional_return = ConflictoConditional_returnStrategy.new
      expect(object_strategy_conditional_return.metodo1).to eq("kawuabonga")
    end

    it "Estrategia Creada por el usuario" do
      the_proc = proc { |the_trait, the_class, the_key|
        the_class.instance_eval do
          define_method(the_key.to_s, Proc.new { raise TraitMethodRepeatException.new })
        end
      }

      Strategy.createStrategy("UserStrategy", the_proc)

      class ConflictoUserStrategy
        uses (MiTrait + MiOtroTrait) ^ Strategy::UserStrategy.new
      end

      object_strategy_user_strategy = ConflictoUserStrategy.new
      self.expect { object_strategy_user_strategy.metodo1 }.to raise_error(TraitMethodRepeatException)
    end
  end
end

# describe '#materia' do
#   it 'debería pasar este test' do
#     expect(prueba.materia).to be :tadp
#   end
# end

#bundle exec rspec test_spec.rb --format doc
