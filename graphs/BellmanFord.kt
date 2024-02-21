package graphs

class Path(val s: Char, val d: Char, val w: Int)

class BellmanFordGraph(
    private val v: Int,
    private val graph: ArrayList<Path> = arrayListOf(),
    private val nodes: ArrayList<Char> = arrayListOf()
) {

    fun addEdge(path: Path) {
        graph.add(path)
    }

    fun addNode(node: Char) {
        nodes.add(node)
    }

    // TC => O(VE) and SC => O(V)
    fun bellmanFord(src: Char) {
        val dist = hashMapOf<Char, Int>()
        for (i in nodes) {
            dist[i] = Int.MAX_VALUE
        }
        dist[src] = 0

        for (i in 0 until v) {
            for (path in graph) {
                if (dist[path.s] != Int.MAX_VALUE &&
                    (dist[path.s]?.plus(path.w) ?: 0) < (dist[path.d] ?: 0)
                ) {
                    dist[path.d] = dist[path.s]?.plus(path.w) ?: 0
                }
            }
        }

        // run one extra time to check negative cycle
        for (path in graph) {
            if (dist[path.s] != Int.MAX_VALUE &&
                (dist[path.s]?.plus(path.w) ?: 0) < (dist[path.d] ?: 0)
            ) {
                println("Graph contains negative cycle")
                return
            }
        }

        println(dist)
    }

}

fun main() {
    val g = BellmanFordGraph(5)
    g.addNode('A')
    g.addNode('B')
    g.addNode('C')
    g.addNode('D')
    g.addNode('E')
    g.addEdge(Path('A', 'C', 6))
    g.addEdge(Path('A', 'D', 6))
    g.addEdge(Path('B', 'A', 3))
    g.addEdge(Path('C', 'D', 1))
    g.addEdge(Path('D', 'C', 2))
    g.addEdge(Path('D', 'B', 1))
    g.addEdge(Path('E', 'B', 4))
    g.addEdge(Path('E', 'D', 2))
    g.bellmanFord('E')
}