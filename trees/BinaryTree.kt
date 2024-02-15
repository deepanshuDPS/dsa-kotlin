package trees

// TC and SC => O(n)
fun preOrderTraversal(rootNode: TreeNode?) {
    if (rootNode == null) return
    println(rootNode.data)
    preOrderTraversal(rootNode.leftChild)  // O(n/2)
    preOrderTraversal(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun inOrderTraversal(rootNode: TreeNode?) {
    if (rootNode == null) return
    inOrderTraversal(rootNode.leftChild)  // O(n/2)
    println(rootNode.data)
    inOrderTraversal(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun postOrderTraversal(rootNode: TreeNode?) {
    if (rootNode == null) return
    preOrderTraversal(rootNode.leftChild)  // O(n/2)
    preOrderTraversal(rootNode.rightChild) // O(n/2)
    println(rootNode.data)
}

// TC and SC => O(n)
fun levelOrderTraversal(rootNode: TreeNode?) {
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
fun searchUsingLOT(rootNode: TreeNode?, value: String): String {
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
fun insertUsingLOT(rootNode: TreeNode?, newValue: String): Boolean {
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

// TC and SC => O(n)
fun getDeepestNode(rootNode: TreeNode?): String? {
    if (rootNode == null) return null
    val queue = QueueLList()
    queue.enqueue(rootNode)             // enqueue
    var tempRoot: TreeNode? = null
    while (!queue.isEmpty()) {          //  O(n)
        tempRoot = queue.dequeue()
        if (tempRoot?.leftChild != null) {
            queue.enqueue(tempRoot.leftChild!!)
        }
        if (tempRoot?.rightChild != null) {
            queue.enqueue(tempRoot.rightChild!!)
        }
    }
    return tempRoot?.data // will return the last traversed node
}

// TC and SC => O(n)
fun deleteDeepestNode(rootNode: TreeNode?, dNode: String?): String? {
    if (rootNode == null) return "Tree is empty"
    val queue = QueueLList()
    queue.enqueue(rootNode)             // enqueue
    while (!queue.isEmpty()) {          //  O(n)
        val tempRoot = queue.dequeue()
        if (tempRoot?.data == dNode) {
            tempRoot?.data = null
        }
        if (tempRoot?.leftChild != null) {
            if (tempRoot.leftChild?.data == dNode) {
                tempRoot.leftChild = null
                return "Deleted"
            }
            queue.enqueue(tempRoot.leftChild!!)
        }
        if (tempRoot?.rightChild != null) {
            if (tempRoot.rightChild?.data == dNode) {
                tempRoot.rightChild = null
                return "Deleted"
            }
            queue.enqueue(tempRoot.rightChild!!)
        }
    }
    return null
}

// TC and SC => O(n)
fun deleteNode(rootNode: TreeNode?, node: String): String {
    if (rootNode == null) return "Tree is empty"
    val queue = QueueLList()
    queue.enqueue(rootNode)             // enqueue
    while (!queue.isEmpty()) {          //  O(n)
        val tempRoot = queue.dequeue()
        if (tempRoot?.data == node) {
            val dNode = getDeepestNode(rootNode) // finding deepest node
            if (dNode != null) {
                deleteDeepestNode(rootNode, dNode) // delete deepest node
                tempRoot.data = dNode // replacing deepest node value with node to delete
                return "Node deleted successfully"
            }
        }
        if (tempRoot?.leftChild != null) {
            queue.enqueue(tempRoot.leftChild!!)
        }
        if (tempRoot?.rightChild != null) {
            queue.enqueue(tempRoot.rightChild!!)
        }
    }
    return "Not Found"
}

// TC and SC => O(1)
fun deleteTree(rootNode: TreeNode?) {
    rootNode?.data = null
    rootNode?.leftChild = null
    rootNode?.rightChild = null
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

    insertUsingLOT(rootNode, "Alcoholic")
    insertUsingLOT(rootNode, "Non-Alcoholic")

    println()
    levelOrderTraversal(rootNode)

    deleteNode(rootNode, "Hot")
    println()
    levelOrderTraversal(rootNode)
    deleteTree(rootNode)
}