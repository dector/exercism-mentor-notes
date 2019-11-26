package assistant

import org.jsoup.Jsoup
import java.io.File

fun requestSolution(solutionId: String, body: (Solution) -> Unit) {
//    val url = readLine()!!.trim()
    val url = "https://exercism.io/mentor/solutions/$solutionId"

    val webPage = Jsoup.connect(url)
        .header("Cookie", File("cookies").readText().trim())
//        .also { println(it.request().headers()) }
        .get()

    val exerciseName = webPage
        .select(".inner > h2:nth-child(2) > strong:nth-child(1)")
        .first()
        .html()

    val author = webPage
        .select(".byline")
        .first()
        .html()
        .removePrefix("by ")

    val files = webPage.select(".solution")
        .map { it.getElementsByTag("code") }
        .filter { it.hasClass("language-kotlin") }
        .map { it.html() }

    println("Loaded $exerciseName by $author (${files.size} files found)")

    val foundFile = when (files.size) {
        0 -> error("No solution file found.")
        1 -> files.first()
        else -> error("More than one (${files.size}) solution files found.")
    }

    val solution = Solution(
        exercise = exerciseName,
        author = author,
        text = foundFile
    )

    logSolutionCode(solution)

    body(solution)
}

data class Solution(
    val exercise: String,
    val author: String,
    val text: String
)
