package arrays

// TC => O(n)
// SC => O(1)
fun traverseArray(array: Array<Int>) {
    array.forEachIndexed { index, _ ->
        println(array[index])
    }
}

// TC => O(1)
// SC => O(1)
fun accessArray(array: Array<Int>, index: Int) {
    if (array.size > index)
        println(array[index])
    else
        println("Index out of bound")
}

fun main(args: Array<String>) {
    // time complexity O(1) coz empty array
    val emptyArray = arrayOf(0)
    // time complexity O(n) coz declaration of array is O(n)
    val oneDArray = arrayOf(1, 2, 3, 4, 5)
    println(oneDArray.joinToString())
    // inserting an element takes O(n) worst case
    // oneDArray.insert(4,6)
    traverseArray(oneDArray)
    accessArray(oneDArray, 3)
    accessArray(oneDArray, 9)
    // deleting an element takes O(n) worst case -> to maintain continuity
    // oneDArray.remove(3)
}

