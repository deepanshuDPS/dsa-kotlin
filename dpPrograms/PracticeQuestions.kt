package dpPrograms

import java.lang.Integer.min
import java.lang.Math.abs


fun longestCommonSubs(str1: String, str2: String): Int {
    val nth = str1.length
    val mth = str2.length
    val dp = arrayOfNulls<Array<Int?>>(nth + 1)
    for (i in 0..nth) {
        dp[i] = arrayOfNulls(mth + 1)
    }
    for (i in 0..nth) {
        dp[i]?.set(0, 0) // making row 0 -> 0
    }
    for (j in 0..mth) {
        dp[0]?.set(j, 0) // making col 0 -> 0
    }

    for (i in 1..nth) {
        for (j in 1..mth) {
            // if the prev character matches
            if (str1[i - 1] == str2[j - 1]) {
                dp[i]?.set(j, 1 + (dp[i - 1]?.get(j - 1) ?: 0))
            } else {
                val insert = dp[i - 1]?.get(j) ?: 0
                val delete = dp[i]?.get(j - 1) ?: 0
                dp[i]?.set(j, kotlin.math.max(insert, delete))
            }
        }
    }
    return dp[nth]?.get(mth) ?: -1
}

fun longestRepeatedSubs(str: String): Int {
    val nth = str.length
    val dp = arrayOfNulls<Array<Int?>>(nth + 1)
    for (i in 0..nth) {
        dp[i] = arrayOfNulls(nth + 1)
    }
    for (i in 0..nth) {
        dp[i]?.set(0, 0) // making row 0 -> 0
        dp[0]?.set(i, 0) // making col 0 -> 0
    }

    for (i in 1..nth) {
        for (j in 1..nth) {
            // if indexes are not same and the prev character matches
            if (i != j && str[i - 1] == str[j - 1]) {
                dp[i]?.set(j, 1 + (dp[i - 1]?.get(j - 1) ?: 0))
            } else {
                val insert = dp[i - 1]?.get(j) ?: 0
                val delete = dp[i]?.get(j - 1) ?: 0
                dp[i]?.set(j, kotlin.math.max(insert, delete))
            }
        }
    }
    return dp[nth]?.get(nth) ?: -1
}

fun shortestCommonSupSeq(str1: String, str2: String): Int {
    val nth = str1.length
    val mth = str2.length
    val dp = arrayOfNulls<Array<Int?>>(nth + 1)
    for (i in 0..nth) {
        dp[i] = arrayOfNulls(mth + 1)
    }
//    for (i in 0..nth) {
//        dp[i]?.set(0, 0) // making row 0 -> 0
//    }
//    for (j in 0..mth) {
//        dp[0]?.set(j, 0) // making col 0 -> 0
//    }

    for (i in 0..nth) {
        for (j in 0..mth) {
            // if the prev character matches
            if (i == 0)
                dp[i]?.set(j, j)    // returning the remaining length of the string
            else if (j == 0)
                dp[i]?.set(j, i)    // returning the remaining length of the string
            else if (str1[i - 1] == str2[j - 1]) {
                // addition of one character
                dp[i]?.set(j, 1 + (dp[i - 1]?.get(j - 1) ?: 0))
            } else {
                val insert = dp[i - 1]?.get(j) ?: 0
                val delete = dp[i]?.get(j - 1) ?: 0
                // taking minimum of insertion and deletion because needs
                // to minimize insertion of second string plus addition of one char
                dp[i]?.set(j, 1 + min(insert, delete))
            }
        }
    }
    return (dp[nth]?.get(mth) ?: -1) //- (mth-nth)
}


fun main() {
    println("LRS using BU: ${longestRepeatedSubs("RRUDRRAKKSHH")}")
    println("LCS using BU: ${longestCommonSubs("babii", "habbii")}")
    println("SCS using BU: ${shortestCommonSupSeq("ABC", "BCDE")}")

}