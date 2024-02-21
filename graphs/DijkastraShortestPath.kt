package graphs

import java.util.PriorityQueue

class Edge(
    val weight: Int,
    val startVertex: DNode,
    val targetVertex: DNode
)

class DNode(
    val name: Char,
    var visited: Boolean = false,
    // previous node that we come to this node
    var predecessor: DNode? = null,
    val neighbors: ArrayList<Edge> = arrayListOf(),
    var minDistance: Int = Int.MAX_VALUE
) {

    fun addEdge(weight: Int, destinationVertex: DNode) {
        val edge = Edge(weight, this, destinationVertex)
        neighbors.add(edge)
    }

    // comparing
    fun lessThan(otherNode: DNode): Int {
        return this.minDistance - otherNode.minDistance
    }

    override fun equals(other: Any?): Boolean {
        return this.name == (other as DNode).name
    }

    override fun toString(): String {
        return " [" + this.name + "-" + this.minDistance + "] "
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + visited.hashCode()
        result = 31 * result + (predecessor?.hashCode() ?: 0)
        result = 31 * result + neighbors.hashCode()
        result = 31 * result + minDistance
        return result
    }

}

class DijkastraShortestPath {

    // priority is defined by minimum distance in the list of nodes
    // makes this minHeap according to min distance
    private val heap = PriorityQueue(Comparator<DNode> { n1, n2 ->
        return@Comparator n1.lessThan(n2)
    })
    // O(V + E log V) -> possible using min heap
    // otherwise O(V^2) because every vertices traveled because we don't know about min distance vertex
    // TC => O(V + E log V) all vertices and edges traversed and SC => O(V+E)
    fun calculate(startVertex: DNode) {
        // assigning minimum distance 0 to start vertex
        startVertex.minDistance = 0

        // heap push start vertex
        heap.add(startVertex)

        while (heap.isNotEmpty()) { // heap is not empty
            // pop the element with the lowest distance
            println("Current min heap: $heap")
            val actualVertex = heap.remove()
            println("Popped: $actualVertex")
            if (actualVertex.visited) continue

            // consider the neighbors
            actualVertex.neighbors.forEach { edge ->
                val start = edge.startVertex
                val target = edge.targetVertex
                // this is the distance till start node + the neighbor weight
                val newDistance = start.minDistance + edge.weight
                // checking whether new distance is less than the target neighbor node
                // because may be neighbor node is traversed by somewhere else otherwise infinity
                if (newDistance < target.minDistance) {
                    target.minDistance = newDistance
                    target.predecessor = start
                    // update heap
                    // check duplicate here
                    if (!heap.contains(target)) {
                        heap.add(target)
                    }
                }
            }
            actualVertex.visited = true
        }
    }

    fun getShortestPath(tillVertex: DNode) {
        println("The shortest path to the vertex is: ${tillVertex.minDistance}")
        var tempVertex: DNode? = tillVertex
        // backtracking
        while (tempVertex != null) {
            print(tempVertex.name + " ")
            tempVertex = tempVertex.predecessor
        }
    }
}

fun main() {
    // Step 1 - create nodes
    val nodeA = DNode('A')
    val nodeB = DNode('B')
    val nodeC = DNode('C')
    val nodeD = DNode('D')
    val nodeE = DNode('E')
    val nodeF = DNode('F')
    val nodeG = DNode('G')
    val nodeH = DNode('H')

    // Step 2 - create edges
    nodeA.addEdge(6, nodeB)
    nodeA.addEdge(10, nodeC)
    nodeA.addEdge(9, nodeD)

    nodeB.addEdge(5, nodeD)
    nodeB.addEdge(16, nodeE)
    nodeB.addEdge(13, nodeF)

    nodeC.addEdge(6, nodeD)
    nodeC.addEdge(5, nodeH)
    nodeC.addEdge(21, nodeG)

    nodeD.addEdge(8, nodeF)
    nodeD.addEdge(7, nodeH)

    nodeE.addEdge(10, nodeG)

    nodeF.addEdge(4, nodeE)
    nodeF.addEdge(12, nodeG)

    nodeH.addEdge(2, nodeF)
    nodeH.addEdge(14, nodeG)

    val algorithm = DijkastraShortestPath()
    algorithm.calculate(nodeA)
    algorithm.getShortestPath(nodeG)
}




