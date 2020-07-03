package exceptions

final case class EstoNoEsUnequipoException(private val message: String = "Esta combinacion no es permitida",
                                            private val cause: Throwable = None.orNull) extends Exception(message, cause)