class Trait
  def addMethod(symbol,method)
    self.class.send(:define_method, symbol) do method end
  end
  #Return this method in case that a name is in two added traits.
  def repeatedMethodException
    raise 'An error has ocurred. A method is repeated in different added traits.'
    end

  def +(otherTrait)
    otherTraitMethods = otherTrait.instance_methods false
    otherTraitMethods.each { |aMethod| !this.methods.include? aMethod ? this.bind(aMethod) : addMethod(repeatedMethodException, :aMethod)}
  end
end