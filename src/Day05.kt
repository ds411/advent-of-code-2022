fun main() {

    fun part1(input: List<String>): String {
        return runCrane(input, reversed = true)
    }

    fun part2(input: List<String>): String {
        return runCrane(input, reversed = false)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

private fun runCrane(input: List<String>, reversed: Boolean): String {
    val breakpoint = input.indexOf("")
    val boxes = input.subList(0, breakpoint)
    val rules = input.subList(breakpoint+1, input.size)
    val columnRow = boxes.last()
    val columns = columnRow.toCharArray().filter(Char::isDigit)
    val indexByColumn = columns.fold(mutableMapOf<Char, Int>()) { map, col ->
        map[col] = columnRow.indexOf(col)
        map
    }
    val boxRows = boxes.subList(0, boxes.lastIndex).reversed()
    val stackByCol = mutableMapOf<Char, String>()
    columns.forEach { stackByCol[it] = "" }
    boxRows.forEach { row ->
        columns.forEach { col ->
            val index = indexByColumn[col]!!
            if (index < row.length) {
                val char = row[index]
                if (char != ' ') {
                    stackByCol[col] = char + stackByCol[col]!!
                }
            }
        }
    }

    val regex = Regex("move ([\\d]+) from ([\\d]+) to ([\\d]+)")
    rules.forEach { rule ->
        val (len, src, tgt) = regex.find(rule)!!.destructured
        val _str = stackByCol[src[0]]!!.substring(0, len.toInt())
        val str = if (reversed) _str.reversed() else _str
        stackByCol[src[0]] = stackByCol[src[0]]!!.substring(len.toInt())
        stackByCol[tgt[0]] = str + stackByCol[tgt[0]]!!
    }
    return columns.map { stackByCol[it]!!.first() }.filter { it.isLetter() }.joinToString("")
}
