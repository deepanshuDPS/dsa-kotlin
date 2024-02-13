package trees

class TNode(var data: TreeNode, var next: TNode? = null)

class QueueLList {

    private var head: TNode? = null
    private var tail: TNode? = null
    private var length = 0

    // TC -> O(1) | SC -> O(1)
    fun enqueue(data: TreeNode) {
        val node = TNode(data)
        if (head == null) {
            head = node
            tail = node
        } else {
            tail?.next = node
            tail = node
        }
        length++
    }

    // TC -> O(1) | SC -> O(1)
    fun dequeue(): TreeNode? {
        return if (length == 0) {
            null
        } else {
            val temp = head
            if (head == tail) {
                tail = null
                head = null
            } else {
                head = head?.next
            }
            length--
            temp?.next = null
            temp?.data
        }
    }

    // TC -> O(1) | SC -> O(1)
    fun peek(): TreeNode? {
        return head?.data
    }

    // TC -> O(1) | SC -> O(1)
    fun isEmpty() = length == 0

    // TC -> O(1) | SC -> O(1)
    fun size() = length

    // TC -> O(1) | SC -> O(1)
    fun delete() {
        head = null
        tail = null
    }

    override fun toString(): String {
        var tempNode = head
        var allData = ""
        while (tempNode != null) {
            allData += "${tempNode.data} ${if (tempNode.next != null) "->" else ""}"
            tempNode = tempNode.next
        }
        return allData
    }
}
