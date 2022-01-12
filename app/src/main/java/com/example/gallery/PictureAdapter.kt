package com.example.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class PictureAdapter: BaseAdapter {

    val context: Context
    val pictures: List<Picture>

    constructor(context: Context, pictures: List<Picture>) : super() {
        this.context = context
        this.pictures = pictures
    }

    override fun getCount(): Int {
        return pictures.size
    }

    override fun getItem(position: Int): Any {
        return pictures[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.pictures_list_item, null, true)
        val itemPicture = itemView.findViewById(R.id.itemPicture) as ImageView
        val itemTitle = itemView.findViewById(R.id.itemTitle) as TextView
        val itemDescription = itemView.findViewById(R.id.itemDescription) as TextView
        val itemLocation = itemView.findViewById(R.id.itemLocation) as TextView
        val itemFavorite = itemView.findViewById(R.id.itemFavorite) as ImageView

        itemPicture.setImageBitmap(Utils.bytesToBitmap(pictures[position].blob))
        itemTitle.text = pictures[position].title
        itemDescription.text = pictures[position].description
        itemLocation.text = pictures[position].location
        if (pictures[position].favorite)
            itemFavorite.visibility = View.VISIBLE
        return itemView
    }

}
