package sortingSearching

import java.util.Collections.max
import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.sqrt

// TC => O(n^2) and SC => O(1)
fun bubbleSort(list: Array<Int>) {
    list.shuffle()
    println("Before Bubble Sort: " + list.joinToString(","))
    // checking elements one by one from 0->n-1
    for (i in 0 until list.size - 1) {
        // if element is greater than next one than swap at that time
        for (j in 0 until list.size - i - 1) {
            if (list[j] > list[j + 1]) {
                val temp = list[j]
                list[j] = list[j + 1]
                list[j + 1] = temp
            }
        }
    }
    println("After Bubble Sort: " + list.joinToString(","))
}

// TC => O(n^2) and SC => O(1)
fun selectionSort(list: Array<Int>) {
    list.shuffle()
    println("Before Selection Sort: " + list.joinToString(","))
    // checking elements one by one from 0->n-1
    for (i in 0 until list.size - 1) {
        var minIndex = i
        for (j in i + 1 until list.size) {
            if (list[minIndex] > list[j]) {
                minIndex = j
            }
        }
        // swapping element only when found the lesser value than current element
        if (minIndex != i) {
            val temp = list[minIndex]
            list[minIndex] = list[i]
            list[i] = temp
        }
    }
    println("After Selection Sort: " + list.joinToString(","))
}

// TC => O(n^2) and SC => O(1)
fun insertionSort(list: Array<Int>) {
    list.shuffle()
    println("Before Insertion Sort: " + list.joinToString(","))
    // checking elements one by one from 1->n
    for (i in 1 until list.size) {
        val element = list[i]
        var j = i - 1
        // shifting back current element if it's lesser than
        // the front sorted list till j>0
        while (j >= 0 && element < list[j]) {
            list[j + 1] = list[j]
            j--
        }
        list[j + 1] = element
    }
    println("After Insertion Sort: " + list.joinToString(","))
}

// TC => O(n^2) and SC => O(n)
fun bucketSort(list: Array<Int>) {
    list.shuffle()
    println("Before Bucket Sort: " + list.joinToString(","))
    val noOfBuckets = round(sqrt(list.size.toDouble()))
    val maxValue = max(list.toList())
    val listOfBuckets = arrayListOf<Array<Int>>()
    for (i in 0 until noOfBuckets.toInt()) {
        listOfBuckets.add(arrayOf())
    }

    for (j in list) {
        val indexOfB = ceil(j + noOfBuckets / maxValue).toInt() - 1
        listOfBuckets[indexOfB][listOfBuckets[indexOfB].size] = j
    }

    //.. now sort all buckets using any sorting
    // and merge them fill give final sorted array

    println("After Bucket Sort: " + list.joinToString(","))
}

// TC => O(n) and SC => O(n)
// 0, 4, 9 => first, (first+last)/2, len - 1
fun merge(list: Array<Int>, first: Int, mid: Int, last: Int) {

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
        }
    }
    while (i <= mid) {
        newArray[k++] = list[i++]
    }

    while (j <= last) {
        newArray[k++] = list[j++]
    }
    k = 0
    // then replace the current array elements with sorted list
    for (p in first..last) {
        list[p] = newArray[k++]
    }
}

// TC of merge sort => O(NlogN) and SC => O(N)
// conditional recursion divide and conquer
// TC => O(logN) and SC => O(n)
fun mergeSort(list: Array<Int>, first: Int, last: Int) {
    if (first < last) {
        val mid = (first + last) / 2
        // 0, 4
        mergeSort(list, first, mid) // O(n/2)
        // 5, 9
        mergeSort(list, mid + 1, last) // O(n/2)
        // 0, 4, 9
        merge(list, first, mid, last)
    }
}

fun swap(list: Array<Int>, index1: Int, index2: Int) {
    val temp = list[index1]
    list[index1] = list[index2]
    list[index2] = temp
}

// TC => O(n) and SC => O(1)
fun pivot(list: Array<Int>, pivotIndex: Int, last: Int): Int {
    var swapIndex = pivotIndex
    for (i in pivotIndex + 1..last) {
        if (list[i] < list[pivotIndex]) {
            // swap index only shifts when the pivot element is greater
            swapIndex++
            // and then swap with last element checks which is greater or equal
            swap(list, swapIndex, i)
        }
    }
    // after dividing left or right  with higher or lesser value
    // swap pivot to middle
    swap(list, swapIndex, pivotIndex)
    return swapIndex // new pivot
}

// TC of quick sort => O(NlogN) and SC => O(n)
// conditional recursion divide and conquer
// TC => O(logN) and SC => O(n)
fun quickSort(list: Array<Int>, left: Int, right: Int) {
    if (left < right) {
        val newPivot = pivot(list, left, right)
        quickSort(list, left, newPivot - 1)
        quickSort(list, newPivot + 1, right)
    }
}

fun heapSort() {
    // insert the elements in min heap
    // extract using heapify
    // reverse the array will sort the list
    //------------OR------------
    // insert the elements in max heap
    // extract using heapify
}


fun main() {

    val list = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    bubbleSort(list)
    selectionSort(list)
    insertionSort(list)
//    bubbleSort(list) // not implemented for now, need to learn
    list.shuffle()
    println("Before Merge Sort: " + list.joinToString(","))
    mergeSort(list, 0, list.size - 1)
    println("After Merge Sort: " + list.joinToString(","))

    list.shuffle()
    println("Before Quick Sort: " + list.joinToString(","))
    quickSort(list, 0, list.size - 1)
    println("After Quick Sort: " + list.joinToString(","))
}