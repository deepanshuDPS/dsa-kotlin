package stackAndQueue

// We can make it using different approach also
// where at the time of pop we replace elements till nth element to temp
// and after popping push back to main queue
class StackUsingTwoQueue {

    private val mainQueue = QueueLList()
    private val tempQueue = QueueLList()

    // O(N)
    fun push(data: Int) {
        for (i in 0 until mainQueue.size()) {
            tempQueue.enqueue(mainQueue.dequeue())
        }
        mainQueue.enqueue(data)
        for (i in 0 until tempQueue.size()) {
            mainQueue.enqueue(tempQueue.dequeue())
        }
    }

    // O(1)
    fun pop(): Int {
        return mainQueue.dequeue()
    }

    override fun toString(): String {
        return mainQueue.toString()
    }

}

fun main(){

    val stackUsingTwoQueue = StackUsingTwoQueue()
    stackUsingTwoQueue.push(1)
    stackUsingTwoQueue.push(2)
    stackUsingTwoQueue.push(3)
    stackUsingTwoQueue.push(4)
    println(stackUsingTwoQueue)
    println(stackUsingTwoQueue.pop())
    println(stackUsingTwoQueue.pop())
    stackUsingTwoQueue.push(5)
    println(stackUsingTwoQueue.pop())
    println(stackUsingTwoQueue.pop())
    stackUsingTwoQueue.push(6)
    println(stackUsingTwoQueue)
}