package dpPrograms

import java.lang.Integer.max
import java.lang.Integer.min


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

fun longestPalindromicSubSeq(str: String): Int {
    val nth = str.length
    val dp = arrayOfNulls<Array<Int?>>(nth + 1)
    for (i in 0..nth) {
        dp[i] = arrayOfNulls(nth + 1)
    }
    for (i in 0..nth) {
        dp[0]?.set(i, 0) // making row 0 -> 0
    }
    for (j in 0..nth) {
        dp[j]?.set(0, 0) // making row 0 -> 0
    }
    for (i in 1..nth) {    // check from 1 - > 7
        for (j in 1..nth) {   // check from 1 - > 7
            // if the prev character matches
            if (str[i - 1] == str[nth - j]) {  // if the char matches -> increase by 2
                dp[i]?.set(j, 1 + (dp[i - 1]?.get(j - 1) ?: 0))
            } else {
                val skipFirst = dp[i - 1]?.get(j) ?: 0
                val skipLast = dp[i]?.get(j - 1) ?: 0
                // skip first element and check next
                // or skip last element and check next
                // max length string will be preferred
                dp[i]?.set(j, max(skipFirst, skipLast))
            }
        }
    }
    return dp[nth]?.get(nth) ?: -1
}

fun subsetSum(list: Array<Int>, sum: Int): Boolean {
    val nth = list.size
    val dp = arrayOfNulls<Array<Boolean?>>(nth + 1)
    for (i in 0..nth) {
        dp[i] = arrayOfNulls(sum + 1)
    }
    // if the some is zero the answer is true
    for (i in 0..nth) {
        dp[i]?.set(0, true)
    }

    // if the sum is not zero and the set is empty
    // then the answer is false
    for (i in 1..sum) {
        dp[0]?.set(i, false)
    }

    for (i in 1..nth) {
        for (j in 1..sum) {
            // j is same because we are at the calculated sum
            val skipCheck = (dp[i - 1]?.get(j) ?: false)
            dp[i]?.set(j, skipCheck)
            if (j >= list[i - 1]) {
                val isSubset =
                    (dp[i - 1]?.get(j - list[i - 1]) ?: false) || skipCheck
                dp[i]?.set(j, isSubset)
            }
        }
    }
    return dp[nth]?.get(sum) ?: false
}


fun maxLengthChainOfPairs(list: Array<Pair<Int, Int>>): Int {
    val nth = list.size
    val dp = Array(nth) { 1 }

    for (i in 1 until nth) {
        // current pair
        val cPair = list[i]
        for (j in 0 until i) {
            // from start till current pair,
            // compare and increase the min pair count by one
            val prevPair = list[j]
            if (prevPair.second < cPair.first && dp[i] < dp[j] + 1) {
                dp[i] = dp[j] + 1
            }
        }
    }

    return dp[nth - 1]
}

fun main() {
    println("LRS using BU: ${longestRepeatedSubs("RRUDRRAKKSHH")}")
    println("LCS using BU: ${longestCommonSubs("babii", "habbii")}")
    println("SCS using BU: ${shortestCommonSupSeq("ABC", "BCDE")}")
    println("LPS using BU: ${longestPalindromicSubSeq("elrmenmet")}")
    println("Subset sum using BU: ${subsetSum(arrayOf(3, 34, 4, 12, 5, 2), 9)}")
    println(
        "Max. Length chain of pairs BU: ${
            maxLengthChainOfPairs(
                arrayOf(
                    Pair(5, 24), Pair(39, 60), Pair(15, 28), Pair(27, 40), Pair(50, 90)
                )
            )
        }"
    )

}