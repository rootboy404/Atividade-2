package com.example.crudfirabaseatividade2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudfirabaseatividade2.adapter.ItemProductAdapter
import com.example.crudfirabaseatividade2.model.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(),ItemProductAdapter.OnClickItem{
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
        viewAdapter.onClickItem = this
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

   fun createNewProduct(view : View) {
       intent = Intent(this, CreateProductActivity::class.java)
       resultLauncherCreate.launch(intent);

   }

    override fun edit(view: View, product: Product) {
            intent = Intent(this, EditProductActivity::class.java)
        intent.putExtra("product",product)
        resultLauncherCreate.launch(intent)
    }


    var resultLauncherCreate =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                getAllProduct()
                Toast.makeText(this, "Criado novo produto", Toast.LENGTH_LONG).show()
            }
            if (result.resultCode == 6) {
            getAllProduct()
            Toast.makeText(this, "Deletado produto", Toast.LENGTH_LONG).show()
            }
            if (result.resultCode == 5) {
                getAllProduct()
                Toast.makeText(this, "Atualizado produto", Toast.LENGTH_LONG).show()
            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Não foi criado novo produto", Toast.LENGTH_LONG).show()
            }

        }
}