package trees

class TrieNode(
    val children: HashMap<Char, TrieNode?> = HashMap(),
    var isEndOfString: Boolean = false
)

class Trie {

    val root = TrieNode()


    // Case 1: A trie is blank
    // Case 2: Prefix already there
    // Case 3: New word is prefix
    // Case 4: Word doesn't exist
    // TC => O(m)
    fun insert(word: String) {
        var current = root
        for (newCh in word) {
            var node = current.children[newCh]
            if (node == null) {
                // if the char not exit or trie is blank
                node = TrieNode()
                current.children[newCh] = node
            }
            current = node // adding the next node or assigning the next node if exist
        }
        current.isEndOfString =
            true // after reaching at end make the last node to endOfString true of the word
    }

    // TC => O(m)
    fun search(word: String): Boolean {
        var current = root
        for (sCh in word) {
            val node = current.children[sCh] ?: return false
            current = node // adding the next node or assigning the next node if exist
        }
        return current.isEndOfString
    }

    // Case 1: A word is not there -> No case mentioned***
    // Case 2: Prefix of word is there so just remove postfix of the word
    // Case 3: Postfix of word is there so just make end of string false
    // Case 4: Remove the whole word
    // TC => O(m)
    fun deleteString(root: TrieNode?, word: String, index: Int): Boolean {
        val ch = word[index]
        val currentNode: TrieNode? = root?.children?.get(ch)

        // means it has min 2 chars so not applicable for deletion
        if ((currentNode?.children?.size ?: 0) > 1) {
            deleteString(currentNode, word, index + 1)
            return false
        }

        // reaches at end of word
        if (index == word.length - 1) {
            return if ((currentNode?.children?.size ?: 0) >= 1) {
                // postfix of the word is there
                currentNode?.isEndOfString = false
                false
            } else {
                // removing postfix of the word
                currentNode?.children?.remove(ch)
                true
            }
        }

        // not reaches till end of word
        if (currentNode?.isEndOfString == true) {
            deleteString(currentNode, word, index + 1)
            return false
        }

        val canThisNodeBeDeleted = deleteString(currentNode, word, index + 1)
        return if (canThisNodeBeDeleted) {
            // till its returning true removing the whole word
            root?.children?.remove(ch)
            true
        } else {
            false
        }
    }
}

fun main() {
    val trie = Trie()
    trie.insert("app")
    trie.insert("apps")
    trie.insert("bcci")
    trie.insert("bcc")
    println(trie.search("bcc"))
    println(trie.deleteString(trie.root, "app", 0))
    println(trie.search("app"))
}