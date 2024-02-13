package queue

import linkedList.Node


class QueueLList {

    private var head: Node? = null
    private var tail: Node? = null
    private var length = 0

    // TC -> O(1) | SC -> O(1)
    fun enqueue(data: Int) {
        val node = Node(data)
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
    fun dequeue(): Int {
        return if (length == 0) {
            -1
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
            temp?.data ?: -1
        }
    }

    // TC -> O(1) | SC -> O(1)
    fun peek(): Int {
        return head?.data ?: -1
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

fun main() {
    val queue = QueueLList()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.enqueue(4)
    println(queue.peek())
    println(queue.dequeue())
    println(queue)
    println(queue.size())
    println(queue.isEmpty())
    queue.delete()
}