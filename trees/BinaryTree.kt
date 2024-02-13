package trees

data class TreeNode(
    val data: String,
    var leftChild: TreeNode? = null,
    var rightChild: TreeNode? = null
)

// TC and SC => O(n)
fun preOrderTraversal(rootNode: TreeNode? = null) {
    if (rootNode == null) return
    println(rootNode.data)
    preOrderTraversal(rootNode.leftChild)  // O(n/2)
    preOrderTraversal(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun inOrderTraversal(rootNode: TreeNode? = null) {
    if (rootNode == null) return
    inOrderTraversal(rootNode.leftChild)  // O(n/2)
    println(rootNode.data)
    inOrderTraversal(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun postOrderTraversal(rootNode: TreeNode? = null) {
    if (rootNode == null) return
    preOrderTraversal(rootNode.leftChild)  // O(n/2)
    preOrderTraversal(rootNode.rightChild) // O(n/2)
    println(rootNode.data)
}

// TC and SC => O(n)
fun levelOrderTraversal(rootNode: TreeNode? = null) {
    if (rootNode == null) return
    val queue = QueueLList()
    queue.enqueue(rootNode) // enqueue
    while (!queue.isEmpty()) {          //  O(n)
        val tempRoot = queue.dequeue()
        println(tempRoot?.data) // dequeue
        if (tempRoot?.leftChild != null) {
            queue.enqueue(tempRoot.leftChild!!)
        }
        if (tempRoot?.rightChild != null) {
            queue.enqueue(tempRoot.rightChild!!)
        }
    }
}

// TC and SC => O(n)
fun searchUsingLOT(rootNode: TreeNode? = null, value: String): String {
    if (rootNode == null) return "Not Found"
    val queue = QueueLList()
    queue.enqueue(rootNode)             // enqueue
    while (!queue.isEmpty()) {          //  O(n)
        val tempRoot = queue.dequeue()
        if (tempRoot?.data == value) return "Found"
        if (tempRoot?.leftChild != null) {
            queue.enqueue(tempRoot.leftChild!!)
        }
        if (tempRoot?.rightChild != null) {
            queue.enqueue(tempRoot.rightChild!!)
        }
    }
    return "Not Found"
}

// TC and SC => O(n)
fun insertUsingLOT(rootNode: TreeNode? = null, newValue: String): Boolean {
    if (rootNode == null) return false
    val queue = QueueLList()
    queue.enqueue(rootNode)             // enqueue
    while (!queue.isEmpty()) {          //  O(n)
        val tempRoot = queue.dequeue()
        if (tempRoot?.leftChild != null) {
            queue.enqueue(tempRoot.leftChild!!)
        } else {
            tempRoot?.leftChild = TreeNode(newValue)
            return true
        }
        if (tempRoot.rightChild != null) {
            queue.enqueue(tempRoot.rightChild!!)
        } else {
            tempRoot.rightChild = TreeNode(newValue)
            return true
        }
    }
    return false
}

fun main() {
    val rootNode = TreeNode("Drinks")
    val hotDrinkNode = TreeNode("Hot")
    val coldDrinkNode = TreeNode("Cold")
    rootNode.leftChild = hotDrinkNode
    rootNode.rightChild = coldDrinkNode

    hotDrinkNode.leftChild = TreeNode("Coffee")
    hotDrinkNode.rightChild = TreeNode("Tea")

    println()
    preOrderTraversal(rootNode)
    println()
    inOrderTraversal(rootNode)
    println()
    postOrderTraversal(rootNode)
    println()
    levelOrderTraversal(rootNode)
    println()
    println(searchUsingLOT(rootNode, "Tea"))

    insertUsingLOT(rootNode,"Alcoholic")
    insertUsingLOT(rootNode,"Non-Alcoholic")

    println()
    inOrderTraversal(rootNode)

}