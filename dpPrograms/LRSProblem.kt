package dpPrograms

import java.lang.Integer.max

var i = 0

fun lrsTD(s: String, i1: Int, i2: Int, map: HashMap<String, Int>): Int? {
    val key = "$i1$i2"
    if (i1 == s.length || i2 == s.length) return 0 // no case to compare
    else if (map[key] != null) {
        return map[key]
    }
    else if (s[i1] == s[i2] && i1 != i2) {
        map[key] = 1 + (lrsTD(s, i1 + 1, i2 + 1, map) ?: 0)
    } else {
        // max(insert,delete)
        map[key] = max((lrsTD(s, i1, i2 + 1, map) ?: 0), (lrsTD(s, i1 + 1, i2, map) ?: 0))
    }
    return map[key]
}

fun main() {
    val hashMap = hashMapOf<String,Int>()
    println("LRS using TD: ${lrsTD("ATTAKTKGGA", 0, 0, hashMap)}")
}