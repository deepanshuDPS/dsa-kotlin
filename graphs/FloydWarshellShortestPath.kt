package graphs

import kotlin.math.min


fun printMatrix(distances: Array<Array<Int>>){
    print("[ ")
    for(i in distances){
        println(i.joinToString(","))
    }
    print(" ]")
}

// TC=> O(V^3) and SC=> O(V^2)
fun calculateShortestPath(distance: Array<Array<Int>>) {

    for (viaVertex in distance.indices) {
        for (s in distance.indices) {
            for (d in distance.indices) {
                distance[s][d] =
                    min(distance[s][d], distance[s][viaVertex] + distance[viaVertex][d])
            }
        }
        printMatrix(distance)
    }

    printMatrix(distance)
}

fun main() {
    val inf = 9999
    // A B C D - vertices
    calculateShortestPath(
        arrayOf(
            arrayOf(0, 8, inf, 1),
            arrayOf(inf, 0, 1, inf),
            arrayOf(4, inf, 0, inf),
            arrayOf(inf, 2, 9, 0)
        )
    )
}