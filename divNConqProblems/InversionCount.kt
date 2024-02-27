package divNConqProblems

import java.lang.Integer.max

fun inversionCount(list: Array<Int>, i: Int, j: Int): Int {
    return if (i >= list.size || j >= list.size) 0
    else if (list[i] > list[j]) {
        val temp = list[i]
        list[i] = list[j]
        list[j] = temp
        1 + inversionCount(list, i, j)
    } else {
        val option1 = inversionCount(list, i + 1, j + 2)
        val option2 = inversionCount(list, i + 2, j + 3)
        max(option1, option2)
    }
}

// TC => O(n) and SC => O(n)
// 0, 4, 9 => first, (first+last)/2, len - 1
fun merge(list: Array<Int>, first: Int, mid: Int, last: Int): Int {
    var inversion = 0
    // store 2 separate array in new list in ascending
    var i = first // 0->4
    var j = mid + 1 // 5 ->9
    var k = 0 // 0 -> 9
    val newArray = Array(last - first + 1) { 0 }
    while (i <= mid && j <= last) {
        if (list[i] <= list[j]) {
            newArray[k++] = list[i++]
        } else {
            newArray[k++] = list[j++]
            inversion++
        }
    }
    while (i <= mid) {
        newArray[k++] = list[i++]
    }

    while (j <= last) {
        newArray[k++] = list[j++]
        if (inversion!=0) inversion++
    }
    k = 0
    // then replace the current array elements with sorted list
    for (p in first..last) {
        list[p] = newArray[k++]
    }
    return inversion
}

// TC of merge sort => O(NlogN) and SC => O(N)
// conditional recursion divide and conquer
// TC => O(logN) and SC => O(n)
fun mergeSort(list: Array<Int>, first: Int, last: Int): Int {
    if (first < last) {
        val mid = (first + last) / 2
        // 0, 4
        val val1 = mergeSort(list, first, mid) // O(n/2)
        // 5, 9
        val val2 = mergeSort(list, mid + 1, last) // O(n/2)
        // 0, 4, 9
        return val1 + val2 + merge(list, first, mid, last)
    }
    return 0
}


fun main() {
    println(mergeSort(arrayOf(8,3,5,1,9,7,4,0), 0, 7))
}