fun main() {

    fun part1(input: List<String>): Int {
        val dirByPath = buildFilesystem(input)
        return dirByPath.values.filter { it.getSize() <= 100000 }.sumOf { it.getSize() }
    }

    fun part2(input: List<String>): Int {
        val dirByPath = buildFilesystem(input)
        val spaceAvailable = 70000000
        val spaceNeeded = 30000000
        val spaceUsed = dirByPath["/"]!!.getSize()
        val spaceUnused = spaceAvailable - spaceUsed
        val spaceToFree = spaceNeeded - spaceUnused
        return dirByPath.values.map { it.getSize() }.filter { it >= spaceToFree }.min()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

private fun buildFilesystem(input: List<String>): Map<String, Directory> {
    val dirByPath = mutableMapOf<String, Directory>()
    val fs = Directory("/")
    dirByPath["/"] = fs
    var cwd = fs
    var ptr = 0

    while (ptr < input.size) {
        val line = input[ptr]
        if (line.startsWith('$')) {
            val parts = line.split(' ')
            val command = parts[1]
            if (command == "cd") {
                val dir = parts[2]
                when (dir) {
                    "/" -> cwd = fs
                    ".." -> {
                        val path = cwd.absolutePath.substringBeforeLast('/')
                        cwd = dirByPath[path]!!
                    }
                    else -> {
                        val node = cwd.nodes[dir]
                        if (node == null) {
                            val path = "${cwd.absolutePath}/${dir}"
                            val directory = Directory(path)
                            dirByPath[path] = directory
                            cwd.nodes[dir] = directory
                            cwd = directory
                        }
                        else {
                            cwd = node as Directory
                        }
                    }
                }
            }
            ptr += 1
        }
        else {
            val parts = line.split(' ')
            val size = parts[0]
            val name = parts[1]
            if (size == "dir") {
                if (!cwd.nodes.containsKey(name)) {
                    val path = "${cwd.absolutePath}/${name}"
                    val directory = Directory(path)
                    dirByPath[path] = directory
                    cwd.nodes[name] = directory
                }
            }
            else {
                val size = size.toInt()
                cwd.nodes[name] = File(size)
            }
            ptr += 1
        }
    }
    return dirByPath
}

private interface FSNode {
    fun getSize(): Int
}

private class Directory(val absolutePath: String): FSNode {

    val nodes: MutableMap<String, FSNode> = mutableMapOf()
    override fun getSize(): Int {
        return nodes.values.sumOf { it.getSize() }
    }

}

private class File(private val size: Int): FSNode {

    override fun getSize(): Int {
        return size
    }

}