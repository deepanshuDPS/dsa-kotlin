package divNConqProblems

import kotlin.math.max

fun houseRobber(houses: Array<Int>, currentIndex: Int): Int {
    return if (currentIndex >= houses.size) 0 // currentIndex exceeds size then zero
    else if (currentIndex + 1 >= houses.size) return houses[currentIndex]
    else {
        val stealFirstHouse = houses[currentIndex] + houseRobber(houses, currentIndex + 2)
        val skipFirstHouse = houseRobber(houses, currentIndex + 1)
        max(stealFirstHouse, skipFirstHouse)
    }
}

fun main() {
    println("Max value to rob houses: ${houseRobber(arrayOf(30, 7, 1, 30, 30, 2, 1), 0)}")
}