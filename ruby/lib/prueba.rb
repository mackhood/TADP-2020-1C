require "byebug"
require "rubygems"
require "ruby2ruby"
require "ruby_parser"
require "pp"

class Trait
  attr_accessor :methods_trait

  def initialize
    self.methods_trait = {}
  end

  def self.define(&a_block)
    byebug
    new_trait = Trait.new()
    byebug
    new_trait.instance_eval(&a_block)
  end

  def method(symbol, &block)
    byebug
    methods_trait[symbol] = block
  end

  def name(symbol)
    byebug
    objeto = self
    Object.const_set(symbol, objeto)
  end

  def repeatedMethodException
    raise "Both traits has the same methodName"
  end

  def copy_of_trait(object)
    a_trait = Trait.new
    object.methods_trait.each do |key, value|
      a_trait.methods_trait[key] = value
    end
    a_trait
  end

  def +(anotherTrait)
    byebug
    my_copy = copy_of_trait(self)
    byebug
    anotherTrait.methods_trait.each do |key, block|
      byebug
      unless my_copy.methods_trait.key? key
        my_copy.methods_trait[key] = block
      else
        my_copy.methods_trait[key] = Proc.new { raise "Both traits has the same methodName" }
      end
    end
    my_copy
  end

  def -(symbol)
    object = copy_of_trait(self)
    object.methods_trait.delete(symbol)
    byebug
    object
  end

  def <<(array_of_symbols)
    first_symbol = array_of_symbols[0]
    second_symbol = array_of_symbols[1]
    alias_method first_symbol second_symbol if array_of_symbols.size == 2 && self.methods.include?(first_symbol)
  end
end

class Class
  def uses(object)
    byebug
    object.methods_trait.each do |key, block|
      byebug
      self.define_method(key.to_s, block)
    end
  end
end

class Symbol
  def >>(symbol)
    array = [self, symbol] if symbol.is_a? Symbol
    array
  end
end
