package com.example.inventoryapp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Giá") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (
                        name.isNotEmpty() &&
                        quantity.isNotEmpty() &&
                        price.isNotEmpty()
                    ) {
                        viewModel.addItem(
                            name,
                            quantity.toInt(),
                            price.toDouble()
                        )

                        name = ""
                        quantity = ""
                        price = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Thêm sản phẩm")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(itemList) { item ->
                    ItemCard(
                        item = item,
                        onDelete = {
                            viewModel.deleteItem(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    item: Item,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Tên: ${item.name}")
            Text(text = "Số lượng: ${item.quantity}")
            Text(text = "Giá: ${item.price}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onDelete) {
                Text("Xóa")
            }
        }
    }
}