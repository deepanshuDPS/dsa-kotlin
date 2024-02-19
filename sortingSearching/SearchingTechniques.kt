package sortingSearching

// TC => O(n) and SC=> O(1)
fun linearSearch(list: Array<Int>, sElement: Int): String {

    list.forEachIndexed { index, i ->
        if (i == sElement) return "Element found at $index"
    }
    return "Not Found"
}

// TC => O(logN) and SC=> O(1)
fun binarySearch(list: Array<Int>, sElement: Int): String {

    var min = 0
    var max = list.size
    var mid: Int
    while (min < max) {
        mid = (min + max) / 2
        if (sElement < list[mid]) {
            max = mid - 1
        } else if (sElement > list[mid]) {
            min = mid + 1
        } else {
            return "Element found at $mid"
        }
    }

    return "Not Found"
}

fun main() {

    println(linearSearch(arrayOf(3, 2, 1, 5, 6, 8), 5))
    println(binarySearch(arrayOf(2, 3, 5, 6, 7, 8, 9), 8))
}