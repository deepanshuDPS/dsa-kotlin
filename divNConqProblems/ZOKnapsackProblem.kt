package divNConqProblems

import java.lang.Integer.max

// get maximum profits from the given fruit values for the given capacity

data class Fruit(val profit: Int, val weight: Int)

// capacity in weights and profit in value
fun zoKnapsackProblem(items: Array<Fruit>, cIndex: Int, capacity: Int): Int {
    return if (cIndex >= items.size || capacity <= 0) 0
    // if remaining capacity is less then current item increase to next one
    else {
        val includeFirst =
            if (items[cIndex].weight <= capacity) items[cIndex].profit + zoKnapsackProblem(
                items,
                cIndex + 1,
                capacity - items[cIndex].weight
            ) else 0
        val skipFirst = zoKnapsackProblem(items, cIndex + 1, capacity)
        max(includeFirst, skipFirst)
    }
}

fun main() {
    println(
        "Maximum profit from the following given items of capacity 7: " +
                "${
                    zoKnapsackProblem(
                        arrayOf(
                            Fruit(31, 3),
                            Fruit(26, 1),
                            Fruit(17, 2),
                            Fruit(72, 5)
                        ), 0, 7
                    )
                }"
    )
}