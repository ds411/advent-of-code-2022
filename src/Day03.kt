fun main() {

    fun part1(input: List<String>): Int {
        return input.map {
            val midpoint = it.length / 2
            val sack1 = it.substring(0, midpoint)
            val sack2 = it.substring(midpoint)
            sack1.toCharArray().first {
                sack2.contains(it)
            }
        }.map(::itemValue).sum()
    }

    fun part2(input: List<String>): Int {
        return (0 until input.size step 3).map {
            val sack1 = input[it].toHashSet()
            val sack2 = input[it+1].toHashSet()
            val sack3 = input[it+2].toHashSet()
            sack1.first {
                sack2.contains(it) && sack3.contains(it)
            }
        }.map(::itemValue).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun itemValue(item: Char): Int {
    if (item.isLowerCase()) return item.minus('a') + 1
    else return item.minus('A') + 27
}
