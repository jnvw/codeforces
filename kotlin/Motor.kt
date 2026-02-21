import java.io.*
import java.util.*
import kotlin.math.min

data class Edge(val u: Int, val v: Int, val w: Int, val id: Int)

class DSU(n: Int) {
    private val parent = IntArray(n + 1) { it }
    private val size = IntArray(n + 1) { 1 }

    fun find(x: Int): Int {
        if (parent[x] != x)
            parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(a: Int, b: Int): Boolean {
        var ra = find(a)
        var rb = find(b)
        if (ra == rb) return false
        if (size[ra] < size[rb]) {
            val t = ra; ra = rb; rb = t
        }
        parent[rb] = ra
        size[ra] += size[rb]
        return true
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val out = StringBuilder()
    val t = br.readLine().toInt()

    repeat(t) {
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val edges = ArrayList<Edge>()

        repeat(m) { id ->
            val (u, v, w) = br.readLine().split(" ").map { it.toInt() }
            edges.add(Edge(u, v, w, id))
        }

        edges.sortBy { it.w }

        val dsu = DSU(n)
        val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val used = BooleanArray(m)

        // Build MST using Kruskal
        for (e in edges) {
            if (dsu.union(e.u, e.v)) {
                used[e.id] = true
                adj[e.u].add(Pair(e.v, e.w))
                adj[e.v].add(Pair(e.u, e.w))
            }
        }

        val LOG = 18
        val parent = Array(LOG) { IntArray(n + 1) }
        val minEdge = Array(LOG) { IntArray(n + 1) { Int.MAX_VALUE } }
        val depth = IntArray(n + 1)

        fun dfs(u: Int, p: Int) {
            for ((v, w) in adj[u]) {
                if (v == p) continue
                depth[v] = depth[u] + 1
                parent[0][v] = u
                minEdge[0][v] = w
                dfs(v, u)
            }
        }

        // Graph may not be connected, run DFS from all components
        for (i in 1..n) {
            if (depth[i] == 0) {
                dfs(i, 0)
            }
        }

        // Build binary lifting tables
        for (k in 1 until LOG) {
            for (i in 1..n) {
                val mid = parent[k - 1][i]
                parent[k][i] = parent[k - 1][mid]
                minEdge[k][i] = min(minEdge[k - 1][i], minEdge[k - 1][mid])
            }
        }

        fun query(u0: Int, v0: Int): Int {
            var u = u0
            var v = v0
            var ans = Int.MAX_VALUE

            if (depth[u] < depth[v]) {
                val tmp = u; u = v; v = tmp
            }

            for (k in LOG - 1 downTo 0) {
                if (depth[u] - (1 shl k) >= depth[v]) {
                    ans = min(ans, minEdge[k][u])
                    u = parent[k][u]
                }
            }

            if (u == v) return ans

            for (k in LOG - 1 downTo 0) {
                if (parent[k][u] != parent[k][v]) {
                    ans = min(ans, minEdge[k][u])
                    ans = min(ans, minEdge[k][v])
                    u = parent[k][u]
                    v = parent[k][v]
                }
            }

            ans = min(ans, minEdge[0][u])
            ans = min(ans, minEdge[0][v])

            return ans
        }

        var bestValue = Int.MAX_VALUE
        var bestEdge: Edge? = null

        for (e in edges) {
            if (!used[e.id]) {
                val minOnPath = query(e.u, e.v)
                if (minOnPath < bestValue) {
                    bestValue = minOnPath
                    bestEdge = e
                }
            }
        }

        val e = bestEdge!!

        // Recover path in MST
        val path = mutableListOf<Int>()
        val stack = Stack<Int>()
        val visited = BooleanArray(n + 1)
        val parent2 = IntArray(n + 1) { -1 }

        stack.push(e.u)
        visited[e.u] = true

        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (node == e.v) break
            for ((nei, _) in adj[node]) {
                if (!visited[nei]) {
                    visited[nei] = true
                    parent2[nei] = node
                    stack.push(nei)
                }
            }
        }

        var cur = e.v
        while (cur != -1) {
            path.add(cur)
            cur = parent2[cur]
        }

        path.reverse()

        out.append(bestValue).append(" ").append(path.size).append("\n")
        for (v in path) out.append(v).append(" ")
        out.append("\n")
    }

    print(out)
}