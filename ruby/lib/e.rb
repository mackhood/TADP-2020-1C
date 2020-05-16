class Meep 
    def self.plsmeep sanclu
       puts(sanclu)
       byebug
       puts("salut")
       puts(sanclu)
       byebug
       puts("why")
       #  "meep" 
    
    end
    def hai array
       array.each do | a | 
          byebug
          puts(a)
       end
    end
    def transformadorDeBloque &unBloque
       a = unBloque
       puts(unBloque)
       puts(self)
       a.call
    end
end
