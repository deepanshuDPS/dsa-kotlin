package stackAndQueue


class StackList {

    private val list = mutableListOf<Int>()

    // TC -> O(1) OR O(n^2)  Doubt d SC -> O(1)
    fun push(value: Int) {
        list.add(value)
    }

    // TC -> O(1) and SC -> O(1)
    fun pop(): Int {
        return list.removeAt(list.lastIndex)
    }

    // TC -> O(1) and SC -> O(1)
    fun peek(): Int {
        return list.last()
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