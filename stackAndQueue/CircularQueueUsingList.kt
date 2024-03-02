package queue


class CircularQueueList(private val maxSize: Int) {

    private val list = mutableListOf<Int?>()
    private var start = -1
    private var top = -1

    // TC and SC -> O(n)
    init {
        for (i in 0 until maxSize) {
            list.add(null)
        }
    }

    // TC and SC -> O(1)
    fun isFull(): Boolean {
        return if (top + 1 == start) true
        else start == 0 && top + 1 == maxSize
    }

    // TC -> O(1)  ||  SC -> O(1)
    fun enqueue(value: Int) {
        if (isFull()) {
            throw Exception("Queue is full")
        } else if (top + 1 == maxSize) {
            top = 0
        } else {
            top++
            if (start == -1) {
                start = 0
            }
        }
        list[top] = value
    }

    // TC -> O(1) and SC -> O(1)
    fun dequeue(): Int {
        if (isEmpty()) {
            throw Exception("Queue is empty")
        } else {
            val st = start
            val firstElement = list[start]
            if (start == top) {
                start = -1
                top = -1
            } else if (start + 1 == maxSize) {
                start = 0
            } else {
                start++
            }
            list[st] = null
            return firstElement!!
        }
    }

    // TC -> O(1) and SC -> O(1)
    fun peek(): Int? {
        if (isEmpty()) {
            throw Exception("Queue is empty")
        }
        return list[start]
    }

    // TC -> O(1) and SC -> O(1)
    fun isEmpty(): Boolean {
        return top == -1
    }

    // TC -> O(1) and SC -> O(1)
    fun size(): Int {
        return list.size
    }

    // TC -> O(n) and SC -> O(1)
    fun delete() {
        for (i in 0 until maxSize) {
            list.set(i, null)
        }
    }
}