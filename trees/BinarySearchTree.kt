package trees


// TC and SC => O(n)
fun preOrderTraversalBST(rootNode: TreeNode? = null) {
    if (rootNode == null) return
    println(rootNode.data)
    preOrderTraversal(rootNode.leftChild)  // O(n/2)
    preOrderTraversal(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun inOrderTraversalBST(rootNode: TreeNode? = null) {
    if (rootNode == null) return
    inOrderTraversal(rootNode.leftChild)  // O(n/2)
    println(rootNode.data)
    inOrderTraversal(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun postOrderTraversalBST(rootNode: TreeNode? = null) {
    if (rootNode == null) return
    preOrderTraversal(rootNode.leftChild)  // O(n/2)
    preOrderTraversal(rootNode.rightChild) // O(n/2)
    println(rootNode.data)
}

// TC and SC => O(n)
fun levelOrderTraversalBST(rootNode: TreeNode? = null) {
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

// TC and SC => O(logN)
fun searchBST(rootNode: TreeNode?, sValue: Int): String {
    if (rootNode?.value == sValue) return "Found"
    if (sValue <= (rootNode?.value ?: -1)) {
        if (rootNode?.leftChild == null) {
            return "Not Found"
        }
        return searchBST(rootNode.leftChild, sValue)
    } else {
        if (rootNode?.rightChild == null) {
            return "Not Found"
        }
        return searchBST(rootNode.rightChild, sValue)
    }
}

// TC and SC => O(logN)
fun insertBST(rootNode: TreeNode?, newValue: Int): Boolean {
    if (rootNode?.value == null) {
        rootNode?.value = newValue
        return true
    }
    return if (newValue <= (rootNode.value ?: -1)) {
        if (rootNode.leftChild == null) {
            rootNode.leftChild = TreeNode(value = newValue)
        }
        insertBST(rootNode.leftChild, newValue)
    } else {
        if (rootNode.rightChild == null) {
            rootNode.rightChild = TreeNode(value = newValue)
        }
        insertBST(rootNode.rightChild, newValue)
    }
}

fun smallestNodeBST(rootNode: TreeNode?): TreeNode? {
    var tempRoot = rootNode
    while (tempRoot?.leftChild != null) {
        tempRoot = tempRoot.leftChild
    }
    return tempRoot
}

// TC and SC => O(logN)
fun deleteNodeBST(rootNode: TreeNode?, dValue: Int): TreeNode? {
    if (rootNode == null) return null
    if (dValue < (rootNode.value ?: -1)) {
        rootNode.leftChild = deleteNodeBST(rootNode.leftChild, dValue)
    } else if (dValue > (rootNode.value ?: -1)) {
        rootNode.rightChild = deleteNodeBST(rootNode.rightChild, dValue)
    } else {
        // if left child is null return right child
        if (rootNode.leftChild == null) {
            val temp = rootNode.rightChild
            rootNode.value = null
            return temp
        }
        // if right child is null return left child
        if (rootNode.rightChild == null) {
            val temp = rootNode.leftChild
            rootNode.value = null
            return temp
        }

        val temp = smallestNodeBST(rootNode.rightChild)
        rootNode.value = temp?.value
        rootNode.rightChild = deleteNodeBST(rootNode.rightChild, temp?.value!!)
        return rootNode
    }
    return null
}

// TC and SC => O(1)
fun deleteTreeBST(rootNode: TreeNode?) {
    rootNode?.value = null
    rootNode?.leftChild = null
    rootNode?.rightChild = null
}


fun main() {
    val rootNode = TreeNode()
    insertBST(rootNode, 70)
    insertBST(rootNode, 50)
    insertBST(rootNode, 90)
    insertBST(rootNode, 30)
    insertBST(rootNode, 60)
    insertBST(rootNode, 80)
    insertBST(rootNode, 100)
    preOrderTraversalBST(rootNode)
    searchBST(rootNode, 30)
    deleteNodeBST(rootNode, 80)
    deleteTreeBST(rootNode)
}