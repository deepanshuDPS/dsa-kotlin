package trees


// TC and SC => O(n)
fun inOrderTraversalValue(rootNode: TreeNode?) {
    if (rootNode == null) return
    inOrderTraversalValue(rootNode.leftChild)  // O(n/2)
    print("${rootNode.value} ")
    inOrderTraversalValue(rootNode.rightChild) // O(n/2)
}

/**
We can simply insert elements in avl tree and it will balance tree automatically
but here we will divide elements by mid and insert on left and right child O(n)/O(logN)
 **/
fun sortedArrayToBalancedTree(list: Array<Int>, min: Int, max: Int): TreeNode? {
    if (min > max) return null
    val mid = (min + max) / 2
    val newNode = TreeNode(value = list[mid])
    newNode.leftChild = sortedArrayToBalancedTree(list, min, mid - 1)
    newNode.rightChild = sortedArrayToBalancedTree(list, mid + 1, max)
    return newNode
}

// inorder successor of the given node in BST
// TC => O(n) and SC => O(n)
fun inorderSuccessor(rootNode: TreeNode?, nValue: Int, list: ArrayList<Int>): Int {
    if (rootNode == null) return -1
    inorderSuccessor(rootNode.leftChild, nValue, list)  // O(n/2)
    // after every entry return check the 2nd last index to be nValue
    // and return the last index will be the inOrderSuccessor
    if (list.size > 1 && list[list.lastIndex - 1] == nValue)
        return list[list.lastIndex]
    list.add(rootNode.value ?: -1)
    inorderSuccessor(rootNode.rightChild, nValue, list)
    if (list.size > 1 && list[list.lastIndex - 1] == nValue)
        return list[list.lastIndex]
    return -1
}


fun main() {
    val rootNode = sortedArrayToBalancedTree(arrayOf(1, 3, 5, 7, 9, 11, 33, 55), 0, 7)
    inOrderTraversalValue(rootNode)
    println("\nInOrder successor: " + inorderSuccessor(rootNode, 55, arrayListOf()))
}