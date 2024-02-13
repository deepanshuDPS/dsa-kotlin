package queue


class QueueList {

    private val list = mutableListOf<Int>()

    // TC -> O(n)  Doubt d SC -> O(1)
    fun enqueue(value: Int) {
        list.add(value)
    }

    // TC -> O(n) and SC -> O(1)
    fun dequeue(): Int {
        return list.removeAt(0)
    }

    // TC -> O(1) and SC -> O(1)
    fun peek(): Int {
        return list.first()
    }

    // TC -> O(1) and SC -> O(1)
    fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    // TC -> O(1) and SC -> O(1)
    fun size(): Int {
        return list.size
    }

    // TC -> O(n) and SC -> O(1)
    fun delete() {
        list.clear()
    }
}