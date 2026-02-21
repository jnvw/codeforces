import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val output = StringBuilder()

    repeat(t) {
        val first = StringTokenizer(br.readLine())
        val n = first.nextToken().toInt()
        val m = first.nextToken().toInt()
        val k = first.nextToken().toInt()

        val a = br.readLine().split(" ").map { it.toInt() }
        val known = br.readLine().split(" ").map { it.toInt() }.toSet()

        val unknownCount = n - k

        if (unknownCount > 1) {
            repeat(m) { output.append("0") }
        } 
        else if (unknownCount == 0) {
            repeat(m) { output.append("1") }
        } 
        else {
            val missing = (1..n).first { it !in known }
            for (ai in a) {
                if (ai == missing) output.append("1")
                else output.append("0")
            }
        }

        output.append("\n")
    }

    print(output)
}