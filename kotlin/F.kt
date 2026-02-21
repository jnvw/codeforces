fun main() {
    val t = readLine()!!.toInt()
    val out = StringBuilder()

    repeat(t) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toLong() }.sorted()
        val b = readLine()!!.split(" ").map { it.toLong() }.sorted()

        val candidates = (a + b).toSet()
        var maxProfit = 0L

        for (p in candidates) {

            val buyers = n - lowerBound(b, p)
            val positive = n - lowerBound(a, p)
            val negative = buyers - positive

            if (negative <= k) {
                maxProfit = maxOf(maxProfit, p * buyers)
            }
        }

        out.append(maxProfit).append("\n")
    }

    print(out)
}

fun lowerBound(arr: List<Long>, target: Long): Int {
    var l = 0
    var r = arr.size
    while (l < r) {
        val mid = (l + r) / 2
        if (arr[mid] < target) l = mid + 1
        else r = mid
    }
    return l
}