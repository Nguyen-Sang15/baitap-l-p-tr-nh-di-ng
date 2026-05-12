package com.example.firestorecrud
                .addOnSuccessListener {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Lỗi: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // READ
        btnRead.setOnClickListener {
            val name = edtName.text.toString()

            db.collection("Users")
                .document(name)
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val userName = it.getString("name")
                        val userAge = it.getString("age")

                        txtResult.text = "Tên: $userName\nTuổi: $userAge"
                    } else {
                        txtResult.text = "Không tìm thấy dữ liệu"
                    }
                }
        }

        // UPDATE
        btnUpdate.setOnClickListener {
            val name = edtName.text.toString()
            val age = edtAge.text.toString()

            db.collection("Users")
                .document(name)
                .update("age", age)
                .addOnSuccessListener {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Update thất bại", Toast.LENGTH_SHORT).show()
                }
        }

        // DELETE
        btnDelete.setOnClickListener {
            val name = edtName.text.toString()

            db.collection("Users")
                .document(name)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show()
                    txtResult.text = ""
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show()
                }
        }
    }
}