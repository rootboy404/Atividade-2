package com.example.crudfirabaseatividade2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.crudfirabaseatividade2.R
import com.example.crudfirabaseatividade2.model.Product

class ItemProductAdapter (val productList: List<Product>): Adapter<ItemProductAdapter.ItemProductViewHolder>() {
    var onClickItem:OnClickItem?=null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ItemProductViewHolder(inflate);
    }

    override fun onBindViewHolder(holder: ItemProductViewHolder, position: Int) {
       holder.bind(productList[position])
    }

    override fun getItemCount() = productList.size


    inner class ItemProductViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(product: Product){
            itemView.findViewById<TextView>(R.id.productName).text = product.name
            itemView.findViewById<TextView>(R.id.idPrice).text=product.price.toString()
            itemView.setOnClickListener{
                onClickItem?.edit(itemView,product)
            }
        }
    }


    interface OnClickItem{
        fun edit(view: View,product: Product)
    }

}