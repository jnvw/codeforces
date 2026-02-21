import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import java.util.HashMap

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()

    repeat(t) {
        val n = br.readLine().toInt()
        val st = StringTokenizer(br.readLine())

        val freq = HashMap<Int, Int>()

        repeat(n) {
            val num = st.nextToken().toInt()
            freq[num] = freq.getOrDefault(num, 0) + 1
        }

        var score = 0
        for (value in freq.values) {
            score += value / 2
        }

        println(score)
    }
}