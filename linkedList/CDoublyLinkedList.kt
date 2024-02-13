package linkedList

class CDoublyLinkedList(data: Int? = null) {

    private var head: DoublyNode? = null
    private var tail: DoublyNode? = null
    var length: Int = 0

    // space and time complexity of init is O(1)
    init {
        if (data != null) {
            val newNode = DoublyNode(data)
            head = newNode
            tail = newNode
            newNode.next = newNode
            newNode.prev = newNode
            length = 1
        }
    }

    override fun toString(): String {
        var tempNode = head
        var allData = ""
        while (tempNode != null) {
            allData += "${tempNode.data} ${if (tempNode.next != head) "<-> " else ""}"
            tempNode = tempNode.next
            if (tempNode == head) {
                break
            }
        }
        return allData
    }

    // time complexity of traverse is O(n) and SC = O(1)
    fun traverse() {
        var current = head
        while (current != null) {
            print(" " + current.data)
            current = current.next
            if (current == head)
                break
        }
    }

    fun reverseTraverse() {
        var current = tail
        while (current != null) {
            print(" " + current.data)
            current = current.prev
            if (current == tail) break
        }
    }

    // time complexity of search is O(n) and SC = O(1)
    fun search(sData: Int) {
        var current = head
        var index = 0
        while (current != null) {
            if (current.data == sData) {
                println("found at index $index")
                return
            }
            index++
            current = current.next
            if (current == head) break
        }
        println("not found")
    }

    // time complexity of get is O(n) and SC = O(1) ->O(n/2)
    fun get(index: Int): DoublyNode? {
        if (index > length || index < 0) {
            return null
        } else if (tail == null) {
            return null
        }
        if (index < length / 2) {
            var current = head
            var i = 0
            while (current != null) {
                if (index == i) {
                    return current
                }
                i++
                current = current.next
            }
        } else {
            var current = tail
            var i = length - 1
            while (current != null) {
                if (index == i) {
                    return current
                }
                i--
                current = current.prev
            }
        }
        return null
    }

    // time complexity of set is O(n) and SC = O(1)
    fun set(index: Int, data: Int): Boolean {
        val node = get(index)
        if (node != null) {
            node.data = data
            return true
        }
        return false
    }


    // space and time complexity of append is O(1)
    fun append(data: Int) {
        val newNode = DoublyNode(data)
        if (length == 0) {
            head = newNode
            tail = newNode
            newNode.next = newNode
            newNode.prev = newNode
        } else {
            tail?.next = newNode
            newNode.prev = tail  // pointing to prev node
            tail = newNode
            head?.prev = tail
            tail?.next = head // pointing to head node
        }
        length++
    }

    // space and time complexity of prepend is O(1)
    fun prepend(data: Int) {
        val newNode = DoublyNode(data)
        if (length == 0) {
            head = newNode
            tail = newNode
            newNode.next = newNode
            newNode.prev = newNode
        } else {
            newNode.next = head
            head?.prev = newNode // pointing to prev node
            head = newNode
            tail?.next = head
            head?.prev = tail // pointing to tail node
        }
        length++
    }

    //  time complexity of insert is O(n) and SC = O(1)
    fun insert(index: Int, data: Int) {
        val newNode = DoublyNode(data)
        if (length == 0) {
            prepend(data)
        } else if (index == 0) {
            prepend(data)
        } else if (index == length) {
            append(data)
        } else {
            var tempNode = head
            // start with 1 because 0 is covered by head
            var i = 1
            // we can also use get(index - 1) to get prevNode
            while (tempNode != null) {
                if (i == index) {
                    newNode.next = tempNode.next
                    tempNode.next?.prev = newNode // pointing to prev node by temp
                    tempNode.next = newNode
                    newNode.prev = tempNode // pointing to prev node by new
                    length++
                    break
                }
                tempNode = tempNode.next
                if (tempNode == head) {
                    break
                }
                i++
            }
        }
    }

    //  time complexity of popFirst is O(1) and SC = O(1)
    fun popFirst(): DoublyNode? {
        if (head == null) {
            return null
        }
        val tempNode = head
        if (length == 1) {
            head?.next = null
            tail?.prev = null
            head = null
            tail = null
            length = 0
            return tempNode
        }
        head = head?.next
        head?.prev = tail // pointing to prev node by null
        tail?.next = head // tail pointed back to head
        tempNode?.next = null
        tempNode?.prev = null
        length--
        return tempNode
    }

    //  time complexity of pop is O(1) and SC = O(1)
    fun pop(): DoublyNode? {
        return when (length) {
            0 -> {
                null
            }
            1 -> {
                popFirst()
            }
            else -> {
                val poppedNode = tail
                tail = tail?.prev // no need to traverse here, just point to prev node
                tail?.next = head
                head?.prev = tail // head pointed next to tail
                poppedNode?.prev = null // pointing prev of popped node
                poppedNode?.next = null
                length--
                poppedNode
            }
        }
    }

    //  time complexity of remove is O(n) and SC = O(1)
    fun remove(index: Int): DoublyNode? {
        if (index > length || index < 0) {
            return null
        } else if (index == 0) {
            return popFirst()
        } else if (index == length - 1) {
            return pop()
        } else {
            // get node to remove and point next prev
            val nodeRemoved = get(index)
            if (nodeRemoved != null) {
                nodeRemoved.prev?.next = nodeRemoved.next
                nodeRemoved.next?.prev = nodeRemoved.prev
                nodeRemoved.next = null
                nodeRemoved.prev = null
                length--
                return nodeRemoved
            }
            return null
        }
    }

    // time complexity of deleteAll is O(n) and SC = O(1)
    fun deleteAll() {
        // garbage collector will delete all nodes if not referenced by any object
        if (length == 0) return
        tail?.next = null
        var current = head
        while (current != null) {
            current.prev = null
            current = current.next
        }
        head = null
        tail = null
        length = 0
    }

    // use prevNode and currentNode technique
    // time complexity of reverse is O(n) and SC = O(1)
//    fun reverse() {
//        if (length == 0 || length == 1) {
//            return
//        } else {
//            var currentNode = head
//            var prevNode: DoublyNode? = null
//            while (currentNode != null) {
//                val nextNode = currentNode.next
//                currentNode.next = prevNode
//                prevNode = currentNode
//                currentNode = nextNode
//            }
//            currentNode?.next = prevNode
//            val temp = head
//            head = tail
//            tail = temp
//        }
//    }

    /*
    def isPalindrome(self, head):

        rev = head
        prev_node = None

        while rev.next is not None:
            next_node = rev.next
            rev.next = prev_node
            prev_node = rev
            rev = next_node

        rev.next = prev_node
        head_2 = rev

        while head is not None and head_2 is not None:
            if head.val != head_2.val:
                return False
            head = head.next
            head_2 = head_2.next

        return True

    * */
}

fun main() {
    val doublyLinkedList = CDoublyLinkedList()
    doublyLinkedList.append(1)
    doublyLinkedList.append(3)
    doublyLinkedList.append(9)
    doublyLinkedList.prepend(-2)
    doublyLinkedList.insert(0, 4)
    println(doublyLinkedList)
    doublyLinkedList.traverse()
    doublyLinkedList.reverseTraverse()
    doublyLinkedList.search(-2)
    println(doublyLinkedList.get(1)?.data)
    doublyLinkedList.set(1, 10)
    println(doublyLinkedList.get(1)?.data)
    println(doublyLinkedList.popFirst()?.data)
    println(doublyLinkedList.pop()?.data)
    println(doublyLinkedList)
    doublyLinkedList.append(2)
    doublyLinkedList.append(3)
    println(doublyLinkedList)
    println(doublyLinkedList.remove(1)?.data)
    println(doublyLinkedList)
//    doublyLinkedList.reverse()
//    println(doublyLinkedList)
    doublyLinkedList.deleteAll()
    println(doublyLinkedList)
}