package com.example.crudfirabaseatividade2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudfirabaseatividade2.adapter.ItemProductAdapter
import com.example.crudfirabaseatividade2.model.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllProduct()

    }

    private fun getAllProduct() {
       db.collection(Product.ProductConstants.COLLECTION)
            .get()
            .addOnSuccessListener { result ->
               createList(result.documents.map { it-> Product(it.id,it.get(Product.ProductConstants.NAME).toString(),it.get(Product.ProductConstants.PRICE).toString()) })

            }
            .addOnFailureListener { exception ->
               Toast.makeText(this,"erro ao carregar lista",Toast.LENGTH_LONG).show()
            }
    }

    private fun createList(list: List<Product>) {
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = ItemProductAdapter(list)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

   fun createNewProduct(){
       intent = Intent(this,CreateProductActivity::class.java)
       resultLauncherCreate.launch(intent);
   }


    var resultLauncherCreate  =   registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            getAllProduct()
            Toast.makeText(this,"Criado novo produto",Toast.LENGTH_LONG).show()

        }
    }


}