import dragon.{Dragon, FuriaNocturna, Gronckle, NadderMortifero}
import org.scalatest.{FreeSpec, Matchers}
import Participante.vikingo.Vikingo
import exceptions.{NingunParticipanteEsAdmitidoEnEstaPostaException, PesoNoPuedeSerMayoraVelocidadBaseException}
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
        Vikingo(50, 200, 5, 70)
      }
      "si algun parametro es negativo, tira un error" in {
        assertThrows[IllegalArgumentException] {
          Vikingo(-1, 200, 5, 70)
        }
      }
    }

    "Al aumentar el hambre" - {
      "si la suma del hambre anterior y el que aumenta da menor que 100, vale eso" in {
        val hipo = Vikingo(50, 200, 5, 70)
        val hipo2 = hipo.aumentarHambre(15)
        hipo.hambre shouldBe 70
        hipo2.hambre shouldBe 85
      }

      "si la suma del hambre anterior y el que aumenta da mayor que 100, vale 100" in {
        val hipo = Vikingo(50, 200, 5, 70)
        val nuevoHipo: Vikingo = hipo.aumentarHambre(35)
        nuevoHipo.hambre shouldBe 100
      }
    }
    "vikingo es mejor en postas que otro" in {
      val hipo = Vikingo(50, 200, 5, 70)
      val astrid = Vikingo(peso = 70, 400, 10, 70)
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
        new Dragon(15)
      }
      "si el peso es negativo o cero, tira un error" in {
        assertThrows[IllegalArgumentException] {
          new Dragon(0)
        }
      }

      "si el peso es mayor a su velocidad base, tira una excepcion" in {
        assertThrows[PesoNoPuedeSerMayoraVelocidadBaseException] {
          new Dragon(61)
        }
      }
    }

    "Velocidad" - {
      "La velocidad es la diferencia entre el peso y la velocidad base" in {
        new Dragon(150, 300).getVelocidad shouldBe 300 - 150
      }

      "La velocidad base default es 60 km/h" in {
        new Dragon(25).getVelocidad shouldBe 60 - 25
      }
    }
  }

  "Razas de dragon" - {
    "Al crear un Furia Nocturna" - {
      "su velocidad es tres veces la velocidad habitual" in {
        FuriaNocturna(30).getVelocidad shouldBe (60 - 30) * 3
      }

      "su daño es el especificado en la creacion" in {
        FuriaNocturna(30, 60, 15).getDanio shouldBe 15
      }
    }

    "Al crear un Nadoer Mortifero" - {
      "su velocidad es la velocidad habitual" in {
        NadderMortifero(10).getVelocidad shouldBe (60 - 10)
      }

      "su daño es 150, siempre" in {
        NadderMortifero(30).getDanio shouldBe 150
      }
    }

    "Al crear un Gronckle" - {
      "su velocidad es la mitad de la base" in {
        Gronckle(10, 200).getVelocidad shouldBe (200 - 10) / 2
      }

      "su daño 5 veces su peso" in {
        Gronckle(40).getDanio shouldBe 200
      }
    }
  }

  "Jinetes" - {
    "Un vikingo monta un dragon" - {
      "Un dragon puede llevar un vikingo que pesa menos del 20% de su peso" in {
        val hipo = Vikingo(50, 200, 5, 70)
        val unGronckle = Gronckle(251, 300)

        hipo.montar(unGronckle)
      }

      "Un dragon NO puede llevar un vikingo que pesa mas del 20% de su peso" in {
        val hipo = Vikingo(50, 200, 5, 70)
        val unGronckle = Gronckle(249, 300)
        assertThrows[exceptions.NoPuedeSerMontadoException] {
          hipo.montar(unGronckle)
        }
      }

      "Chimuelo es un Furia nocturna que requiere que el vikingo tenga un sistema de vuelo como ítem" in {
        val requisito = (_: Dragon, vikingo: Vikingo) => vikingo.poseeUnItemDelTipo[SistemaDeVuelo]()
        val chimuelo = FuriaNocturna(255, 500, 15, Array(requisito))
        val hipo = Vikingo(50, 200, 5, 70, Some(SistemaDeVuelo()))
        hipo.montar(chimuelo)
      }

      "Chimuelo es un Furia nocturna que requiere que el vikingo tenga un sistema de vuelo como ítem (Si no lo tiene, falla) " in {
        val requisito = (_: Dragon, vikingo: Vikingo) => vikingo.poseeUnItemDelTipo[SistemaDeVuelo]()
        val chimuelo = FuriaNocturna(255, 500, 15, Array(requisito))
        val hipo = Vikingo(50, 200, 5, 70)
        assertThrows[exceptions.NoPuedeSerMontadoException] {
          hipo.montar(chimuelo)
        }
      }

      "Los Nadder mortíferos siempre tienen una segunda restricción básica y es que el daño que puede hacer un vikingo no supere el suyo" in {
        val unNadderMortifero = NadderMortifero(260, 300)
        val hipo = Vikingo(50, 200, 5, 70)
        hipo.montar(unNadderMortifero)
      }

      "Los Nadder mortíferos siempre tienen una segunda restricción básica y es que el daño que puede hacer un vikingo no supere el suyo (si lo supera rompe)" in {
        val unNadderMortifero = NadderMortifero(260, 300)
        val hipo = Vikingo(50, 200, 155, 70)
        assertThrows[exceptions.NoPuedeSerMontadoException] {
          hipo.montar(unNadderMortifero)
        }
      }

      "Los Gronckle necesitan un vikingo que no supere un peso determinado para montarlos." in {
        val unGronckle = Gronckle(260, 300, Array(), 60)
        val hipo = Vikingo(50, 200, 5, 70)
        hipo.montar(unGronckle)
      }

      "Los Gronckle necesitan un vikingo que no supere un peso determinado para montarlos. (si lo supera rompe)" in {
        val unGronckle = Gronckle(260, 300, Array(), 20)
        val hipo = Vikingo(50, 200, 155, 70)
        assertThrows[exceptions.NoPuedeSerMontadoException] {
          hipo.montar(unGronckle)
        }
      }
    }
  }

  "Postas" - {
    "Solo vikingos" - {
      "En pesca es mejor el competidor que más pescado logre cargar" - {
        "A misma barbarosidad, el que mas pesa gana" in {
          val unVikingo = Vikingo(50, 200, 5, 70)
          val otroVikingo = Vikingo(80, 200, 5, 70)
          assert(otroVikingo.esMejorQue(unVikingo)(Pesca()))
        }

        "A mismo peso, el mas barbaro gana" in {
          val unVikingo = Vikingo(50, 200, 5, 70)
          val otroVikingo = Vikingo(50, 200, 6, 70)
          assert(otroVikingo.esMejorQue(unVikingo)(Pesca()))
        }

        "El primero es primero y el segundo, segundo" in {
          val unVikingo = Vikingo(50, 200, 5, 70)
          val otroVikingo = Vikingo(50, 200, 6, 70)
          Pesca().podioPosta(Array(unVikingo, otroVikingo)).head shouldBe otroVikingo
        }
      }
    }
  }

  "Vikingo: Mejor resultado" - {
    "Un vikingo sin dragones para ser jinete es el mejor resultado en una posta cualquiera" in {
      val hipo = Vikingo(50, 200, 5, 70)
      hipo.mejorMontura(Array(), Pesca()).equals(hipo)
    }

    "Un vikingo con un dragon gana en peso => es la mejor montura para pesca" in {
      val unGronckle = Gronckle(260, 300, Array(), 60)
      val otroGronckleMasPesado = Gronckle(280, 300, Array(), 60)
      val hipo = Vikingo(50, 200, 5, 70)
      hipo.mejorMontura(Array(unGronckle), Pesca()).equals(hipo.montar(otroGronckleMasPesado))
    }
  }

  "Un torneo" in {
    val hipo = Vikingo(50, 200, 5, 70)
    val astrid = Vikingo(peso = 70, 400, 10, 70)
    val torneo = Torneo(Array(hipo, astrid), Array(Pesca().agregarRequisitoPeso(55)))
    torneo.resultadoTorneo shouldBe Array(astrid)
  }

  "Un torneo que nadie gana (porque no pasan los requisitos)" in {
    val hipo = Vikingo(50, 200, 5, 70)
    val astrid = Vikingo(peso = 70, 400, 10, 70)
    assertThrows[NingunParticipanteEsAdmitidoEnEstaPostaException] {
      Torneo(Array(hipo, astrid), Array(Pesca(Array {
        case participante: Vikingo => participante.peso > 150000
        case _ => false
      })))
        .resultadoTorneo
    }
  }

}
