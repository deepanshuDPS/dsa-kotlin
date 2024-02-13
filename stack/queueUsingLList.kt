package stack

import linkedList.Node


class StackLList {

    private var head: Node? = null
    private var length = 0

    // TC -> O(1) | SC -> O(1)
    fun push(data: Int) {
        val node = Node(data)
        if (head == null) {
            head = node
        } else {
            node.next = head
            head = node
        }
        length++
    }

    // TC -> O(1) | SC -> O(1)
    fun pop(): Int {
        return if (length == 0) {
            -1
        } else {
            val temp = head
            head = head?.next
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
    val stack = StackLList()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    println(stack.peek())
    println(stack.pop())
    println(stack)
    println(stack.size())
    println(stack.isEmpty())
    stack.delete()
}