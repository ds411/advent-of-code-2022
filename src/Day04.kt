fun main() {

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(',') }
            .fold(0) { acc, pair ->
                val range1 = pair[0].split('-').map(String::toInt)
                val range2 = pair[1].split('-').map(String::toInt)
                if (rangeEncompasses(range1, range2)) acc + 1
                else acc
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(',') }
            .fold(0) { acc, pair ->
                val range1 = pair[0].split('-').map(String::toInt)
                val range2 = pair[1].split('-').map(String::toInt)
                if (rangeOverlaps(range1, range2)) acc + 1
                else acc
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

private fun rangeEncompasses(range1: List<Int>, range2: List<Int>): Boolean {
    val r1 = (range1[0]..range1[1])
    val r2 = (range2[0]..range2[1])
    return (r1.first <= r2.first && r1.last >= r2.last) ||
            (r2.first <= r1.first && r2.last >= r1.last)
}

private fun rangeOverlaps(range1: List<Int>, range2: List<Int>): Boolean {
    val r1 = (range1[0]..range1[1])
    val r2 = (range2[0]..range2[1])
    return r1.contains(r2.first) || r1.contains(r2.last) || r2.contains(r1.first) || r2.contains(r1.last)
}

