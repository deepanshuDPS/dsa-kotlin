package trees

data class TreeNode(
    var data: String? = null,
    var value: Int? = null,
    var leftChild: TreeNode? = null,
    var rightChild: TreeNode? = null,
    var height: Int = 1 // by default 1
)