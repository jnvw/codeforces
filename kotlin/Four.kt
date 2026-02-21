class DSU(n: Int) {
    private val parent = IntArray(n + 1) { it }
    private val size = IntArray(n + 1) { 1 }

    fun find(x: Int): Int {
        if (parent[x] != x)
            parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(a: Int, b: Int) {
        var ra = find(a)
        var rb = find(b)
        if (ra == rb) return

        if (size[ra] < size[rb]) {
            val temp = ra
            ra = rb
            rb = temp
        }
        parent[rb] = ra
        size[ra] += size[rb]
    }
}

fun main() {
    val t = readLine()!!.toInt()
    val output = StringBuilder()

    repeat(t) {
        val (n, m1, m2) = readLine()!!.split(" ").map { it.toInt() }

        val edgesF = ArrayList<Pair<Int, Int>>()
        repeat(m1) {
            val (u, v) = readLine()!!.split(" ").map { it.toInt() }
            edgesF.add(u to v)
        }

        val dsuG = DSU(n)
        repeat(m2) {
            val (u, v) = readLine()!!.split(" ").map { it.toInt() }
            dsuG.union(u, v)
        }

        val dsuF = DSU(n)
        var removeCount = 0

        for ((u, v) in edgesF) {
            if (dsuG.find(u) != dsuG.find(v)) {
                removeCount++
            } else {
                dsuF.union(u, v)
            }
        }

        val map = HashMap<Int, MutableSet<Int>>()

        for (i in 1..n) {
            val rootG = dsuG.find(i)
            val rootF = dsuF.find(i)
            map.computeIfAbsent(rootG) { mutableSetOf() }.add(rootF)
        }

        var addCount = 0
        for (set in map.values) {
            addCount += set.size - 1
        }

        output.append(removeCount + addCount).append("\n")
    }

    print(output)
}