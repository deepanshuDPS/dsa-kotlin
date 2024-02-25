package divNConqProblems

import kotlin.math.max

// longest common subsequence in 2 strings
fun lcs(s1: String, s2: String, index1: Int, index2: Int): Int {

    return if (index1 == s1.length || index2 == s2.length) 0
    else if (s1[index1] == s2[index2]) 1 + lcs(s1, s2, index1 + 1, index2 + 1)
    else {
        val insert = lcs(s1, s2, index1 + 1, index2)
        val delete = lcs(s1, s2, index1, index2 + 1)
        max(insert, delete) // maximum of all sequence
    }
}

fun main() {
    println(
        "Longest common subsequence in s1 and s2: " +
                "${lcs("elephant", "pant", 0, 0)}"
    )
}