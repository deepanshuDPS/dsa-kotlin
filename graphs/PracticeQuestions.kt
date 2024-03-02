package graphs

// using level order traversal BFS -> Queue
fun isPathExistInGraph(graphMap: HashMap<Char, ArrayList<Char>>, start: Char, end: Char): Boolean {
    val visited = HashSet<Char>()
    visited.add(start)
    val queue = ArrayDeque<Char>()
    queue.add(start)
    while (queue.isNotEmpty()) {
        val visit = queue.removeFirst()
        if (visit == end) return true // reached to end vertex from start
        for (adjacentVertex in graphMap[visit] ?: arrayListOf()) { // every edge twice -> O(E)
            if (adjacentVertex !in visited) {
                visited.add(adjacentVertex)
                queue.add(adjacentVertex)
            }
        }
    }
    return false
}


fun finishDependentTask(graphMap: HashMap<Char, ArrayList<Char>>) {
    val vertices = graphMap.keys
    val dependentVertices = hashSetOf<Char>()
    for (i in graphMap) {
        for (j in i.value) {
            dependentVertices.add(j)
        }
    }
    val independentNodes = vertices.subtract(dependentVertices)
    // topological sorting
    // and append independent nodes in front
}

fun main() {
    println(
        "Is Path Exist: " + isPathExistInGraph(
            hashMapOf(
                'a' to arrayListOf('b'),
                'b' to arrayListOf('e', 'a'),
                'c' to arrayListOf('d', 'e'),
                'd' to arrayListOf('c', 'e'),
                'e' to arrayListOf('a', 'b')
            ), 'a', 'c'
        )
    )

    println(
        "Order of tasks: " + finishDependentTask(
            hashMapOf(
                'a' to arrayListOf('b'),
                'b' to arrayListOf('e', 'a'),
                'c' to arrayListOf('d', 'e'),
                'd' to arrayListOf('c', 'e'),
                'e' to arrayListOf('a', 'b'),
                'f' to arrayListOf()
            )
        )
    )
}