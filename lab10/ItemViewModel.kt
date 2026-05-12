package com.example.inventoryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.data.InventoryDatabase
import com.example.inventoryapp.data.Item
import com.example.inventoryapp.data.ItemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ItemRepository

    init {
        val dao = InventoryDatabase
            .getDatabase(application)
            .itemDao()

        repository = ItemRepository(dao)
    }

    val items = repository.allItems.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addItem(name: String, quantity: Int, price: Double) {
        viewModelScope.launch {
            repository.insert(
                Item(
                    name = name,
                    quantity = quantity,
                    price = price
                )
            )
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }
}