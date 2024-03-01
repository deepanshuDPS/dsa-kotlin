package divNConqProblems


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
        if (inversion != 0) inversion++
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
fun inversionCountMS(list: Array<Int>, first: Int, last: Int): Int {
    if (first < last) {
        val mid = (first + last) / 2
        // 0, 4
        val val1 = inversionCountMS(list, first, mid) // O(n/2)
        // 5, 9
        val val2 = inversionCountMS(list, mid + 1, last) // O(n/2)
        // 0, 4, 9
        return val1 + val2 + merge(list, first, mid, last)
    }
    return 0
}


fun countNumberOfZeros(list: Array<Int>): Int {

    var min = 0
    var max = list.size
    var mid: Int
    // till
    while (min <= max) {
        mid = (min + max) / 2
        // no index available to check
        if (mid + 1 == list.size)
            break

        // if no ones or only 1 at index 0
        if (mid == 0 && list[mid] == 0) return list.size
        else if (mid == 0) return list.size - 1

        if (list[mid] == 0 && list[mid - 1] == 1) {
            // all elements at right are 0s
            return list.size - mid
        } else if (list[mid] == 1 && list[mid + 1] == 0) {
            // all elements at right are 0s excluding 1
            return list.size - mid - 1
        } else if (list[mid] == 0 && list[mid - 1] == 0) {
            // shift left side to find more zeros
            max = mid - 1
        } else {
            // shift right side to find more zeros
            min = mid + 1
        }
    }
    return -1
}

fun findNumInRotatedList(list: Array<Int>, s: Int): Int {

    var min = 0
    var max = list.size
    var mid: Int
    var rotateIndexFound = false
    // till
    while (min <= max) {
        mid = (min + max) / 2

        // worst case scenario
        if (!rotateIndexFound && (mid - 1 < 0 || mid + 1 == list.size))
            break

        // element found
        if (list[mid] == s) return mid
        else if (list[mid] < list[mid - 1] || list[mid] > list[mid + 1]) {
            // rotated index
            // locate the list for searching element
            if (list[min] > s) {
                // it means element is on right sorted array
                min = mid + 1
            } else {
                // it means element is on left sorted array
                max = mid - 1
            }
            rotateIndexFound = true
        } else if (rotateIndexFound) {
            if (s < list[mid]) {
                max = mid - 1
            } else {
                min = mid + 1
            }
        } else {
            if (list[mid] > list[min]) {
                // rotated array is on right side
                min = mid + 1
            } else if (list[mid] < list[min]) {
                // rotated array is on right side
                max = mid - 1
            }
        }
    }
    return -1
}


fun occOfNumSortedList(list: Array<Int>, n: Int, min: Int, max: Int): Int {
    if (min < 0 || max == list.size) return 0
    if (min <= max) {
        val mid = (min + max) / 2
        var count = 0
        // if equal return +1 with next division of list
        if (list[mid] == n) count++
        // only divide list when elements is divided list is <>n
        if (list[min] <= n || list[max] >= n)
            return count + occOfNumSortedList(list, n, mid + 1, max) +
                    occOfNumSortedList(list, n, min, mid - 1)
    }
    return 0
}


fun main() {
    // inversion count using merge sort in nlog(n)
    println("Inversion Count: " + inversionCountMS(arrayOf(8, 3, 5, 1, 9, 7, 4, 0), 0, 7))
    // count the number of zeros (1,1,1,1,0,0,0,0) in log(n)
    println("Zeros Count: " + countNumberOfZeros(arrayOf(1, 0, 0, 0, 0, 0)))
    // count the number of zeros (8,13,18,24,30,3,4,6) in log(n)
    println(
        "Position of num in Rotated Array: " +
                findNumInRotatedList(arrayOf(8, 13, 18, 24, 30, 3, 4, 6), 30)
    )
    // find occurrence of number in sorted array => O(LogN)
    println(
        "Occurrence of number: " +
                occOfNumSortedList(arrayOf(3, 3, 8, 8, 8, 18, 18, 30), 3, 0, 7)
    )
}