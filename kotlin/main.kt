import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()

    repeat(t) {
        val st = StringTokenizer(br.readLine())
        val a1 = st.nextToken().toInt()
        val a2 = st.nextToken().toInt()
        val a4 = st.nextToken().toInt()
        val a5 = st.nextToken().toInt()

        val candidates = listOf(
            a1 + a2,
            a4 - a2,
            a5 - a4
        )

        var ans = 0

        for (a3 in candidates) {
            var count = 0
            if (a3 == a1 + a2) count++
            if (a4 == a2 + a3) count++
            if (a5 == a3 + a4) count++
            ans = max(ans, count)
        }

        println(ans)
    }
}