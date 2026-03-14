package com.example.deliveryfood.model

enum class Category(val displayName: String) {
    BURGERS("Бургеры"),
    PIZZA("Пицца"),
    SUSHI("Суши"),
    DRINKS("Напитки"),
    DESSERTS("Десерты");

    companion object{
        fun fromDisplayName(name: String): Category{
            return entries.first{it.displayName == name}
        }
    }
}