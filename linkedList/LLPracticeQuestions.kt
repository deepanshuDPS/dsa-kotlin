package linkedList

import kotlin.math.abs

// TC O(n) and SC O(n)
fun removeDuplicates(linkedList: SinglyLinkedList) {
    var tempHead = linkedList.head
    val newLinkedList = SinglyLinkedList()
    val tempSet = HashSet<Int>()
    while (tempHead != null) {
        if (!tempSet.contains(tempHead.data)) {
            newLinkedList.append(tempHead.data)
            tempSet.add(tempHead.data)
        }
        tempHead = tempHead.next
    }
    println(newLinkedList)
}

// TC O(n) and SC O(n)
fun removeDuplicatesUsingSameList(linkedList: SinglyLinkedList) {
    var currentNode = linkedList.head
    var prevNode: Node? = null
    val tempSet = HashSet<Int>()
    while (currentNode != null) {
        if (!tempSet.contains(currentNode.data)) {
            tempSet.add(currentNode.data)
            prevNode = currentNode
            currentNode = currentNode.next
        } else {
            prevNode?.next = currentNode.next
            currentNode = currentNode.next
            linkedList.length--
        }
    }
    prevNode?.next = null
    linkedList.tail = prevNode
    println(linkedList)
}

// 3 then ith = 2
fun returnLastKthNode(linkedList: SinglyLinkedList, kth: Int): Node? {

    if (linkedList.length == 0) return null
    if (kth > linkedList.length - 1) return null

    var current = linkedList.head
    val index = linkedList.length - kth
    var i = 0
    while (current != null) {
        if (index == i) {
            return current
        }
        current = current.next
        i++
    }
    return null
}

// traverse temp till few points and current - temp till null will give kth term
fun returnLastKthNode2(linkedList: SinglyLinkedList, kth: Int): Node? {

    if (linkedList.length == 0) return null
    if (kth > linkedList.length - 1) return null

    var current = linkedList.head
    var currentTemp = linkedList.head
    for (i in 0 until kth) {
        if (currentTemp == null) {
            return null
        }
        currentTemp = currentTemp.next
    }

    while (currentTemp != null) {
        current = current?.next
        currentTemp = currentTemp.next
    }
    return current
}

// TC => O(n) and SC => O(1)
fun partitionAroundX(linkedList: SinglyLinkedList, x: Int) {
    var currentNode = linkedList.head
    linkedList.tail = linkedList.head
    // make the tail to head
    // store the nextNode
    // evaluate the current and push to head if small otherwise tail

    while (currentNode != null) {
        val nextNode = currentNode.next
        currentNode.next = null
        if (currentNode.data < x) {
            currentNode.next = linkedList.head
            linkedList.head = currentNode
        } else {
            linkedList.tail?.next = currentNode
            linkedList.tail = currentNode
        }
        currentNode = nextNode
    }
    if (linkedList.tail != null) linkedList.tail?.next = null
    println(linkedList)
}

// TC => O(n+m) => O(2n) and SC => O(n) where m == n and p == n+1
fun sumOfTwoLists(list1: SinglyLinkedList, list2: SinglyLinkedList) {
    val current1 = list1.head
    val current2 = list2.head
    val newList = SinglyLinkedList()
    var carry = 0
    while (current1 != null || current2 != null) {
        var result = carry
        if (current1 != null) {
            result += current1.data
        }
        if (current2 != null) {
            result += current2.data
        }
        carry = result / 10
        newList.append(result)
    }
    println(newList)
}

// TC => O(n+m) and SC => O(1)
// here intersection node not intersection value, it's check by address
fun intersectionNodeOfTwoList(list1: SinglyLinkedList, list2: SinglyLinkedList): Node? {

    if (list1.tail != list2.tail) {
        return null
    }

    val len1 = list1.length
    val len2 = list2.length

    var longerListNode = if (len1 > len2) list1.head else list2.head
    var shorterListNode = if (len1 > len2) list2.head else list1.head

    val diff = abs(len2 - len1)
    for (i in 0 until diff) {
        longerListNode = longerListNode?.next
    }

    while (longerListNode != shorterListNode) {
        longerListNode = longerListNode?.next
        shorterListNode = shorterListNode?.next
    }
    return longerListNode
}

fun main() {
    val linkedList = SinglyLinkedList()
    linkedList.append(3)
    linkedList.append(-1)
    linkedList.append(5)
    linkedList.append(3)
    linkedList.append(2)
    linkedList.append(1)
    linkedList.append(-1)
    println(linkedList)
    // we can do using nested loop also, it has time complexity of O(n^2)
    // and space complexity of O(1)
    // in inner loop we will skip the next nodes equal to current nodes.
    removeDuplicates(linkedList)
    removeDuplicatesUsingSameList(linkedList)
    println(returnLastKthNode(linkedList, 4)?.data)
    println(returnLastKthNode2(linkedList, 4)?.data)
    partitionAroundX(linkedList, 3)
}