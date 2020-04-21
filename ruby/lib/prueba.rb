require "byebug"
require "rubygems"
require "ruby2ruby"
require "ruby_parser"
require "pp"

class Trait
  attr_accessor :methods_trait

  def initialize(hash)
    @methods_trait = hash
  end

  def self.define(&a_block)
    byebug
    new_trait = Trait.new({})
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
