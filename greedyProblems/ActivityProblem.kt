package greedyProblems

data class Activity(val name: String, val start: Int, val finish: Int)

// TC => O(NLogN) and SC => O(1)
fun main() {

    val activities = arrayOf(
        Activity("A1", 0, 6),
        Activity("A2", 3, 4),
        Activity("A3", 1, 2),
        Activity("A4", 5, 8),
        Activity("A5", 5, 7),
        Activity("A6", 8, 9)
    )
    println("Activities to perform: ")
    // sort by finish timings
    activities.sortBy { it.finish } // O(NLogN)
    println(activities[0].name)
    var j = 0 // last activity index
    for (i in 1 until activities.size) {
        if (activities[i].start > activities[j].finish) {
            println(activities[i].name)
            j = i
        }
    }
}