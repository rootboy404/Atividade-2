package com.example.crudfirabaseatividade2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.crudfirabaseatividade2.model.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateProductActivity : AppCompatActivity() {

    var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)
    }



    fun create(view : View){
        var findNameViewById = findViewById<TextView>(R.id.idTextName)
        var findPriceViewById = findViewById<TextView>(R.id.idTextPrice)
        var product =
            Product(null, findNameViewById.text.toString(), findPriceViewById.text.toString())

        db.collection(Product.ProductConstants.COLLECTION)
            .add(product.toProductHashMap())
            .addOnSuccessListener { documentReference ->
                setResult(Activity.RESULT_OK)
                finish();
            }
            .addOnFailureListener { e ->
                setResult(Activity.RESULT_CANCELED)
                finish();
            }
    }

    fun cancel(view: View){
        setResult(Activity.RESULT_CANCELED)
        finish();
    }

    fun saveEdit(view: View) {}
    fun delete(view: View) {}
}