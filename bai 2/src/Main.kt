fun main() {
    val num = 4
    if (num > 4) {
        println("Lớn hơn 4")
    } else if (num == 4) {
        println("Bằng 4")
    } else {
        println("Nhỏ hơn 4")
    }

    val luckyNumber = 6
    when (luckyNumber) {
        6 -> println("Số may mắn!")
        7 -> println("Số bình thường")
        else -> println("Không phải số 6 hoặc 7")
    }

    val diceRange = 1..6
    val randomNumber = diceRange.random()
    repeat(3) {
        println("Lặp lại lần thứ ${it + 1}")
    }

    val myDice = Dice(6)
    println("Xúc xắc có ${myDice.numSides} mặt")
    myDice.roll()
}

class Dice(val numSides: Int) {
    fun roll() {
        val result = (1..numSides).random()
        println("Kết quả tung: $result")
    }
}