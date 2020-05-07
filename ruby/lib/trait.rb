require "byebug"

class Trait
  attr_accessor :methods_trait
  attr_accessor :methods_trait_alias
  attr_accessor :strategy
  attr_accessor :trait_name

  def initialize
    self.methods_trait = {}
    self.methods_trait_alias = {}
    self.strategy = Strategy::DefaultStrategy.new
    self.trait_name = ""
  end

  def self.define(&a_block)
    new_trait = Trait.new()

    new_trait.instance_eval(&a_block)
    new_trait
  end

  def method(symbol, &block)
    methods_trait[symbol] = [block]
  end

  def name(symbol)
    objeto = self
    Object.const_set(symbol, objeto)

    self.trait_name = symbol.to_s
  end

  def copy_of_trait(object)
    a_trait = Trait.new

    a_trait.trait_name = object.trait_name
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
        my_copy.methods_trait[key] = [my_copy.methods_trait[key][0], array_of_block[0]]
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

  # def do_i_have_conflict_methods? #not working_investigate
  #   new_array = self.methods_trait.merge(self.methods_trait.merge) { |k, v| !is_a_conlflict_method? k }.values

  #   @value = new_array.inject() { |result, element| result && element }
  #   @value = !@value
  # end

  def is_a_conlflict_method?(method)
    @other = self.methods_trait[method].size != 1
  end

  def ^(new_strategy)
    my_copy = copy_of_trait(self)
    my_copy.strategy = new_strategy

    my_copy
  end
end

class TraitMethodRepeatException < StandardError
  def initialize(msg = "Both traits has the same methodName", exception_type = "custom")
    @exception_type = exception_type
    super(msg)
  end
end

class Class
  def uses(object)
    #object.do_i_have_conflict_methods?
    object.methods_trait.each do |key, array_of_block|
      unless object.is_a_conlflict_method?(key)
        self.define_method(key.to_s, array_of_block[0])

        self.alias_method(object.methods_trait_alias[key], key) if object.methods_trait_alias.include?(key)
      else
        object.strategy.execute(object, self, key)
      end
    end
  end
end

class Symbol
  def >>(symbol)
    array = [self, symbol] if symbol.is_a? Symbol
    array
  end
end

module Strategy
  def self.createStrategy(name, a_proc)
    self.const_set(name.to_sym, Class.new do
      self.define_method(:execute) do |a_trait, a_class, key|
        a_proc.call(a_trait, a_class, key)
      end
    end)
  end

  class DefaultStrategy
    def execute(a_trait, a_class, key)
      a_class.instance_eval do
        define_method(key.to_s, Proc.new { raise TraitMethodRepeatException.new })
      end
      #raise "Both traits has the same methodName"
    end
  end

  class In_Order
    def execute(a_trait, a_class, key)
      a_class.class_eval do
        last_result = ""
        self.define_method(key.to_s) do |*args|
          a_trait.methods_trait[key].each_with_index do |block, i|
            puts(self.instance_exec args[i], &block)
            last_result = self.instance_exec args[i], &block
          end

          return last_result
        end
      end
    end
  end

  class Function_fold
    def execute(a_trait, a_class, key)
      a_class.class_eval do
        self.define_method(key.to_s) do |*args|
          @function = args.delete_at(0)
          new_array = a_trait.methods_trait[key].each_with_index.map do |block, i|
            self.instance_exec args[i], &block
          end
          new_array.inject() { |result, element|
            self.instance_exec result, element, &@function
          }
        end
      end
    end
  end

  class Conditional_return
    attr_accessor :the_condition

    def initialize(a_condition)
      self.the_condition = a_condition
    end

    def execute(a_trait, a_class, key)
      condition = self.the_condition
      a_class.class_exec condition do
        self.define_method(key.to_s) do |*args|
          a_trait.methods_trait[key].each_with_index do |block, i|
            value = self.instance_exec args[i], &block
            return value if condition.call(value)
          end
        end
      end
    end
  end
end

# the_proc = proc { |the_trait, the_class, the_key|
#   the_class.instance_eval do
#     define_method(the_key.to_s, Proc.new { raise "The New Strategy" })
#   end
# }
