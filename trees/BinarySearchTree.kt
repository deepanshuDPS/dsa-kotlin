package trees


// TC and SC => O(n)
fun preOrderTraversalBST(rootNode: TreeNode?) {
    if (rootNode == null) return
    println(rootNode.value)
    preOrderTraversalBST(rootNode.leftChild)  // O(n/2)
    preOrderTraversalBST(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun inOrderTraversalBST(rootNode: TreeNode?) {
    if (rootNode == null) return
    inOrderTraversalBST(rootNode.leftChild)  // O(n/2)
    println(rootNode.value)
    inOrderTraversalBST(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun postOrderTraversalBST(rootNode: TreeNode?) {
    if (rootNode == null) return
    preOrderTraversalBST(rootNode.leftChild)  // O(n/2)
    preOrderTraversalBST(rootNode.rightChild) // O(n/2)
    println(rootNode.value)
}

// TC and SC => O(n)
fun levelOrderTraversalBST(rootNode: TreeNode?) {
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
fun insertBST(rootNode: TreeNode?, newValue: Int): TreeNode {
    if (rootNode == null) {
        return TreeNode(value = newValue)
    }
    if (newValue <= (rootNode.value ?: -1)) {
        rootNode.leftChild = insertBST(rootNode.leftChild, newValue)
    } else {
        rootNode.rightChild = insertBST(rootNode.rightChild, newValue)
    }
    return rootNode
}

fun minValueNodeBST(rootNode: TreeNode?): TreeNode? {
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

        val temp = minValueNodeBST(rootNode.rightChild)
        rootNode.value = temp?.value
        rootNode.rightChild = deleteNodeBST(rootNode.rightChild, temp?.value!!)
        return rootNode
    }
    return rootNode
}

// TC and SC => O(1)
fun deleteTreeBST(rootNode: TreeNode?) {
    rootNode?.value = null
    rootNode?.leftChild = null
    rootNode?.rightChild = null
}


fun main() {
    val rootNode = TreeNode(value = 70)
    insertBST(rootNode, 50)
    insertBST(rootNode, 90)
    insertBST(rootNode, 30)
    insertBST(rootNode, 60)
    insertBST(rootNode, 80)
    insertBST(rootNode, 100)
    preOrderTraversalBST(rootNode)
    println(searchBST(rootNode, 30))
    println()
    println(deleteNodeBST(rootNode, 70)?.value)
    println()
    preOrderTraversalBST(rootNode)
    deleteTreeBST(rootNode)
}