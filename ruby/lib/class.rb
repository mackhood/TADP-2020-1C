class Class
  def uses (unTrait)
    unTrait.metodos.keys.each do |simbolo|
      #chequeo que no exista un metodo con ese simbolo, para crearlo

      if !self.method_defined? simbolo
        self.send(:define_method, simbolo, unTrait.metodos[simbolo])
      end
    end
  end
end