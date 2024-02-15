package trees

class BinaryTreeList(private val capacity: Int) {

    // TC O(1) and SC O(n)
    private var list: Array<String?>? = arrayOfNulls<String?>(capacity)
    private var lastIndex = 0

    // TC O(1) and SC O(1)
    fun insert(data: String) {
        if (lastIndex + 1 == capacity) {
            println("Tree is full")
            return
        }
        list?.set(++lastIndex, data)
    }

    // TC O(n) and SC O(1)
    fun search(sData: String): String {
        for (i in 1 until lastIndex) {
            if (list?.get(i) == sData)
                return "Element Found"
        }
        return "Element not Found"
    }

    // TC O(n) and SC O(1)
    fun delete(dData: String) {
        if (lastIndex == 0) {
            println("Tree is empty")
            return
        }
        for (i in 1 until lastIndex) {
            if (list?.get(i) == dData) {
                list?.set(i, list?.get(lastIndex))
                list?.set(lastIndex--, null)
                println("Element deleted")
                return
            }
        }
    }

    // TC O(n) and SC O(1)
    fun preOrderTraversal(index: Int = 1) {
        if (index >= capacity || list?.get(index) == null) return
        println(list?.get(index))
        preOrderTraversal(index * 2)
        preOrderTraversal(index * 2 + 1)
    }

    // TC O(n) and SC O(1)
    fun inOrderTraversal(index: Int = 1) {
        if (index >= capacity || list?.get(index) == null) return
        inOrderTraversal(index * 2)
        println(list?.get(index))
        inOrderTraversal(index * 2 + 1)
    }

    // TC O(n) and SC O(1)
    fun postOrderTraversal(index: Int = 1) {
        if (index >= capacity || list?.get(index) == null) return
        postOrderTraversal(index * 2)
        postOrderTraversal(index * 2 + 1)
        println(list?.get(index))
    }

    // TC O(n) and SC O(1)
    fun levelOrderTraversal() {
        for (i in 1 until lastIndex+1) {
            println(list?.get(i))
        }
    }

    // TC O(1) and SC O(1)
    fun deleteBT() {
        list = null
    }
}

fun main() {
    val tree = BinaryTreeList(10)
    tree.insert("Drinks")
    tree.insert("Hot")
    tree.insert("Cold")
    tree.insert("Tea")
    tree.insert("Coffee")
    tree.insert("Alcoholic")
    tree.insert("Non Alcoholic")
    println("PreOrder")
    tree.preOrderTraversal()
    println("\nInOrder")
    tree.inOrderTraversal()
    println("\nPostOrder")
    tree.postOrderTraversal()
    println("\nLevelOrder")
    tree.levelOrderTraversal()

    println()
    println(tree.search("Hot"))
    tree.delete("Hot")
    println("\nLevelOrder")
    tree.levelOrderTraversal()

    tree.deleteBT()

}