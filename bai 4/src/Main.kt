import kotlinx.coroutines.*

abstract class Dwelling(var residents: Int) {
    abstract val buildingMaterial: String
    abstract val capacity: Int
    fun hasRoom() = residents < capacity
}

class RoundHut(residents: Int) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4
}

fun main() = runBlocking {
    val myHut = RoundHut(3)
    println("Vật liệu: ${myHut.buildingMaterial}")
    println("Còn chỗ không? ${myHut.hasRoom()}")

    launch {
        delay(1000L)
        println("Coroutine đã chạy xong!")
    }
    println("Đang chờ Coroutine...")
}