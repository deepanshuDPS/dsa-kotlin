package dpPrograms

import divNConqProblems.numberFactors

fun numberFactorTD(n: Int, map: HashMap<Int, Int>): Int? {
    return when (n) {
        in arrayOf(0, 1, 2) -> 1
        3 -> 2
        else -> {
            if (map[n] == null)
                map[n] = numberFactors(n - 1) + numberFactors(n - 3) + numberFactors(n - 4)
            map[n]
        }
    }
}

fun numberFactorBU(n: Int): Int {
    val list = arrayListOf(1, 1, 1, 2)
    for (i in 4..n) {
        list.add(list[i - 1] + list[i - 3] + list[i - 4])
    }
    return list[n]
}

fun main() {
    println("Top Down: ${numberFactorTD(5, hashMapOf())}")
    println("Bottom Up: ${numberFactorBU(5)}")

}