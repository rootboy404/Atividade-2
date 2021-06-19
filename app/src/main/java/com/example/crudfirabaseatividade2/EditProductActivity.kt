package com.example.crudfirabaseatividade2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.crudfirabaseatividade2.model.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditProductActivity : AppCompatActivity() {
    var db= Firebase.firestore
    private lateinit var id:String
    private lateinit var textName: EditText
    private lateinit var textPrice: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)
        val intent = intent
        val product = intent.getSerializableExtra("product") as Product
        textName = findViewById<EditText>(R.id.idTextName).apply {
            setText(product.name)
        }
        textPrice = findViewById<EditText>(R.id.idTextPrice).apply {
            setText(product.price)
        }
        id = product.id.toString();
    }

    fun saveEdit(view: View){
        val productToSave = Product(null,textName.text.toString(),textPrice.text.toString())
        db.collection(Product.ProductConstants.COLLECTION).document(id)
            .set(productToSave.toProductHashMap())
            .addOnSuccessListener {
                setResult(5)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Erro",Toast.LENGTH_LONG).show()
            }
    }

    fun delete(view: View){
        db.collection(Product.ProductConstants.COLLECTION).document(id)
            .delete()
            .addOnSuccessListener { setResult(6)
                finish() }
            .addOnFailureListener {     Toast.makeText(this,"Erro",Toast.LENGTH_LONG).show() }

    }
}