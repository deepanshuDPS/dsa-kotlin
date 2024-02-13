package arrays

// TC => O(n*m)
// SC => O(1)
fun traverse2DArray(array: Array<Array<Int>>) {
    for (i in array.indices) {
        for (j in array[i].indices) {
            print(array[i][j])
        }
        println()
    }
}

// TC => O(1)
// SC => O(1)
fun access2DArray(array: Array<Array<Int>>, row: Int, col: Int) {
    if (array.size > row && array[row].size > col)
        println(array[row][col])
    else
        println("Index out of bound")
}

fun main(args: Array<String>) {
    // time complexity O(n) coz declaration of array is O(n*m)
    val twoDArray = arrayOf(arrayOf(1, 2, 3, 4), arrayOf(1, 2, 3, 4), arrayOf(1, 2, 3, 4))
    println(twoDArray.joinToString())
    // inserting a column takes O(n*m) worst case
    // inserting a row takes O(m*n) worst case
    // we cannot add single element in 2D array
    // oneDArray.insert(4,6)
    traverse2DArray(twoDArray)
    access2DArray(twoDArray, 2, 2)
    // deleting an element takes O(m*n) worst case
    // we cannot add single element in 2D array
    // deleting an element takes O(n) worst case -> to maintain continuity
    // oneDArray.remove(3)
}

