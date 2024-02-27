package dpPrograms

import kotlin.math.max

fun houseRobberTD(houses: Array<Int>, currentIndex: Int, map: HashMap<Int, Int>): Int? {
    return if (currentIndex >= houses.size) 0 // currentIndex exceeds size then zero
    else if (currentIndex + 1 >= houses.size) return houses[currentIndex]
    else {
        if (map[currentIndex] == null) {
            val stealFirstHouse =
                houses[currentIndex] + (houseRobberTD(houses, currentIndex + 2, map) ?: 0)
            val skipFirstHouse = (houseRobberTD(houses, currentIndex + 1, map) ?: 0)
            map[currentIndex] = max(stealFirstHouse, skipFirstHouse)
        }
        return map[currentIndex]
    }
}

fun houseRobberBU(houses: Array<Int>): Int? {
    val list = arrayOfNulls<Int>(houses.size + 2)
    for (i in houses.lastIndex downTo 0) {
        val stealFirstHouse = houses[i] + (list[i + 2] ?: 0)
        val skipFirstHouse = list[i + 1] ?: 0
        list[i] = max(stealFirstHouse, skipFirstHouse)
    }
    return list[0]
}

fun main() {
    println(
        "Max value to rob houses using TD: ${
            houseRobberTD(
                arrayOf(30, 7, 1, 9, 8, 2, 1),
                0,
                hashMapOf()
            )
        }"
    )
    println(
        "Max value to rob houses using BU: ${
            houseRobberBU(
                arrayOf(30, 7, 1, 9, 8, 2, 1)
            )
        }"
    )

}