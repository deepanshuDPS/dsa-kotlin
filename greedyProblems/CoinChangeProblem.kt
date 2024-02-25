package greedyProblems

// TC => O(NLogN) and SC => O(1)
fun main() {
    val coins = arrayOf(50, 1, 1000, 20, 10, 2, 100)
    coins.sortDescending()// O(NLogN)
    println("Types of coins required: ")
    var amount = 2012
    for (i in coins.indices) { // O(N)
        while (amount >= coins[i]) {
            amount -= coins[i]
            println(coins[i])
        }
        if (amount == 0)
            break
    }
}