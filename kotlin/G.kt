fun main() {
    val t = readLine()!!.toInt()
    val out = StringBuilder()

    repeat(t) {
        val (n, sInput, xInput) = readLine()!!.split(" ").map { it.toLong() }
        val arr = readLine()!!.split(" ").map { it.toLong() }

        val s = sInput
        val x = xInput

        fun count(limit: Long): Long {
            var result = 0L
            var prefix = 0L
            val map = HashMap<Long, Long>()
            map[0L] = 1L

            for (value in arr) {
                if (value > limit) {
                    prefix = 0
                    map.clear()
                    map[0L] = 1L
                    continue
                }

                prefix += value
                result += map.getOrDefault(prefix - s, 0L)
                map[prefix] = map.getOrDefault(prefix, 0L) + 1
            }

            return result
        }

        val ans = count(x) - count(x - 1)
        out.append(ans).append("\n")
    }

    print(out)
}