package com.example.crudfirabaseatividade2.model

 class Product(val id:String, var name:String, var price: String) {

     object ProductConstants{
        const val COLLECTION = "products"
        const val NAME = "name"
        const val PRICE = "price"
    }

}