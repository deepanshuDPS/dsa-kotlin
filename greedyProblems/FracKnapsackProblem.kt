package greedyProblems

data class Item(val weight: Int, val value: Int, val ratio: Float = (value * 1f) / weight)

// TC => O(NLogN) and SC => O(1)
fun main() {

    val items = arrayOf(
        Item(20, 100),
        Item(30, 120),
        Item(10, 60)
    )

    items.sortByDescending { it.ratio } // sort by ratio descending O(NLogN)
    val capacity = 50
    var usedCapacity = 0
    var totalValue = 0f
    for (i in items) {
        if (usedCapacity + i.weight < capacity) {
            usedCapacity += i.weight
            totalValue += i.value
        } else {
            val unusedWeight = capacity - usedCapacity
            val value = i.ratio * unusedWeight // current value of unused weight
            usedCapacity += unusedWeight
            totalValue += value
        }
        if (usedCapacity == capacity)
            break
    }

    println("Total Value obtained from given 3 Items: $totalValue")
}