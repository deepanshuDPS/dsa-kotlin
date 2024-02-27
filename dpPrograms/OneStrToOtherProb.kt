package dpPrograms

import java.lang.Integer.min

// convert one string to other in minimum operations using replace, insert, delete

fun oneStringToOtherTD(
    s1: String,
    s2: String,
    index1: Int,
    index2: Int,
    map: HashMap<String, Int>
): Int? {
    val key = "$index1$index2"
    if (index1 >= s1.length) return s2.length - index2   // inserting more
    else if (index2 >= s2.length) return s1.length - index1    // deleting more
    else if (s1[index1] == s2[index2])
        map[key] = oneStringToOtherTD(s1, s2, index1 + 1, index2 + 1, map) ?: 0
    else {
        if (map[key] == null) {
            val replace = oneStringToOtherTD(s1, s2, index1 + 1, index2 + 1, map) ?: 0
            val insert = oneStringToOtherTD(s1, s2, index1 + 1, index2, map) ?: 0
            val delete = oneStringToOtherTD(s1, s2, index1, index2 + 1, map) ?: 0
            map[key] = 1 + min(min(replace, insert), delete)    // minimum of all operations
        }
    }
    return map[key]
}

fun oneStringToOtherBU(
    s1: String,
    s2: String
): Int? {
    val map = hashMapOf<String, Int>()
    // initializing worst case for both strings
    for (i in 0..s1.length) {
        map["${i}0"] = i
    }
    for (i in 0..s2.length) {
        map["0$i"] = i
    }
    for (i in 1..s1.length) {
        for (j in 1..s2.length) {
            val key = "$i$j"
            if (s1[i-1] == s2[j-1]) {
                val tempKey = "${i - 1}${j - 1}"
                map[key] = map[tempKey] ?: 0
            } else {
                val keyD = "${i - 1}${j}"
                val keyI = "${i}${j - 1}"
                val keyR = "${i - 1}${j - 1}"
                map[key] = 1 + min(map[keyD] ?: 0, min(map[keyI] ?: 0, map[keyR] ?: 0))
            }
        }
    }
    return map["${s1.length}${s2.length}"]
}

fun main() {
    println(
        "Minimum operations to change s2 to s1 using TD: " +
                "${oneStringToOtherTD("dipanshu", "deepansh", 0, 0, hashMapOf())}"
    )

    println(
        "Minimum operations to change s2 to s1 using BU: " +
                "${oneStringToOtherBU("dipanshu", "deepansh")}"
    )
}