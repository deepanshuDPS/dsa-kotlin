package divNConqProblems

import kotlin.math.max

// longest palindromic subsequence in a String
fun lps(s: String, first: Int, last: Int): Int {

    return if (first > last) 0 // crosses half way
    else if (first == last) 1 // reaches half way means middle element is same
    else if (s[first] == s[last]) 2 + lps(s, first + 1, last - 1) // two chars matches
    else {
        val firstSkip = lps(s, first + 1, last)
        val lastSkip = lps(s, first, last - 1)
        max(firstSkip, lastSkip) // maximum of all sequence
    }
}

fun main() {
    println(
        "Longest palindromic subsequence in string: " +
                "${lps("elrmenmet", 0, 7)}"
    )
}