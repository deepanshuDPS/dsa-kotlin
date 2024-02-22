package graphs

class Path(val s: Char, val d: Char, val w: Int) {
    override fun toString(): String {
        return "$s->$d = $w"
    }
}
