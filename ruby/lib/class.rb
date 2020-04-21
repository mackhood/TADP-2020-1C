class Class
  def uses (aTrait)
    aTraitMethods = aTrait.class.instance_methods false
    aTraitMethods.each { |aMethod| !self.class.methods.include? aMethod ? self.class.bind(aMethod) : null}
  end
end