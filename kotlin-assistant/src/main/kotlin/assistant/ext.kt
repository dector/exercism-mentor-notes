package assistant

import java.util.*

fun String.encodeBase64() = Base64.getEncoder().encode(toByteArray()).toString()

fun logSolutionCode(solution: Solution) {
    println()
    println("```")
    println(solution.text)
    println("```")
}
