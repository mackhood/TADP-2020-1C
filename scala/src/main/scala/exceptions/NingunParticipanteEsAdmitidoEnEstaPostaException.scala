package exceptions
import posta.Posta
final case class NingunParticipanteEsAdmitidoEnEstaPostaException(private val nombrePosta: String ,
                                            private val cause: Throwable = None.orNull) extends Exception("Ningun participante paso los requisitos para competir en la posta ".concat(nombrePosta), cause)
