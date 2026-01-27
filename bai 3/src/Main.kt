fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    println("Phần tử đầu tiên: ${numbers[0]}")
    println("Kích thước: ${numbers.size}")

    val entrants = mutableListOf<String>()
    entrants.add("Alice")
    entrants.add("Bob")
    entrants.remove("Alice")
    println("Danh sách: $entrants")

    val colors = listOf("Red", "Green", "Blue")
    for (color in colors) {
        println("Màu: $color")
    }

    val filtered = numbers.filter { it > 3 } // [4, 5]
    val doubled = numbers.map { it * 2 }     // [2, 4, 6, 8, 10]
    println("Sau khi lọc: $filtered")
}