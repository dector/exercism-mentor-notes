package exercises.hamming

import assistant.Solution
import assistant.requestSolution

fun main() {
    requestSolution("232564e0038a4623b6d0a32328237838") { solution ->
        println("Few suggestions:")
        with(solution) {
            checkRequireIsUsed()
            checkThrowsNotDeclared()
            checkZipping()
        }
    }
}

private fun Solution.checkRequireIsUsed() {
    if (text.contains("throw IllegalArgumentException")) {
        println("- You can use [require()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html#require) function to verify arguments contract.")
    }
}

private fun Solution.checkThrowsNotDeclared() {
    if (text.contains("@Throws")) {
        println("- There is no need to use `@Throws(Exception::class)` as Kotlin [doesn't have checked expections](https://kotlinlang.org/docs/reference/exceptions.html#checked-exceptions). So this annotation is mostly used if you are building library and you expect that this function will be called from Java code.")
    }
}

private fun Solution.checkZipping() {
    if (text.contains("for (") || text.contains("for(")) {
        println("- If you want to go bit functionally - check-out [Zipping](https://kotlinlang.org/docs/reference/collection-transformations.html#zipping) and [`count {}`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/count.html#count).")
    }
}
