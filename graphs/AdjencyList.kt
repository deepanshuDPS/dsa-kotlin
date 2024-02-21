package graphs

import java.util.Stack

class GraphList {
    private var adjMap = mutableMapOf<Char, MutableList<Char>>()

    fun setGraph(map: MutableMap<Char, MutableList<Char>>) {
        adjMap = map
    }

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
            for (adjacentVertex in adjMap[traversedV]
                ?: mutableListOf()) { // every edge twice -> O(E)
                if (adjacentVertex !in visited) {
                    stack.push(adjacentVertex)
                }
            }
        }
    }

//    def bfs(self, start, end):
//        queue = []
//        queue.append([start])
//        while queue:
//            path = queue.pop(0)
//            node = path[-1]
//            if node == end:
//                return path
//            for adjacent in self.gdict.get(node, []):
//                new_path = list(path)
//                new_path.append(adjacent)
//                queue.append(new_path)

    // SC => O(V) algorithm needs to store a queue of vertices that have been visited but not yet processed.
    // TC => O(E) because edges is traversed more than isolated vertices so E>V
    // TC => O(E) and SC => O(V)
    fun shortestPathBFS(startVertex: Char, endVertex: Char): ArrayList<Char> {
        val queue = ArrayDeque<ArrayList<Char>>()
        // adding first vertex path
        queue.add(arrayListOf(startVertex)) // every add of vertex -> O(v)
        while (queue.isNotEmpty()) {
            // this path is traversed and become starting point of other path
            val path = queue.removeFirst() // optimized deque -> O(1)
            // if this path is traversed here not printing
            val visited = path.last()
            // but checking the last destination (which is stored for parents) or not, if yes, returning till path
            // level by level first reached path
            if (visited == endVertex) return path
            for (adVertex in adjMap[visited] ?: mutableListOf()) {
                // continuing the last path (parents) till vertex to create new path
                val newPath = ArrayList(path)
                // adding adjacent vertex to create new path till new adj. vertex
                newPath.add(adVertex)
                // adding the new path to queue to create new level of paths
                queue.add(newPath)
            }
        }
        return arrayListOf()
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

    val graph2 = GraphList()
    graph2.setGraph(
        mutableMapOf(
            'a' to mutableListOf('b', 'c'),
            'b' to mutableListOf('d', 'g'),
            'c' to mutableListOf('d', 'e'),
            'd' to mutableListOf('f'),
            'e' to mutableListOf('f'),
            'g' to mutableListOf('f')

        )
    )
    println()
    println("Graph 2")
    println("Shortest path is: " + graph2.shortestPathBFS('a', 'g'))
}