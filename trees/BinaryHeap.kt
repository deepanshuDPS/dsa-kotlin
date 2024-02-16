package trees

class BinaryHeap(private val capacity: Int, private val type: String) {

    // TC O(1) and SC O(n)
    private var list: Array<Int?>? = arrayOfNulls<Int?>(capacity + 1)
    private var heapSize = 0
    private val maxSize = capacity + 1

    // TC O(1) and SC O(1)
    fun peekOfHeap(): Int? {
        return list?.get(1)
    }

    fun sizeOfHeap(): Int {
        return heapSize
    }


    // TC O(n) and SC O(1)
    fun preOrderTraversal(index: Int = 1) {
        if (index >= maxSize || list?.get(index) == null) return
        println(list?.get(index))
        preOrderTraversal(index * 2)
        preOrderTraversal(index * 2 + 1)
    }

    // TC O(n) and SC O(1)
    fun inOrderTraversal(index: Int = 1) {
        if (index >= maxSize || list?.get(index) == null) return
        inOrderTraversal(index * 2)
        println(list?.get(index))
        inOrderTraversal(index * 2 + 1)
    }

    // TC O(n) and SC O(1)
    fun postOrderTraversal(index: Int = 1) {
        if (index >= maxSize || list?.get(index) == null) return
        postOrderTraversal(index * 2)
        postOrderTraversal(index * 2 + 1)
        println(list?.get(index))
    }

    // TC O(n) and SC O(1)
    fun levelOrderTraversal() {
        for (i in 1 until maxSize) {
            list?.get(i)?.let {
                println(it)
            }
        }
    }


    // TC O(1) and SC O(1)
    fun deleteHeapTree() {
        list = null
    }

    // TC and SC => O(logN)
    private fun heapifyTreeInsert(index: Int) {
        val parentIndex = index / 2
        if (index <= 1) return
        else if (type == "min") {
            if ((list?.get(index) ?: -1) < (list?.get(parentIndex) ?: -1)) {
                val temp = list?.get(index)
                list?.set(index, list?.get(parentIndex))
                list?.set(parentIndex, temp)
            }
            heapifyTreeInsert(parentIndex)
        } else {
            if ((list?.get(index) ?: -1) > (list?.get(parentIndex) ?: -1)) {
                val temp = list?.get(index)
                list?.set(index, list?.get(parentIndex))
                list?.set(parentIndex, temp)
            }
            heapifyTreeInsert(parentIndex)
        }
    }


    // TC and SC => O(logN)
    private fun heapifyTreeExtract(index: Int) {
        val leftIndex = index * 2
        val rightIndex = index * 2 + 1
        val swapChild: Int
        if (heapSize < leftIndex) return // no children
        else if (leftIndex == heapSize) { // only one child
            if (type == "min") {
                if ((list?.get(index) ?: -1) > (list?.get(leftIndex) ?: -1)) {
                    val temp = list?.get(index)
                    list?.set(index, list?.get(leftIndex))
                    list?.set(leftIndex, temp)
                }
                return
            } else {
                if ((list?.get(index) ?: -1) < (list?.get(leftIndex) ?: -1)) {
                    val temp = list?.get(index)
                    list?.set(index, list?.get(leftIndex))
                    list?.set(leftIndex, temp)
                }
                return
            }
            // if only left child exist no more children to check
        } else {
            if (type == "min") {
                swapChild = if ((list?.get(leftIndex) ?: -1) < (list?.get(rightIndex) ?: -1))
                    leftIndex
                else
                    rightIndex
                if ((list?.get(index) ?: -1) > (list?.get(swapChild) ?: -1)) {
                    val temp = list?.get(index)
                    list?.set(index, list?.get(swapChild))
                    list?.set(swapChild, temp)
                }
            } else {
                swapChild = if ((list?.get(leftIndex) ?: -1) > (list?.get(rightIndex) ?: -1))
                    leftIndex
                else
                    rightIndex
                if ((list?.get(index) ?: -1) < (list?.get(swapChild) ?: -1)) {
                    val temp = list?.get(index)
                    list?.set(index, list?.get(swapChild))
                    list?.set(swapChild, temp)
                }
            }
            // if both children exist it means more children to check
            heapifyTreeExtract(swapChild)
        }
    }

    fun insertNode(value: Int) {
        if (heapSize == maxSize - 1) {
            println("Heap is full")
        } else {
            list?.set(++heapSize, value)
            heapifyTreeInsert(heapSize)
        }
    }

    fun extractNode(): Int {
        return if (heapSize == 0) {
            println("Heap is empty")
            -1
        } else {
            val extractedNode = list?.get(1)
            list?.set(1, list?.get(heapSize)) // assign last element to top
            list?.set(heapSize, null) // remove last element
            heapSize--
            // heapifyTreeExtract
            heapifyTreeExtract(1)
            extractedNode ?: -1
        }
    }
}

fun main() {
    val heapTree = BinaryHeap(10, "max")
    heapTree.insertNode(4)
    heapTree.insertNode(5)
    heapTree.insertNode(2)
    heapTree.insertNode(1)
    heapTree.levelOrderTraversal()
    println(heapTree.extractNode())
    println()
    heapTree.levelOrderTraversal()
}