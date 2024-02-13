package arrays

// TC => O(n)
// SC => O(1)
fun linearSearch(array: Array<Int>, sElem: Int) {
    for (i in array.indices) {
        if (array[i] == sElem) {
            println("Element found at index $i")
            return
        }
    }
    println("Element not found")
}

fun main(args: Array<String>) {
    // time complexity O(n) coz declaration of array is O(n)
    val oneDArray = arrayOf(2, 3, 1, 5, 7, 3, 8, 3, 2)
    linearSearch(oneDArray, 10)
}

