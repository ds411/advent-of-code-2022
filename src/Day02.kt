fun main() {

    val moveValue = mapOf(
        "X" to 1,
        "Y" to 2,
        "Z" to 3,
        "A" to 1,
        "B" to 2,
        "C" to 3
    )

    val comboValue = mapOf(
        Pair("A", "X") to 3,
        Pair("A", "Y") to 6,
        Pair("A", "Z") to 0,
        Pair("B", "X") to 0,
        Pair("B", "Y") to 3,
        Pair("B", "Z") to 6,
        Pair("C", "X") to 6,
        Pair("C", "Y") to 0,
        Pair("C", "Z") to 3,
    )

    val resultValue = mapOf(
        "X" to 0,
        "Y" to 3,
        "Z" to 6
    )

    val choiceValue = mapOf(
        Pair("A", "X") to moveValue["C"]!! + resultValue["X"]!!,
        Pair("B", "X") to moveValue["A"]!! + resultValue["X"]!!,
        Pair("C", "X") to moveValue["B"]!! + resultValue["X"]!!,
        Pair("A", "Y") to moveValue["A"]!! + resultValue["Y"]!!,
        Pair("B", "Y") to moveValue["B"]!! + resultValue["Y"]!!,
        Pair("C", "Y") to moveValue["C"]!! + resultValue["Y"]!!,
        Pair("A", "Z") to moveValue["B"]!! + resultValue["Z"]!!,
        Pair("B", "Z") to moveValue["C"]!! + resultValue["Z"]!!,
        Pair("C", "Z") to moveValue["A"]!! + resultValue["Z"]!!,
    )

    fun part1(input: List<String>): Int {
        return input.fold(0) { total, round ->
            val (enemy, player) = round.split(' ').take(2)
            val pair = Pair(enemy, player)
            val roundScore = moveValue[player]!! + comboValue[pair]!!
            total + roundScore
        }
    }

    fun part2(input: List<String>): Int {
        return input.fold(0) { total, round ->
            val (enemy, result) = round.split(' ').take(2)
            val pair = Pair(enemy, result)
            val roundScore = choiceValue[pair]!!
            total + roundScore
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
