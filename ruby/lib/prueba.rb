require "byebug"

class Trait
  def self.define(&a_block)
    byebug
    new_trait = Trait.new

    new_trait.instance_eval(&a_block)
  end

  def method(symbol, &block)
    byebug
    define_singleton_method(symbol) do
      block
    end
  end

  def name(symbol)
    objeto = self
    the_symbol = symbol
    Object.const_set(the_symbol, objeto)
  end
end

class Class
  def uses(object)
    byebug
    object.singleton_methods.each do |method|
      byebug
      a_proc = method.to_proc
      self.define_method(method.to_s, &a_proc) unless self.instance_methods(false).include? method
    end
  end
end
