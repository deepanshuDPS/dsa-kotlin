package divNConqProblems


fun numberFactors(n: Int): Int {
    return when (n) {
        in arrayOf(0, 1, 2) -> 1 // {1} // {1,1} // {1,1,1} // 1, 2, 3
        3 -> 2 // {1,3}, {3,1} // 4
        else -> {
            numberFactors(n - 1) + numberFactors(n - 3) + numberFactors(n - 4)
        }
    }
}

// number factors from {1,3,4}
fun main() {
    println("Number factors of 5 from {1,3,4}= ${numberFactors(5)}")
}