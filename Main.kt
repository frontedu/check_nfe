package dev.eddu.check_nfe
import java.time.LocalDate
import dev.eddu.check_nfe.Emissor

//Regra de negócio - https://enotas.com.br/blog/chave-de-acesso/

val emissor = Emissor(
    cnpj = "36561434000299",
    estado = 35,
    fundacao = 2021
)

fun main() {

    println("Chave de acesso:")
    val input = readLine()
    val chave = input?.replace(" ", "")?.trim()
    if (chave != null && chave.length == 44) {
        if (isEmissor(chave)) {
            println("A chave de acesso é válida.")
        } else {
            println("A chave de acesso não foi emitida por este CNPJ.")
        }
    } else {
        println("A chave de acesso é inválida.")
    }
}

fun isEmissor(chave: String): Boolean {
    val chaveEstadoCode = chave.substring(0, 2).toInt()
    val chaveAnoCode = chave.substring(2, 4).toInt()
    val chaveCnpj = chave.substring(6, 20)

    return when {
        chaveEstadoCode != emissor.estado -> false
        chaveAnoCode !in emissor.fundacao % 1000..LocalDate.now().year % 1000 -> false
        chaveCnpj != emissor.cnpj -> false
        else -> true
    }
}
