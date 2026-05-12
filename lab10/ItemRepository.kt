package com.example.inventoryapp.data

class ItemRepository(private val dao: ItemDao) {

    val allItems = dao.getAllItems()

    suspend fun insert(item: Item) {
        dao.insert(item)
    }

    suspend fun update(item: Item) {
        dao.update(item)
    }

    suspend fun delete(item: Item) {
        dao.delete(item)
    }
}