package com.example.crudfirabaseatividade2.model

import java.io.Serializable

class Product(val id: String?, var name:String, var price: String):Serializable{


     fun toProductHashMap() :HashMap<String,String>{
        return hashMapOf(
            ProductConstants.NAME to name,
            ProductConstants.PRICE to price
         )
     }
     object ProductConstants{
        const val COLLECTION = "products"
        const val NAME = "name"
        const val PRICE = "price"
    }

}