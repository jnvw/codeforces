fun main() {
    val t = readLine()!!.toInt()
    val out = StringBuilder()

    repeat(t) {
        val s = readLine()!!
        var sum = 0
        var c2 = 0
        var c3 = 0

        for (ch in s) {
            val d = ch - '0'
            sum += d
            if (d == 2) c2++
            if (d == 3) c3++
        }

        var ok = false
        for (x in 0..minOf(c2, 8)) {
            for (y in 0..minOf(c3, 8)) {
                if ((sum + 2*x + 6*y) % 9 == 0) {
                    ok = true
                    break
                }
            }
            if (ok) break
        }

        out.append(if (ok) "YES\n" else "NO\n")
    }

    print(out)
}