fun main() {
    fun part1(input: List<String>): Int {
        val loads = calcLoads(input)
        return loads.max()
    }

    fun part2(input: List<String>): Int {
        val loads = calcLoads(input)
        return loads.sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun calcLoads(input: List<String>): List<Int> {
    return input.fold(mutableListOf<Int>(0)) { list, line ->
        if (line == "") {
            list += 0
        }
        else {
            list[list.lastIndex] += line.toInt()
        }
        list
    }
}