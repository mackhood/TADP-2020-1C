import dragon.{Dragon, FuriaNocturna, Gronckle, NadderMortifero}
import Participante.jinete.Jinete
import org.scalatest.{FreeSpec, Matchers}
import Participante.vikingo.Vikingo
import Participante.Participante
import exceptions.{NoPuedeSerMontadoException, PesoNoPuedeSerMayoraVelocidadBaseException}
import items.SistemaDeVuelo
import posta.{Carrera, Combate, Pesca}
import torneo.Torneo
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
      "vikingo es mejor en postas que otro" in{
        var hipo = new Vikingo(50, 200, 5, 70)
        var astrid = new Vikingo(_peso = 70,400, 10, 70)
        val pesca = Pesca()
        val carrera = Carrera()
        val combate = Combate()
        astrid.esMejorQue(hipo)(pesca) shouldBe true
        astrid.esMejorQue(hipo)(carrera) shouldBe true
        astrid.esMejorQue(hipo)(combate) shouldBe true

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
        var unNadderMortifero = new NadderMortifero(10)
        assert(unNadderMortifero.getVelocidad == (60 - 10))
      }

      "su daño es 150, siempre" in {
        var unNadderMortifero = new NadderMortifero(30)
        assert(unNadderMortifero.getDanio == 150)
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

      "Chimuelo es un Furia nocturna que requiere que el vikingo tenga un sistema de vuelo como ítem" in {
        var requisito = (dragon: Dragon, vikingo: Vikingo) => vikingo.poseeUnItemDelTipo[SistemaDeVuelo]()
        var chimuelo = new FuriaNocturna(255, 500, 15, Array(requisito) )
        var hipo = new Vikingo(50, 200, 5, 70,Some(new SistemaDeVuelo))
        hipo.montar(chimuelo)
      }

      "Chimuelo es un Furia nocturna que requiere que el vikingo tenga un sistema de vuelo como ítem (Si no lo tiene, falla) " in {
        var requisito = (dragon: Dragon, vikingo: Vikingo) => vikingo.poseeUnItemDelTipo[SistemaDeVuelo]()
        var chimuelo = new FuriaNocturna(255, 500, 15, Array(requisito) )
        var hipo = new Vikingo(50, 200, 5, 70)
        assertThrows[exceptions.NoPuedeSerMontadoException]{
          hipo.montar(chimuelo)
        }
      }

      "Los Nadder mortíferos siempre tienen una segunda restricción básica y es que el daño que puede hacer un vikingo no supere el suyo" in {
        var unNadderMortifero = new NadderMortifero(260, 300)
        var hipo = new Vikingo(50, 200, 5, 70)
        hipo.montar(unNadderMortifero)
      }

      "Los Nadder mortíferos siempre tienen una segunda restricción básica y es que el daño que puede hacer un vikingo no supere el suyo (si lo supera rompe)" in {
        var unNadderMortifero = new NadderMortifero(260, 300)
        var hipo = new Vikingo(50, 200, 155, 70)
        assertThrows[exceptions.NoPuedeSerMontadoException] {
          hipo.montar(unNadderMortifero)
        }
      }

      "Los Gronckle necesitan un vikingo que no supere un peso determinado para montarlos." in {
        var unGronckle = new Gronckle(260, 300, Array(), 60)
        var hipo = new Vikingo(50, 200, 5, 70)
        hipo.montar(unGronckle)
      }

      "Los Gronckle necesitan un vikingo que no supere un peso determinado para montarlos. (si lo supera rompe)" in {
        var unGronckle = new Gronckle(260, 300, Array(), 20)
        var hipo = new Vikingo(50, 200, 155, 70)
        assertThrows[exceptions.NoPuedeSerMontadoException] {
          hipo.montar(unGronckle)
        }
      }
    }
  }

  "Postas" - {
    "Solo vikingos" - {
      "En pesca es mejor el competidor que más pescado logre cargar" - {
        "A misma barbarosidad, el que mas pesa gana" in  {
          var pesca = new Pesca()
          var unVikingo = new Vikingo(50, 200, 5, 70)
          var otroVikingo = new Vikingo(80, 200, 5, 70)
          assert(otroVikingo.esMejorQue(unVikingo)(pesca))
        }

        "A mismo peso, el mas barbaro gana" in  {
          var pesca = new Pesca()
          var unVikingo = new Vikingo(50, 200, 5, 70)
          var otroVikingo = new Vikingo(50, 200, 6, 70)
          assert(otroVikingo.esMejorQue(unVikingo)(pesca))
        }

        "El primero es primero y el segundo, segundo" in {
          var pesca = new Pesca()
          var unVikingo = new Vikingo(50, 200, 5, 70)
          var otroVikingo = new Vikingo(50, 200, 6, 70)
          pesca.podioPosta(Array(unVikingo, otroVikingo)).head shouldBe otroVikingo
        }
      }
    }
  }

  "Vikingo: Mejor resultado" - {
    "Un vikingo sin dragones para ser jinete es el mejor resultado en una posta cualquiera" - {
      var hipo = new Vikingo(50, 200, 5, 70)
      var pesca = new Pesca()
      hipo.mejorMontura(Array(), pesca).equals(hipo)
    }

    "Un vikingo sin dragones para ser jinete es el mejor resultado en una posta cualquiera" - {
      var hipo = new Vikingo(50, 200, 5, 70)
      var pesca = new Pesca()
      hipo.mejorMontura(Array(), pesca).equals(hipo)
    }
  }

  "Un torneo" in {
    var hipo = new Vikingo(50, 200, 5, 70)
    var astrid = new Vikingo(_peso = 70,400, 10, 70)
    val pesca = new Pesca()
    pesca.agregarRequisitoPeso(55)
    var elTorneo = new Torneo(Array(hipo, astrid), Array(pesca))
    elTorneo.resultadoTorneo shouldBe Array(astrid)
  }

  "Un torneo que nadie gana (porque no pasan los requisitos)" in {
    var hipo = new Vikingo(50, 200, 5, 70)
    var astrid = new Vikingo(_peso = 70,400, 10, 70)
    val pesca = new Pesca(Array {
      case participante: Vikingo => participante._peso > 150000
      case _ => false
    })
    var elTorneo = new Torneo(Array(hipo, astrid), Array(pesca))
    elTorneo.resultadoTorneo shouldBe Array()
  }

}
