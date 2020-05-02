class Trait
  attr_accessor :methods_trait
  attr_accessor :methods_trait_alias
  attr_accessor :strategy
  attr_accessor :name

  def initialize
    self.methods_trait = {}
    self.methods_trait_alias = {}
    self.strategy = Strategy::DefaultStrategy
    self.name = ""
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
    byebug
    objeto.name = symbol.to_s
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

class Class
  def uses(object)
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
  def self.createStrategy(name, &block)
    self.class.const_set :name, Class.new {
      def self.execute(a_trait, a_class, key)
        call.block
      end
    }
  end

  class DefaultStrategy #Throws  exception
    def self.execute(a_trait, a_class, key)
      a_class.instance_eval do
        define_method(key.to_s, a_trait.methods_trait[key][0])
      end
    end
  end

  class In_Order
    def self.execute(a_trait, a_class, key)
      a_trait.methods_trait[key].delete_at(0)
      @@procs = a_trait.methods_trait[key]

      a_class.class_eval do
        self.define_method(key.to_s) do |*args|
          @@procs.each_with_index do |block, i|
            self.instance_exec args[i], &block
          end
        end
      end
    end
  end

  class Function_fold
    def self.execute(a_trait, a_class, key)
      a_trait.methods_trait[key].delete_at(0)
      @@procs = a_trait.methods_trait[key]
      a_class.class_eval do
        self.define_method(key.to_s) do |*args|
          @function = args.delete_at(0)

          new_array = @@procs.each_with_index.map do |block, i|
            self.instance_exec args[i], &block
          end

          #new_array.inject() { |result, element| result.send(@function, element) }
          new_array.inject() { |result, element|
            result = self.instance_exec result, element, &@function
          }
        end
      end
    end
  end

  class Conditional_return
    def self.execute(a_trait, a_class, key)
      a_trait.methods_trait[key].delete_at(0)
      @@procs = a_trait.methods_trait[key]
      a_class.class_eval do
        self.define_method(key.to_s) do |*args|
          @function = args.delete_at(0)
          @@procs.each_with_index do |block, i|
            value = self.instance_exec args[i], &block

            return value if @function.call(value)
          end
        end
      end
    end
  end
end
