package graphs

class DisjointSet(
    private val vertices: Array<Char>
) {

    private val parent: HashMap<Char, Char> by lazy {
        HashMap<Char, Char>().also { map ->
            vertices.forEach {
                map[it] = it
            }
        }
    }

    // set size or child size -> for a vertex as parent
    private val ranks: HashMap<Char, Int> by lazy {
        HashMap<Char, Int>().also { map ->
            vertices.forEach {
                map[it] = 0
            }
        }
    }

    // finding the parents recursively till end
    // TC(Log V)
    fun find(vertex: Char): Char {
        return if (parent[vertex] == vertex) vertex
        else find(parent[vertex]!!)
    }

    fun union(x: Char, y: Char) {
        val xRoot = find(x)
        val yRoot = find(y)
        if ((ranks[xRoot] ?: 0) < (ranks[yRoot] ?: 0)) {
            parent[xRoot] = yRoot
        } else if ((ranks[xRoot] ?: 0) > (ranks[yRoot] ?: 0)) {
            parent[yRoot] = xRoot
        } else {
            parent[yRoot] = xRoot
            ranks[xRoot]?.plus(1)
        }
    }

}


class KruskalGraph(
    private val v: Int
) {
    private val mst = arrayListOf<Path>()
    private val graph: ArrayList<Path> = arrayListOf()
    private val nodes: ArrayList<Char> = arrayListOf()

    fun addEdge(path: Path) {
        graph.add(path)
    }

    fun addNode(node: Char) {
        nodes.add(node)
    }

    // TC => O(V + ELogV + E) => O(ELogE) and SC => O(E)
    fun kruskalAlgo() {
        var i = 0;
        var e = 0
        val disjointSet = DisjointSet(nodes.toTypedArray())
        // sort by edges and checking cycle
        graph.sortBy { it.w } // O(ELogE)
        // edges in MST will always = v - 1
        while (e < v - 1) { // O(V)
            val path = graph[i++]
            val x = disjointSet.find(path.s) // O(E)
            val y = disjointSet.find(path.d) // O(E)
            // means parents are different for both it means no cycle
            if (x != y) {
                e++
                // make same parent
                disjointSet.union(x, y)
                // add in MST graph
                mst.add(path)
            }
        }
        println("MST solution:\n$mst")
    }
}

fun main() {
    val g = KruskalGraph(5)
    g.addNode('A')
    g.addNode('B')
    g.addNode('C')
    g.addNode('D')
    g.addNode('E')
    g.addEdge(Path('A', 'B', 5))
    g.addEdge(Path('A', 'C', 13))
    g.addEdge(Path('A', 'E', 15))
    g.addEdge(Path('B', 'A', 5))
    g.addEdge(Path('B', 'C', 10))
    g.addEdge(Path('B', 'D', 8))
    g.addEdge(Path('C', 'A', 13))
    g.addEdge(Path('C', 'B', 10))
    g.addEdge(Path('C', 'E', 20))
    g.addEdge(Path('C', 'D', 6))
    g.addEdge(Path('D', 'B', 8))
    g.addEdge(Path('D', 'C', 6))
    g.addEdge(Path('E', 'A', 15))
    g.addEdge(Path('E', 'C', 20))

    g.kruskalAlgo()
}
