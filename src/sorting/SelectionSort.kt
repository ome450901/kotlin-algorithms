package sorting

import kotlin.test.assertTrue

/**
 * @author WeiYi Yu
 * @date 2020-06-25
 */

/**
 *  Selection sort is an unstable algorithm
 *
 *  For example, given [4*, 2, 3, 4, 1], sort it in ascending order.
 *  When we pick the least value which is 1 and swap it to the first position,
 *  it became [1, 2, 3, 4, 4*], the first 4* has been moved to a position after the other 4,
 *  the relative order of the 4's have changed.
 *
 *  Time Complexity
 *
 *  Best-case: O(n^2) -> Go through the array to find the max/min element which takes O(n^2/2) ≈ O(n^2)
 *  Worst-case: O(n^2) -> Same as the best-case
 *
 */

fun main() {
    val values = arrayOf(2, 5, 3, 1, 7)
    values.selectionSort(ascendingComparator)
    assertTrue(values contentEquals arrayOf(1, 2, 3, 5, 7))

    values.selectionSort(descendingComparator)
    assertTrue(values contentEquals arrayOf(7, 5, 3, 2, 1))
}

/**
 * @param comparator decide the sort is ascending or descending.
 */
fun <T : Comparable<T>> Array<T>.selectionSort(comparator: Comparator<T>) {
    // There is only one item in the array, no need to sort
    if (size <= 1) {
        return
    }

    // For example, given [2, 5, 3, 1, 7], sort it in ascending order:
    // when i = 0 -> select the value 1 and swap its index with item at index 0: [1, 5, 3, 2, 7]
    // when i = 1 -> select the value 2 and swap its index with item at index 1: [1, 2, 3, 5, 7]
    for (i in 0 until size) {
        var selectedItemIndex = i

        // Find the max/min item index in the array, start from index i+1 since the first i elements are sorted.
        for (j in i + 1 until size) {
            val item = this[j]
            if (comparator.compare(item, this[selectedItemIndex]) > 0) {
                selectedItemIndex = j
            }
        }

        // Put the max/min item to the beginning of the array. i.e. index i
        swap(this, selectedItemIndex, i)
    }
}

private fun <T> swap(array: Array<T>, indexOne: Int, indexTwo: Int) {
    val temp = array[indexOne]
    array[indexOne] = array[indexTwo]
    array[indexTwo] = temp
}

private val ascendingComparator = Comparator<Int> { o1, o2 ->
    o2.compareTo(o1)
}

private val descendingComparator = Comparator<Int> { o1, o2 ->
    o1.compareTo(o2)
}