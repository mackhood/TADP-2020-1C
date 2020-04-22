require "byebug"
require "rubygems"
require "ruby2ruby"
require "ruby_parser"
require "pp"

class Trait
  attr_accessor :methods_trait
  attr_accessor :methods_trait_alias

  def initialize
    self.methods_trait = {}
    self.methods_trait_alias = {}
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
    Proc.new { raise "Both traits has the same methodName" }
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
        my_copy.methods_trait[key] = self.repeatedMethodException
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
    object = copy_of_trait(self)
    first_symbol = array_of_symbols[0]
    byebug
    second_symbol = array_of_symbols[1]
    byebug
    object.methods_trait_alias[first_symbol] = second_symbol if array_of_symbols.size == 2
    object
  end
end

class Class
  def uses(object)
    byebug
    object.methods_trait.each do |key, block|
      self.define_method(key.to_s, block)
      byebug
      self.alias_method(object.methods_trait_alias[key] ,key) if object.methods_trait_alias.include?(key) 
    end
  end
end

class Symbol
  def >>(symbol)
    byebug
    array = [self, symbol] if symbol.is_a? Symbol
    array
  end
end
