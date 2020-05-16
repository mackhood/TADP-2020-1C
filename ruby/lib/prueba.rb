class Trait
  attr_accessor :TraitMethods
  attr_accessor :AliasMethods
  attr_accessor :strategy
  attr_accessor :Name

  def initialize
    self.TraitMethods = {}
    self.AliasMethods = {}
    self.Strategy = Strategy::DefaultStrategy
    self.Name = ''
  end

  def self.define(&a_block)
    newTrait = Trait.new()
    newTrait.instance_eval(&a_block)
    newTrait
  end

  def method(symbol, &block)
    TraitMethods[symbol] = [block]
  end

  def name(symbol)
    subObject = self
    Object.const_set(symbol, subObject)
    byebug
    subObject.Name = symbol.to_s
  end

  def repeatedMethodException
    Proc.new { raise "Both traits has the same methodName" }
  end

  def copy_of_trait(object)
    a_trait = Trait.new
    byebug
    a_trait.name = object.name
    object.methods_trait.each do |key, value|
      a_trait.methods_trait[key] = value
    end
    a_trait
  end

  def +(anotherTrait)
    my_copy = copy_of_trait(self)
    anotherTrait.methods_trait.each do |key, array_of_block|
      unless my_copy.methods_trait.key? key
        my_copy.methods_trait[key] = array_of_block
      else
        my_copy.methods_trait[key] = [my_copy.repeatedMethodException, my_copy.methods_trait[key][0], array_of_block[0]]
      end
    end
    my_copy
  end

  def -(symbol)
    object = copy_of_trait(self)
    object.methods_trait.delete(symbol)

    object
  end

  def <<(array_of_symbols)
    object = copy_of_trait(self)
    first_symbol = array_of_symbols[0]

    second_symbol = array_of_symbols[1]

    object.methods_trait_alias[first_symbol] = second_symbol if array_of_symbols.size == 2
    object
  end

  def do_i_have_conflict_methods?
    new_array = self.methods_trait.merge(self.methods_trait.merge) { |k, v| !is_a_conlflict_method? k }.values
    @value = new_array.inject(false) { |result, element| result && element }
    @value = !@value
  end

  def is_a_conlflict_method?(method)
    @other = self.methods_trait[method].size != 1
  end

  def ^(new_strategy)
    my_copy = copy_of_trait(self)
    my_copy.strategy = new_strategy

    my_copy
  end
end