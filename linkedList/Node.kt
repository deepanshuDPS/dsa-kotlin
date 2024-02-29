package linkedList

class Node(var data: Int, var next: Node? = null) {

    fun copy() = Node(data, next)
}
