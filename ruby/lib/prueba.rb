require_relative './trait.rb'
require_relative './class.rb'

Operations = Trait.new
Operations.addMethod( 'addition',
    def addition(a, b)
      return a + b
    end
)


class Prueba uses Operations
  def materia
    :tadp
  end
end