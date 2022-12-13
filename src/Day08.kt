fun main() {

    fun part1(input: List<String>): Int {
        val cols = input[0].length
        val rows = input.size
        val grid = input.map { it.toCharArray().map { it.toInt() } }

        val visibleTrees = hashSetOf<Tree>()
        var max = -1;

        // From top
        (0 until cols).forEach { col ->
            max = -1
            (0 until rows).forEach { row ->
                val height = grid[row][col]
                if (height > max) {
                    val tree = Tree(
                        x = col,
                        y = row,
                        height = height
                    )
                    visibleTrees += tree
                    max = height
                }
            }
        }

        // From bottom
        (0 until cols).forEach { col ->
            max = -1
            (rows-1 downTo 0).forEach { row ->
                val height = grid[row][col]
                if (height > max) {
                    val tree = Tree(
                        x = col,
                        y = row,
                        height = height
                    )
                    visibleTrees += tree
                    max = height
                }
            }
        }

        // From left
        (0 until rows).forEach { row ->
            max = -1
            (0 until cols).forEach { col ->
                val height = grid[row][col]
                if (height > max) {
                    val tree = Tree(
                        x = col,
                        y = row,
                        height = height
                    )
                    visibleTrees += tree
                    max = height
                }
            }
        }

        // From right
        (0 until rows).forEach { row ->
            max = -1
            (cols-1 downTo 0).forEach { col ->
                val height = grid[row][col]
                if (height > max) {
                    val tree = Tree(
                        x = col,
                        y = row,
                        height = height
                    )
                    visibleTrees += tree
                    max = height
                }
            }
        }

        return visibleTrees.size
    }

    fun part2(input: List<String>): Int {
        val cols = input[0].length
        val rows = input.size
        val grid = input.map { it.toCharArray().map { it.toInt() } }

        var max = -1;
        (0 until cols).forEach { col ->
            (0 until rows).forEach { row ->
                val score = calcScore(grid, col, row)
                if (score > max) max = score
            }
        }

        return max
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    println(part2(testInput))
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

private fun calcScore(grid: List<List<Int>>, col: Int, row: Int): Int {
    if (row == 0 || col == 0) return 0

    val rows = grid.size
    val cols = grid[0].size

    val height = grid[row][col]
    var folding = true
    val left = (col-1 downTo 0).fold(0) { acc, col ->
        if (folding) {
            val height2 = grid[row][col]
            if (height2 >= height) folding = false
            acc+1
        }
        else acc
    }
    folding = true
    val right = (col+1 .. cols-1).fold(0) { acc, col ->
        if (folding) {
            val height2 = grid[row][col]
            if (height2 >= height) folding = false
            acc+1
        }
        else acc
    }
    folding = true
    val top = (row-1 downTo 0).fold(0) { acc, row ->
        if (folding) {
            val height2 = grid[row][col]
            if (height2 >= height) folding = false
            acc+1
        }
        else acc
    }
    folding = true
    val bottom = (row+1 .. rows-1).fold(0) { acc, row ->
        if (folding) {
            val height2 = grid[row][col]
            if (height2 >= height) folding = false
            acc+1
        }
        else acc
    }
    return left * right * top * bottom
}

private data class Tree(
    val x: Int,
    val y: Int,
    val height: Int
)
