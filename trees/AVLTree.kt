package trees

import java.lang.Integer.max


// TC and SC => O(n)
fun preOrderTraversalAVL(rootNode: TreeNode?) {
    if (rootNode == null) return
    println(rootNode.value)
    preOrderTraversalAVL(rootNode.leftChild)  // O(n/2)
    preOrderTraversalAVL(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun inOrderTraversalAVL(rootNode: TreeNode?) {
    if (rootNode == null) return
    inOrderTraversalAVL(rootNode.leftChild)  // O(n/2)
    println(rootNode.value)
    inOrderTraversalAVL(rootNode.rightChild) // O(n/2)
}

// TC and SC => O(n)
fun postOrderTraversalAVL(rootNode: TreeNode?) {
    if (rootNode == null) return
    preOrderTraversalAVL(rootNode.leftChild)  // O(n/2)
    preOrderTraversalAVL(rootNode.rightChild) // O(n/2)
    println(rootNode.value)
}

// TC and SC => O(n)
fun levelOrderTraversalAVL(rootNode: TreeNode?) {
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
fun searchAVL(rootNode: TreeNode?, sValue: Int): String {
    if (rootNode?.value == sValue) return "Found"
    if (sValue <= (rootNode?.value ?: -1)) {
        if (rootNode?.leftChild == null) {
            return "Not Found"
        }
        return searchAVL(rootNode.leftChild, sValue)
    } else {
        if (rootNode?.rightChild == null) {
            return "Not Found"
        }
        return searchAVL(rootNode.rightChild, sValue)
    }
}

// TC and SC => O(logN)
fun insertAVL(rootNode: TreeNode?, newValue: Int): TreeNode? {
    if (rootNode == null) {
        return TreeNode(value = newValue)
    }
    if (newValue <= (rootNode.value ?: -1)) {
        rootNode.leftChild = insertAVL(rootNode.leftChild, newValue)
    } else {
        rootNode.rightChild = insertAVL(rootNode.rightChild, newValue)
    }
    // BST insertion is completed above
    // now init new heights
    rootNode.height = 1 + max(getHeight(rootNode.leftChild), getHeight(rootNode.rightChild))
    val balance = getBalance(rootNode)
    if (balance > 1 && newValue <= (rootNode.leftChild?.value ?: -1)) {
        // Left-> (>1)  Left-> (value inserted at left) condition
        return rightRotate(rootNode)
    } else if (balance > 1 && newValue > (rootNode.leftChild?.value ?: -1)) {
        // Left-> (>1)  Right-> (value inserted at right) condition
        rootNode.leftChild = leftRotate(rootNode.leftChild)
        return rightRotate(rootNode)
    }
    if (balance < -1 && newValue > (rootNode.rightChild?.value ?: -1)) {
        // Right-> (< -1)  Right-> (value inserted at right) condition
        return leftRotate(rootNode)
    }
    if (balance < -1 && newValue <= (rootNode.rightChild?.value ?: -1)) {
        // Right-> (< -1)  Left-> (value inserted at left) condition
        rootNode.rightChild = rightRotate(rootNode.rightChild)
        return leftRotate(rootNode)
    }
    return rootNode // no rotation
}

fun getMinValueNode(rootNode: TreeNode?): TreeNode? {
    if (rootNode?.leftChild == null) {
        return rootNode
    }
    return getMinValueNode(rootNode)
}

// TC and SC => O(logN)
fun deleteNodeAVL(rootNode: TreeNode?, dValue: Int): TreeNode? {
    if (rootNode == null) return null
    if (dValue < (rootNode.value ?: -1)) {
        rootNode.leftChild = deleteNodeAVL(rootNode.leftChild, dValue)
    } else if (dValue > (rootNode.value ?: -1)) {
        rootNode.rightChild = deleteNodeAVL(rootNode.rightChild, dValue)
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

        val temp = getMinValueNode(rootNode.rightChild)
        rootNode.value = temp?.value
        rootNode.rightChild = deleteNodeAVL(rootNode.rightChild, temp?.value!!)
        // BST deletion is completed above
        // now init new heights
    }
    rootNode.height = 1 + max(getHeight(rootNode.leftChild), getHeight(rootNode.rightChild))
    val balance = getBalance(rootNode)
    if (balance > 1 && getBalance(rootNode.leftChild) >= 0) {
        // Left-> (>1)  Left-> (value inserted at left) condition
        return rightRotate(rootNode)
    } else if (balance > 1 && getBalance(rootNode.leftChild) < 0) {
        // Left-> (>1)  Right-> (value inserted at right) condition
        rootNode.leftChild = leftRotate(rootNode.leftChild)
        return rightRotate(rootNode)
    }
    if (balance < -1 && getBalance(rootNode.rightChild) <= 0) {
        // Right-> (< -1)  Right-> (value inserted at right) condition
        return leftRotate(rootNode)
    }
    if (balance < -1 && getBalance(rootNode.rightChild) > 0) {
        // Right-> (< -1)  Left-> (value inserted at left) condition
        rootNode.rightChild = rightRotate(rootNode.rightChild)
        return leftRotate(rootNode)
    }
    return rootNode
}

// TC and SC => O(1)
fun deleteTreeAVL(rootNode: TreeNode?) {
    rootNode?.value = null
    rootNode?.leftChild = null
    rootNode?.rightChild = null
}

fun getHeight(rootNode: TreeNode?): Int {
    return rootNode?.height ?: 0
}

fun getBalance(rootNode: TreeNode?): Int {
    if (rootNode == null) return 0
    return getHeight(rootNode.leftChild) - getHeight(rootNode.rightChild)
}

// TC and SC => O(1)
fun rightRotate(disbalancedNode: TreeNode?): TreeNode? {
    val newRoot = disbalancedNode?.leftChild
    // to balance the left child right children
    disbalancedNode?.leftChild = disbalancedNode?.leftChild?.rightChild
    // and then assign newRoot right child -> disbalanced node
    newRoot?.rightChild = disbalancedNode
    disbalancedNode?.height =
        1 + max(getHeight(disbalancedNode?.leftChild), getHeight(disbalancedNode?.rightChild))
    newRoot?.height =
        1 + max(getHeight(newRoot?.leftChild), getHeight(newRoot?.rightChild))
    return newRoot
}

// TC and SC => O(1)
fun leftRotate(disbalancedNode: TreeNode?): TreeNode? {
    val newRoot = disbalancedNode?.rightChild
    // to balance the left child right children
    disbalancedNode?.rightChild = disbalancedNode?.rightChild?.leftChild
    // and then assign newRoot right child -> disbalanced node
    newRoot?.leftChild = disbalancedNode
    disbalancedNode?.height =
        1 + max(getHeight(disbalancedNode?.leftChild), getHeight(disbalancedNode?.rightChild))
    newRoot?.height =
        1 + max(getHeight(newRoot?.leftChild), getHeight(newRoot?.rightChild))
    return newRoot
}

fun main() {
    var rootNode: TreeNode? = TreeNode(value = 5)
    rootNode = insertAVL(rootNode, 10)
    rootNode = insertAVL(rootNode, 15)
    rootNode = insertAVL(rootNode, 20)
    preOrderTraversalAVL(rootNode)
    rootNode = deleteNodeAVL(rootNode, 20)
    preOrderTraversalAVL(rootNode)
    deleteTreeAVL(rootNode)
}