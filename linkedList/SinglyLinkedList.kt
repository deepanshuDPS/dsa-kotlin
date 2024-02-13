package linkedList

class SinglyLinkedList(data: Int? = null) {

    var head: Node? = null
    var tail: Node? = null
    var length: Int = 0
    // space and time complexity of init is O(1)
    init {
        if (data != null) {
            val newNode = Node(data)
            head = newNode
            tail = newNode
            length = 1
        }
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

    // time complexity of traverse is O(n) and SC = O(1)
    fun traverse() {
        var current = head
        while (current != null) {
            print(" " + current.data)
            current = current.next
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
        }
        println("not found")
    }

    // time complexity of get is O(n) and SC = O(1)
    fun get(index: Int): Node? {
        if (index > length || index < 0) {
            return null
        } else if (tail == null) {
            return null
        }
        var current = head
        var i = 0
        while (current != null) {
            if (index == i) {
                return current
            }
            i++
            current = current.next
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
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        length++
    }

    // space and time complexity of prepend is O(1)
    fun prepend(data: Int) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head = newNode
        }
        length++
    }

    //  time complexity of insert is O(n) and SC = O(1)
    fun insert(index: Int, data: Int) {
        val newNode = Node(data)
        if (head == null) {
            prepend(data)
        } else if (index == 0) {
            prepend(data)
        } else if (index == length) {
            append(data)
        } else {
            var tempNode = head
            // start with 1 because 0 is covered by head
            var i = 1
            while (tempNode != null) {
                if (i == index) {
                    newNode.next = tempNode.next
                    tempNode.next = newNode
                    length++
                    break
                }
                tempNode = tempNode.next
                i++
            }
        }
    }

    //  time complexity of popFirst is O(1) and SC = O(1)
    fun popFirst(): Node? {

        if (head == null) {
            return null
        }
        val tempNode = head
        if (length == 1) {
            head = null
            tail = null
            length = 0
            return tempNode
        }
        head = head?.next
        tempNode?.next = null
        length--
        return tempNode
    }

    //  time complexity of pop is O(n) and SC = O(1)
    fun pop(): Node? {
        if (head == null) {
            return null
        } else if (length == 1) {
            return popFirst()
        } else {
            val tempNode = tail
            var current = head
            while (current?.next != tail) {
                current = current?.next
            }
            current?.next = null
            tail = current
            tempNode?.next = null
            length--
            return tempNode
        }
    }

    //  time complexity of remove is O(n) and SC = O(1)
    fun remove(index: Int): Node? {
        if (index > length || index < 0) {
            return null
        } else if (index == 0) {
            return popFirst()
        } else if (index == length - 1) {
            return pop()
        } else {
            var tempNode = head
            // start with 1 because 0 is covered by head
            var i = 1
            while (tempNode != null) {
                if (i == index) {
                    val nodeRemoved = tempNode.next
                    tempNode.next = tempNode.next?.next
                    length--
                    nodeRemoved?.next = null
                    return nodeRemoved
                }
                tempNode = tempNode.next
                i++
            }
            return null
        }
    }

    // time complexity of deleteAll is O(1) and SC = O(1)
    fun deleteAll() {
        // garbage collector will delete all nodes if not referenced by any object
        head = null
        tail = null
        length = 0
    }

    // use prevNode and currentNode technique
    // time complexity of reverse is O(n) and SC = O(1)
    fun reverse() {
        if (length == 0 || length == 1) {
            return
        } else {
            var currentNode = head
            var prevNode: Node? = null
            while (currentNode != null) {
                val nextNode = currentNode.next
                currentNode.next = prevNode
                prevNode = currentNode
                currentNode = nextNode
            }
            currentNode?.next = prevNode
            val temp = head
            head = tail
            tail = temp
        }
    }

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
    val singlyLinkedList = SinglyLinkedList()
    singlyLinkedList.append(1)
    singlyLinkedList.insert(0, 4)
    singlyLinkedList.append(3)
    singlyLinkedList.prepend(-2)
    println(singlyLinkedList)
    singlyLinkedList.traverse()
    singlyLinkedList.search(10)
    println(singlyLinkedList.get(1)?.data)
    singlyLinkedList.set(1, 10)
    println(singlyLinkedList.get(1)?.data)
    println(singlyLinkedList.popFirst()?.data)
    println(singlyLinkedList.pop()?.data)
    println(singlyLinkedList)
    singlyLinkedList.append(2)
    singlyLinkedList.append(3)
    println(singlyLinkedList)
    println(singlyLinkedList.remove(2)?.data)
    println(singlyLinkedList)
    singlyLinkedList.reverse()
    println(singlyLinkedList)
    singlyLinkedList.deleteAll()
    println(singlyLinkedList)
}