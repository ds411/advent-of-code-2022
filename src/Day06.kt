fun main() {

    fun part1(input: List<String>): Int {
        val str = input.joinToString("")
        val len = 4
        val pos = distinctStringEndPos(str, len)
        return pos
    }

    fun part2(input: List<String>): Int {
        val str = input.joinToString("")
        val len = 14
        val pos = distinctStringEndPos(str, len)
        return pos
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

private fun distinctStringEndPos(str: String, len: Int): Int {
    val pos = (0 until str.length+1-len).first { start ->
        val substr = str.substring(start, start+len)
        substr.toSet().size == len
    }
    return pos + len
}
