package graphs

import java.util.Stack

class GraphList {
    private val adjMap = mutableMapOf<Char, MutableList<Char>>()

    fun addVertex(vertex: Char) {
        if (!adjMap.containsKey(vertex)) {
            adjMap[vertex] = mutableListOf()
        }
    }

    fun addEdge(from: Char, to: Char) {
        // checking for vertices exist or not
        addVertex(from)
        addVertex(to)
        adjMap[from]?.add(to)
        adjMap[to]?.add(from)
    }

    fun removeEdge(from: Char, to: Char) {
        adjMap[from]?.remove(to)
        adjMap[to]?.remove(from)
    }

    fun removeVertex(vertex: Char) {
        adjMap[vertex]?.forEach { linkToVertex ->
            adjMap[linkToVertex]?.remove(vertex)
        }
        adjMap.remove(vertex)
    }

    override fun toString(): String {
        return adjMap.toString()
    }

    // TC => O(V+E) and SC => O(V)
    fun bfs(vertex: Char) {
        print("BFS: ")
        val visited = HashSet<Char>()
        visited.add(vertex)
        val queue = ArrayDeque<Char>()
        queue.add(vertex) // every add of vertex -> O(v)
        while (queue.isNotEmpty()) {
            val traversedV = queue.removeFirst() // optimized deque -> O(1)
            print("$traversedV ")
            for (adjacentVertex in adjMap[traversedV]
                ?: mutableListOf()) { // every edge twice -> O(E)
                if (adjacentVertex !in visited) {
                    visited.add(adjacentVertex)
                    queue.add(adjacentVertex)
                }
            }
        }
    }

    // TC => O(V+E) and SC => O(V)
    fun dfs(vertex: Char) {
        print("DFS: ")
        val visited = HashSet<Char>()
        val stack = Stack<Char>()
        stack.push(vertex) // every add of vertex -> O(v)
        while (stack.isNotEmpty()) {
            val traversedV = stack.pop() // optimized stack -> O(1)
            if (traversedV !in visited) {
                print("$traversedV ")
                visited.add(traversedV)
            }
            for (adjacentVertex in adjMap[traversedV] ?: mutableListOf()) { // every edge twice -> O(E)
                if (adjacentVertex !in visited) {
                    stack.push(adjacentVertex)
                }
            }
        }
    }
}

fun main() {
    val graph = GraphList()
    graph.addVertex('a')
    graph.addVertex('b')
    graph.addVertex('c')
    graph.addVertex('d')
    graph.addVertex('e')
    graph.addEdge('a', 'b')
    graph.addEdge('a', 'c')
    graph.addEdge('b', 'e')
    graph.addEdge('c', 'd')
    graph.addEdge('d', 'e')
    println(graph)
//    graph.removeEdge('a', 'c')
//    println("After removing edge")
//    println(graph)
//    graph.removeVertex('d')
//    println("After removing vertex")
//    println(graph)
    graph.bfs('a')
    println()
    graph.dfs('a')
}