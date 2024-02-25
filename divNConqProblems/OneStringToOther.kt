package divNConqProblems

import java.lang.Integer.min

// convert one string to other in minimum operations using replace, insert, delete

fun oneStringToOther(s1: String, s2: String, index1: Int, index2: Int): Int {

    return if (index1 >= s1.length) s2.length - index2   // inserting more
    else if (index2 >= s2.length) s1.length - index1    // deleting more
    else if (s1[index1] == s2[index2]) oneStringToOther(s1, s2, index1 + 1, index2 + 1)
    else {
        val replace = 1 + oneStringToOther(s1, s2, index1 + 1, index2 + 1)
        val insert = 1 + oneStringToOther(s1, s2, index1 + 1, index2)
        val delete = 1 + oneStringToOther(s1, s2, index1, index2 + 1)
        min(min(replace, insert), delete) // minimum of all operations
    }
}

fun main() {
    println(
        "Minimum operations to change s2 to s1: " +
                "${oneStringToOther("table", "tbla", 0, 0)}"
    )
}