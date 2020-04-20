require "byebug"

class Trait
  def self.define(&a_block)
    new_trait = Trait.new()

    new_trait.instance_eval(&a_block)
  end

  def method(symbol, &block)
    self.define_singleton_method(symbol) do
      block
    end
  end

  def name(symbol)
    objeto = self

    the_symbol = symbol

    Object.const_set(the_symbol, objeto)
  end
end
