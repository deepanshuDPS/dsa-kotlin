package graphs

import java.util.Stack

// directed graph
class Graph {
    private val graphMap = mutableMapOf<Char, MutableList<Char>>()
    private val vertices = HashSet<Char>()

    fun addEdge(vertex: Char, edge: Char) {
        vertices.add(vertex)
        vertices.add(edge)
        if(graphMap[vertex] == null){
            graphMap[vertex] = mutableListOf(edge)
            return
        }
        graphMap[vertex]?.add(edge)
    }

    private fun topSortUtil(vertex: Char, visited: HashSet<Char>, stack: Stack<Char>) {
        visited.add(vertex)

        // check edges of current vertex
        for (i in graphMap[vertex] ?: mutableListOf()) { // O(E)
            if (i !in visited) {
                // visit the depth vertices
                topSortUtil(i, visited, stack)
            }
        }
        stack.push(vertex)
    }

    // TC => O(V+E) and SC => SC => O(V+E)
    fun topologicalSort() {
        val visited = HashSet<Char>()
        val stack = Stack<Char>()

        for (k in vertices) {   // O(v)
            if (k !in visited) {
                topSortUtil(k, visited, stack)
            }
        }
        stack.reverse() // because vertex which is parent is pushed on last
        println(stack)
    }


}

fun main() {
    val graph = Graph()
    graph.addEdge('a', 'c')
    graph.addEdge('c', 'e')
    graph.addEdge('e', 'h')
    graph.addEdge('e', 'f')
    graph.addEdge('f', 'g')
    graph.addEdge('b', 'd')
    graph.addEdge('b', 'c')
    graph.addEdge('d', 'f')
    graph.topologicalSort()
}