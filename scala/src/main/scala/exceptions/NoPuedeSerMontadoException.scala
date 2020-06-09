package exceptions

final case class NoPuedeSerMontadoException(private val message: String = "Esta combinacion vikingo - dragon no es permitida",
private val cause: Throwable = None.orNull)extends Exception(message, cause)