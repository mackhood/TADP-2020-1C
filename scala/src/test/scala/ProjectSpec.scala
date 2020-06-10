import dragon.{Dragon, FuriaNocturna, Gronckle, NadoerMortifero}
import Participante.jinete.Jinete
import org.scalatest.{FreeSpec, Matchers}
import Participante.vikingo.Vikingo
import exceptions.{NoPuedeSerMontadoException, PesoNoPuedeSerMayoraVelocidadBaseException}
class ProjectSpec extends FreeSpec with Matchers {

  "Este proyecto" - {

    "cuando está correctamente configurado" - {
      "debería resolver las dependencias y pasar este test" in {
        Prueba.materia shouldBe "tadp"
      }
    }
  }

  "vikingo.Vikingo" - {
    "Al crear uno nuevo" - {
      "si todos los parametros son positivos o hambre en 0 se crea sin problema" in {
        var hipo = new Vikingo(50, 200, 5, 70)
      }
      "si algun parametro es negativo, tira un error" in {
        assertThrows[IllegalArgumentException]{var hipo = new Vikingo(-1, 200, 5, 70)}
      }
    }

    "Al aumentar el hambre" - {
      "si la suma del hambre anterior y el que aumenta da menor que 100, vale eso" in {
        var hipo = new Vikingo(50, 200, 5, 70)
        var hipo2 = hipo.aumentarHambre(15)
        hipo.hambre shouldBe 70
        hipo2.hambre shouldBe 85
      }

      "si la suma del hambre anterior y el que aumenta da mayor que 100, vale 100" in {
        var hipo = new Vikingo(50, 200, 5, 70)
        var nuevoHipo :Vikingo = hipo.aumentarHambre(35)
        nuevoHipo.hambre shouldBe 100
      }
    }
  }

  "Dragon" - {
    "Al crear uno nuevo" - {
      "si el peso es positivo y no le mando velocidad base se crea sin problema" in {
        var unDragon = new Dragon(15)
      }
      "si el peso es negativo o cero, tira un error" in {
        assertThrows[IllegalArgumentException]{var unDragon = new Dragon(0)}
      }

      "si el peso es mayor a su velocidad base, tira una excepcion" in {
        assertThrows[PesoNoPuedeSerMayoraVelocidadBaseException]{var unDragon = new Dragon(61)}
      }
    }

    "Velocidad" - {
      "La velocidad es la diferencia entre el peso y la velocidad base" in {
        var unDragon = new Dragon(150, 300 )
        assert(unDragon.getVelocidad == 300 - 150)
      }

      "La velocidad base default es 60 km/h" in {
        var unDragon = new Dragon(25)
        assert(unDragon.getVelocidad == 60 - 25)
      }
    }
  }

  "Razas de dragon" - {
    "Al crear un Furia Nocturna" - {
      "su velocidad es tres veces la velocidad habitual" in {
        var unFuriaNocturna = new FuriaNocturna(30)
        assert(unFuriaNocturna.getVelocidad == (60 - 30) * 3)
      }

      "su daño es el especificado en la creacion" in {
        var unFuriaNocturna = new FuriaNocturna(30, 60, 15 )
        assert(unFuriaNocturna.getDanio == 15)
      }
    }

    "Al crear un Nadoer Mortifero" - {
      "su velocidad es la velocidad habitual" in {
        var unNadoerMortifero = new NadoerMortifero(10)
        assert(unNadoerMortifero.getVelocidad == (60 - 10))
      }

      "su daño es 150, siempre" in {
        var unNadoerMortifero = new NadoerMortifero(30)
        assert(unNadoerMortifero.getDanio == 150)
      }
    }

    "Al crear un Gronckle" - {
      "su velocidad es la mitad de la base" in {
        var unGronckle = new Gronckle(10, 200)
        assert(unGronckle.getVelocidad == (200 - 10) / 2)
      }

      "su daño 5 veces su peso" in {
        var unGronckle = new Gronckle(40)
        assert(unGronckle.getDanio == 200)
      }
    }
  }

  "Jinetes" - {
    "Un vikingo monta un dragon" - {
      "Un dragon puede llevar un vikingo que pesa menos del 20% de su peso" in {
        var hipo = new Vikingo(50, 200, 5, 70)
        var unGronckle = new Gronckle(251, 300)

        val jineteExitoso: Jinete = hipo.montar(unGronckle)
      }

      "Un dragon NO puede llevar un vikingo que pesa mas del 20% de su peso" in {
        var hipo = new Vikingo(50, 200, 5, 70)
        var unGronckle = new Gronckle(249, 300)
        assertThrows[exceptions.NoPuedeSerMontadoException]{
          val jineteNoExitoso: Jinete = hipo.montar(unGronckle)
        }
      }
    }
  }

}
