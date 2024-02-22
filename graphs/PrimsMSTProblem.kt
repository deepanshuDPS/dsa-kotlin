package graphs

class PrimsGraph(
    private val edges: Array<Array<Int>>,
) {
    private val vertices = edges.size
    private val mst = arrayListOf<Path>()
    private val graph: ArrayList<Path> = arrayListOf()
    private val nodes: ArrayList<Char> = arrayListOf()


    fun addNode(node: Char) {
        nodes.add(node)
    }

    // TC => o(v^3) and SC => O(v^2)
    fun primsAlgo() {
        // index wise visited nodes in boolean
        val visited = arrayOfNulls<Boolean>(vertices)
        var e = 0
        // first index visited as node
        visited[0] = true
        // edges in MST will always = v - 1
        while (e < vertices - 1) {
            var min = Int.MAX_VALUE
            var s = 0
            var d = 0
            for (i in 0 until vertices) {
                if (visited[i] == true) {
                    for (j in 0 until vertices) {
                        // checking cycle of visited and minimum of the till s->d path
                        if (visited[j] != true && edges[i][j]!=0 && min > edges[i][j]) {
                            min = edges[i][j]
                            s = i
                            d = j
                        }
                    }
                }
            }
            mst.add(Path(nodes[s], nodes[d], min))
            visited[d] = true
            e++
        }
        println("MST solution:\n$mst")
    }
}

fun main() {
    val g = PrimsGraph(
        arrayOf(
            arrayOf(0, 10, 20, 0, 0),
            arrayOf(10, 0, 30, 5, 0),
            arrayOf(20, 30, 0, 15, 6),
            arrayOf(0, 5, 15, 0, 8),
            arrayOf(0, 0, 6, 8, 0)
        )
    )
    g.addNode('A')
    g.addNode('B')
    g.addNode('C')
    g.addNode('D')
    g.addNode('E')
    g.primsAlgo()
}