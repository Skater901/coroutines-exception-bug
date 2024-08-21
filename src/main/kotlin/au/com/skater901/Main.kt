package au.com.skater901

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    System.setProperty("kotlinx.coroutines.debug", "on")
    try {
        runBlocking {
            withContext(coroutineContext) {
                throw IllegalArgumentException("oh no!")
            }
        }
    } catch (t: Throwable) {
        t.print()
    }
}

private fun Throwable.print() {
    println("${javaClass.name}: $message")
    print("  ")
    println(stackTrace.joinToString("\n  "))
    cause?.let {
        if (it != this) {
            print("Caused by: ")
            it.print()
        }
    }
}